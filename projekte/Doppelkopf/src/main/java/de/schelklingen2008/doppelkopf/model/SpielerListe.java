package de.schelklingen2008.doppelkopf.model;

import java.util.ArrayList;

public class SpielerListe extends ArrayList<Spieler> {

	private static final long serialVersionUID = 1L;
	private int anDerReihe;
	
	public Spieler getAnDerReihe()
	{
		return this.get(anDerReihe);
	}
	
	public Spieler getNext()
	{
		return this.get(nextAnDerReihe(anDerReihe));
	}
	
	public void rotieren()
	{
		anDerReihe = nextAnDerReihe(anDerReihe);
	}
	
	private int nextAnDerReihe(int nach)
	{
		if(nach+1 < this.size())
			return nach+1;
		else
			return 0;
	}
}