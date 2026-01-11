package org.secondchancetech.model;

public class Spec {
    private int specId;
    private int gadgetId;    // For SmartScoringServlet
    private int productId;   // Matches DB 'product_id'
    private String specKey;  // Matches DB 'key'
    private String specValue; // Matches DB 'value'

    public Spec() {}

    // RESTORED: Old constructor used by SmartScoringServlet
    public Spec(int specId, int gadgetId, String specKey) {
        this.specId = specId;
        this.gadgetId = gadgetId;
        this.specKey = specKey;
    }

    // Getters and Setters
    public int getSpecId() { return specId; }
    public void setSpecId(int specId) { this.specId = specId; }
    public int getGadgetId() { return gadgetId; }
    public void setGadgetId(int gadgetId) { this.gadgetId = gadgetId; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getSpecKey() { return specKey; }
    public void setSpecKey(String specKey) { this.specKey = specKey; }
    public String getSpecValue() { return specValue; }
    public void setSpecValue(String specValue) { this.specValue = specValue; }
}