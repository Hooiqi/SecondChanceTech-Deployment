package org.secondchancetech.model;

public class ProductSpec {
    private int productSpecId;
    private int productId;
    private int gadgetSpecId;
    private String specValue;

    public int getProductSpecId() { return productSpecId; }
    public void setProductSpecId(int productSpecId) { this.productSpecId = productSpecId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getGadgetSpecId() { return gadgetSpecId; }
    public void setGadgetSpecId(int gadgetSpecId) { this.gadgetSpecId = gadgetSpecId; }

    public String getSpecValue() { return specValue; }
    public void setSpecValue(String specValue) { this.specValue = specValue; }

    public ProductSpec() {}

    public ProductSpec(int productSpecId, int productId, int gadgetSpecId, String specValue) {
        this.productSpecId = productSpecId;
        this.productId = productId;
        this.gadgetSpecId = gadgetSpecId;
        this.specValue = specValue;
    }
}
