package org.example.Model;

public class Items {

    private String iID;
    private String iName;
    private String iDescription;
    private String iLocation;

    private int iAttack;

    private int iHeal;
    private int mHealth;

    public Items(String iId, String iName, String iDescription, String iLocation, int iAttack, int iHeal, int mHealth) {
        this.iID = iId;
        this.iName = iName;
        this.iDescription = iDescription;
        this.iLocation = iLocation;
        this.iAttack = iAttack;
        this.iHeal = iHeal;
        this.mHealth = mHealth;

    }

    public String getiName() {
        return iName;
    }

    public String getiDescription() {
        return iDescription;
    }

    public String getiLocation() {
        return iLocation;
    }

    public void look() {
        System.out.println(iDescription);
    }

}
