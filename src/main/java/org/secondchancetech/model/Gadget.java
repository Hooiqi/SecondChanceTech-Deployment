package org.secondchancetech.model;

public class Gadget {
    private int gadgetId;
    private int productId; // Added to match home.jsp and DB schema
    private String name;
    private double price;  // Added to match product.jsp and DB schema
    private String imagePath;

    // Getters and Setters
    public int getGadgetId() { return gadgetId; }
    public void setGadgetId(int gadgetId) { this.gadgetId = gadgetId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}