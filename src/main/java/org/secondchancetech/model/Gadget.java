package org.secondchancetech.model;

public class Gadget {
    private int gadgetId;
    private String name;
    private String imagePath;
    private double price;

    public int getGadgetId() { return gadgetId; }
    public void setGadgetId(int gadgetId) { this.gadgetId = gadgetId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    // New Getter and Setter to fix the DAO error
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}