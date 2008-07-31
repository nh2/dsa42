package de.schelklingen2008.reversi.ai.strategy;


public enum MinMax {
    MIN, MAX;

    public MinMax other() {
        return this == MAX ? MIN : MAX;
    }

}
