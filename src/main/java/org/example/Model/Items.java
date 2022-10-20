package org.example.Model;

public class Items {

    private String iName;
    private String iDescription;

    private String iLocation;

    public Items(String iName, String iDescription, String iLocation) {
        this.iName = iName;
        this.iDescription = iDescription;
        this.iLocation = iLocation;
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
