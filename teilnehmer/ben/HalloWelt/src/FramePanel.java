import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FramePanel extends JPanel
{

    public FramePanel()
    {
        JButton loeschButton = new JButton("Löschen");
        JButton beendenButton = new JButton("Beenden");

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));

        MalPanel malPanel = new MalPanel();

        ActionListener loeschListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                repaint();
            }
        };

        ActionListener beendenListener = new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        };

        loeschButton.addActionListener(loeschListener);
        beendenButton.addActionListener(beendenListener);

        add(Box.createHorizontalStrut(5));
        add(malPanel);
        add(Box.createHorizontalStrut(5));
        add(buttons);
        add(Box.createHorizontalStrut(5));

        buttons.add(Box.createVerticalGlue());
        buttons.add(loeschButton);
        buttons.add(Box.createVerticalStrut(5));
        buttons.add(beendenButton);
        buttons.add(Box.createVerticalStrut(5));
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new FramePanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
