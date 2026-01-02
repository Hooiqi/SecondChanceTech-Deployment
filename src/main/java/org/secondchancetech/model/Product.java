package org.secondchancetech.model;

public class Product {
    private int productId;
    private int gadgetId;
    private String name;
    private String imagePath;
    private int deliveryDay;
    private int guaranteedPeriod;
    private double price;

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public int getGadgetId() { return gadgetId; }
    public void setGadgetId(int gadgetId) { this.gadgetId = gadgetId; }
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public int getDeliveryDay() { return deliveryDay; }
    public void setDeliveryDay(int deliveryDay) { this.deliveryDay = deliveryDay; }
    public int getGuaranteedPeriod() { return guaranteedPeriod; }
    public void setGuaranteedPeriod(int guaranteedPeriod) { this.guaranteedPeriod = guaranteedPeriod; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
