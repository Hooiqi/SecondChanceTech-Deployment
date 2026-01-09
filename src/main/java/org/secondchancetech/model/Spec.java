package org.secondchancetech.model;

public class Spec {
    private int specId;
    private int gadgetId;
    private String specKey;

    public int getSpecId() { return specId; }
    public void setSpecId(int specId) { this.specId = specId; }

    public int getGadgetId() { return gadgetId; }
    public void setGadgetId(int gadgetId) { this.gadgetId = gadgetId; }

    public String getSpecKey() { return specKey; }
    public void setSpecKey(String specKey) { this.specKey = specKey; }

    public Spec() {}

    public Spec(int specId, int gadgetId, String key) {
        this.specId = specId;
        this.gadgetId = gadgetId;
        this.specKey = key;
    }
}
