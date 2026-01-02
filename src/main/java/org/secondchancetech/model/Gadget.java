package org.secondchancetech.model;

public class Gadget {
    private int gadgetId;
    private String name; // smartphone, camera
    private String imagePath; // e.g., uploads/smartphone.png

    public int getGadgetId() { return gadgetId; }
    public void setGadgetId(int gadgetId) { this.gadgetId = gadgetId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}
