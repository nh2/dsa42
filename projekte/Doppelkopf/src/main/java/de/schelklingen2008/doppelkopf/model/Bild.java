package de.schelklingen2008.doppelkopf.model;

/**
 * Speichert die Bilder der Karten.
 */
public enum Bild
{
    As(11), Zehn(10), Koenig(4), Dame(3), Bube(2), Neun(0);

    private int wertigkeit;

    private Bild(int wertigkeit)
    {
        this.wertigkeit = wertigkeit;
    }

    public int getWertigkeit()
    {
        return wertigkeit;
    }
}
