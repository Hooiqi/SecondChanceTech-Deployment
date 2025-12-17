package org.secondchancetech.model;

public class Product {
    private int productId;
    private int gadgetId;
    private int deliveryDay;
    private int stockDay;
    private int guaranteedPeriod;
    private double price;

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public int getGadgetId() { return gadgetId; }
    public void setGadgetId(int gadgetId) { this.gadgetId = gadgetId; }
    public int getDeliveryDay() { return deliveryDay; }
    public void setDeliveryDay(int deliveryDay) { this.deliveryDay = deliveryDay; }
    public int getStockDay() { return stockDay; }
    public void setStockDay(int stockDay) { this.stockDay = stockDay; }
    public int getGuaranteedPeriod() { return guaranteedPeriod; }
    public void setGuaranteedPeriod(int guaranteedPeriod) { this.guaranteedPeriod = guaranteedPeriod; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
