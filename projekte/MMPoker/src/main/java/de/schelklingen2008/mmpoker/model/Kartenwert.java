/**
 * 
 */
package de.schelklingen2008.mmpoker.model;

public enum Kartenwert {
    ZWEI("2"),
    DREI("3"),
    VIER("4"),
    FUENF("5"),
    SECHS("6"),
    SIEBEN("7"),
    ACHT("8"),
    NEUN("9"),
    ZEHN("10"),
    BUBE("j"),
    DAME("q"),
    KOENIG("k"),
    ASS("a");

    private String imageName;

    private Kartenwert(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }
}