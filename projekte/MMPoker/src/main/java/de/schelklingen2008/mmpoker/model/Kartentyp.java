/**
 * 
 */
package de.schelklingen2008.mmpoker.model;

public enum Kartentyp {

    KARO("diamonds"), HERZ("hearts"), PIK("spades"), KREUZ("clubs");

    private String imageName;

    private Kartentyp(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }

}