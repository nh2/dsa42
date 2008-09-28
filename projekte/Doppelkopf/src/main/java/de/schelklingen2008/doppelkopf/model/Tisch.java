package de.schelklingen2008.doppelkopf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Tisch implements Serializable
{
	public static final int SONDERVERTEILUNG_NONE		= 0;
	public static final int SONDERVERTEILUNG_HOCHZEIT	= 1;
	
    private List<Karte>   mitte;
    private List<Spieler> mittenSpieler;
    private Spieler       anDerReihe;
    private int           stichAnzahl;
    private SpielerListe  spielerliste;
    private Spieler       hochzeitSpieler;

    public Spieler getHochzeitSpieler()
    {
        return hochzeitSpieler;
    }
    
    public void unsetHochzeitSpieler() 
    {
    	hochzeitSpieler = null;
    }

    public SpielerListe getSpielerliste()
    {
        return spielerliste;
    }

    private Set<Spieler> re, contra;
    public int           zusatzpunkte;
    public int           refuchs, contrafuchs;

    public Tisch(SpielerListe spielerliste)
    {
        mitte = new ArrayList<Karte>(4);
        mittenSpieler = new ArrayList<Spieler>(4);

        re = new HashSet<Spieler>();
        contra = new HashSet<Spieler>();

        this.spielerliste = spielerliste;
        // for (int i = 1; i <= 4; i++)
        // spielerliste.add(new Spieler("Spieler " + i));
        anDerReihe = spielerliste.getAnDerReihe();
    }

    public void gibKarten(int SONDERVERTEILUNG)
    {
        // TODO Spielerkarten leeren!
        for (Spieler p : spielerliste)
            p.getBlatt().leereKarten();

        Stack<Karte> stapel = erzeugeStapel(); // Ausgabekartenstapel erzeugen
        mischeStapel(stapel); // Stapel mischen
        for (Karte k : stapel)
            System.out.println(k.toString());

        // Karten verteilen
        switch (SONDERVERTEILUNG) {
			case SONDERVERTEILUNG_HOCHZEIT:
				verteileKartenHochzeit(stapel);
				break;
			default:
				verteileKartenNormal(stapel);
				break;
		}

        teileTeamsZu();
    }

    private void verteileKartenNormal(Stack<Karte> stapel) {
        while (!stapel.isEmpty())
        {
            anDerReihe.getBlatt().add(stapel.pop());
            anDerReihe = spielerliste.next();
        }
    }
    
    private void verteileKartenHochzeit(Stack<Karte> stapel) {

    	// Die beiden Kreuzdamen an den ersten Spieler verteilen
    	for(int anz=1; anz<=2; anz++) {
	    	int i=0;
	    	for(Karte k : stapel)
	    		if(k.farbe == Farbe.Kreuz && k.bild == Bild.Dame)
	    			break;
	    		else
	    			i++;
	    	
	        anDerReihe.getBlatt().add(stapel.remove(i));
    	}
    	
    	// Den anderen 3 Spielern jeweils 2 Karten geben
    	anDerReihe = spielerliste.next();
    	for(int anz=1; anz<=3; anz++) {
            anDerReihe.getBlatt().add(stapel.pop());
            anDerReihe = spielerliste.next();
    	}
    	
    	// Die restlichen Karten normal verteilen
    	verteileKartenNormal(stapel);
    }

    private void teileTeamsZu()
    {
        for (Spieler p : spielerliste)
            if (p.getBlatt().getKarte(Farbe.Kreuz, Bild.Dame) != null)
            {
                re.add(p);
                p.team = Team.Re;
            }
            else
            {
                contra.add(p);
                p.team = Team.Contra;
            }
        // Hochzeit-Spieler ermitteln
        if (getTeamRe().size() == 1) hochzeitSpieler = getTeamRe().iterator().next();
    }

    private Stack<Karte> erzeugeStapel()
    {
        Stack<Karte> stapel = new Stack<Karte>();
        for (Farbe f : Farbe.values())
            for (Bild b : Bild.values())
                for (int i = 0; i < 2; i++)
                    stapel.push(new Karte(f, b));

        return stapel;
    }

    private void mischeStapel(Stack<Karte> stapel)
    {
        Collections.shuffle(stapel);
    }

    public List<Karte> getMitte()
    {
        return mitte;
    }

    public List<Spieler> getMittenspieler()
    {
        return mittenSpieler;
    }

    public int getStichAnzahl()
    {
        return stichAnzahl;
    }

    public void stichGespielt()
    {
        mitte.clear();
        mittenSpieler.clear();
        stichAnzahl++;
    }

    public int getRePunkte()
    {
        int punkte = 0;
        for (Spieler p : re)
            for (Karte k : p.getGewinnstapel())
                punkte += k.bild.getWertigkeit();
        return punkte;
    }

    public int getContraPunkte()
    {
        int punkte = 0;
        for (Spieler p : contra)
            for (Karte k : p.getGewinnstapel())
                punkte += k.bild.getWertigkeit();
        return punkte;
    }

    public Set<Spieler> getTeamRe()
    {
        return re;
    }

    public Set<Spieler> getTeamContra()
    {
        return contra;
    }

}
