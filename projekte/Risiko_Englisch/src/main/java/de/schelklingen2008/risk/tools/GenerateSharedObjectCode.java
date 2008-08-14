package de.schelklingen2008.risk.tools;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.samskivert.util.StringUtil;
import com.samskivert.velocity.VelocityUtil;
import com.threerings.presents.dobj.DObject;
import com.threerings.presents.dobj.DSet;
import com.threerings.presents.dobj.OidList;
import com.threerings.presents.tools.GenUtil;
import com.threerings.presents.tools.SourceFile;

/**
 * Generates necessary additional distributed object declarations and methods.
 */
public class GenerateSharedObjectCode
{

    private static final ClassLoader    classloader  = Thread.currentThread().getContextClassLoader();

    private static final Class<DObject> dObjectClass = DObject.class;

    private static final Class<DSet>    dSetClass    = DSet.class;

    private static final Class<OidList> oidListClass = OidList.class;

    private static VelocityEngine       velocityEngine;

    private static void execute()
    {
        try
        {
            velocityEngine = VelocityUtil.createEngine();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Failure initializing Velocity", e);
        }

        File root = new File(".");
        SuffixFileFilter fileFilter = new SuffixFileFilter("State.java");
        Collection files = FileUtils.listFiles(root, fileFilter, DirectoryFileFilter.INSTANCE);
        for (Object file : files)
            processObject((File) file);
    }

    public static void main(String[] args)
    {
        execute();
    }

    /** Processes a distributed object source file. */
    private static void processObject(File source)
    {
        System.err.println("Processing " + source + "...");
        // load up the file and determine it's package and classname
        String name = null;
        try
        {
            name = GenUtil.readClassName(source);
        }
        catch (Exception e)
        {
            System.err.println("Failed to parse " + source + ": " + e.getMessage());
        }

        try
        {
            processObject(source, classloader.loadClass(name));
        }
        catch (ClassNotFoundException cnfe)
        {
            System.err.println("Failed to load " + name + ".\n" + "Missing class: " + cnfe.getMessage());
            System.err.println("Be sure to set the 'classpathref' attribute to a classpath\n"
                               + "that contains your projects invocation service classes.");
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);
        }
    }

    /** Processes a resolved distributed object class instance. */
    private static void processObject(File source, Class oclass)
    {
        if (!dObjectClass.isAssignableFrom(oclass) || dObjectClass.equals(oclass)) return;
        ArrayList<Field> flist = new ArrayList<Field>();
        Field fields[] = oclass.getDeclaredFields();
        for (Field f : fields)
        {
            int mods = f.getModifiers();
            if (Modifier.isPublic(mods) && !Modifier.isStatic(mods) && !Modifier.isTransient(mods)) flist.add(f);
        }

        SourceFile sfile = new SourceFile();
        try
        {
            sfile.readFrom(source);
        }
        catch (IOException ioe)
        {
            System.err.println(new StringBuilder().append("Error reading '")
                                                  .append(source)
                                                  .append("': ")
                                                  .append(ioe)
                                                  .toString());
            return;
        }
        StringBuilder fsection = new StringBuilder();
        StringBuilder msection = new StringBuilder();
        for (int ii = 0; ii < flist.size(); ii++)
        {
            Field f = flist.get(ii);
            Class ftype = f.getType();
            String fname = f.getName();
            VelocityContext ctx = new VelocityContext();
            ctx.put("field", fname);
            ctx.put("type", com.samskivert.util.GenUtil.simpleName(f));
            ctx.put("wrapfield", GenUtil.boxArgument(ftype, "value"));
            ctx.put("wrapofield", GenUtil.boxArgument(ftype, "ovalue"));
            ctx.put("clonefield", GenUtil.cloneArgument(dSetClass, f, "value"));
            ctx.put("capfield", StringUtil.unStudlyName(fname).toUpperCase());
            ctx.put("upfield", StringUtils.capitalize(fname));
            if (ftype.isArray())
            {
                Class etype = ftype.getComponentType();
                ctx.put("elemtype", com.samskivert.util.GenUtil.simpleName(etype, null));
                ctx.put("wrapelem", GenUtil.boxArgument(etype, "value"));
                ctx.put("wrapoelem", GenUtil.boxArgument(etype, "ovalue"));
            }
            if (dSetClass.isAssignableFrom(ftype))
            {
                java.lang.reflect.Type t;
                for (t = f.getGenericType(); t instanceof Class; t = ((Class) t).getGenericSuperclass())
                    ;
                if (t instanceof ParameterizedType)
                {
                    ParameterizedType pt = (ParameterizedType) t;
                    if (pt.getActualTypeArguments().length > 0) ctx.put(
                                                                        "etype",
                                                                        com.samskivert.util.GenUtil.simpleName(
                                                                                                               (Class) pt.getActualTypeArguments()[0],
                                                                                                               null));
                }
                else
                {
                    ctx.put("etype", "DSet.Entry");
                }
            }
            String tname = "field.tmpl";
            if (dSetClass.isAssignableFrom(ftype))
                tname = "set.tmpl";
            else if (oidListClass.isAssignableFrom(ftype)) tname = "oidlist.tmpl";
            StringWriter fwriter = new StringWriter();
            StringWriter mwriter = new StringWriter();
            try
            {
                velocityEngine.mergeTemplate("com/threerings/presents/tools/dobject_name.tmpl", "UTF-8", ctx, fwriter);
                velocityEngine.mergeTemplate(new StringBuilder().append("com/threerings/presents/tools/dobject_")
                                                                .append(tname)
                                                                .toString(), "UTF-8", ctx, mwriter);
            }
            catch (Exception e)
            {
                System.err.println("Failed processing template");
                e.printStackTrace(System.err);
            }
            if (ii > 0)
            {
                fsection.append("\n");
                msection.append("\n");
            }
            fsection.append(fwriter.toString());
            msection.append(mwriter.toString());
        }

        try
        {
            sfile.writeTo(source, fsection.toString(), msection.toString());
        }
        catch (IOException ioe)
        {
            System.err.println(new StringBuilder().append("Error writing '")
                                                  .append(source)
                                                  .append("': ")
                                                  .append(ioe)
                                                  .toString());
        }
    }
}
