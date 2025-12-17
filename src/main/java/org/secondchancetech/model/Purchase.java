// Purchase.java
package org.secondchancetech.model;

public class Purchase {
    private int purchaseId;
    private int cartId;
    private String purchasedDate;
    private double totalAmount;

    public int getPurchaseId() { return purchaseId; }
    public void setPurchaseId(int purchaseId) { this.purchaseId = purchaseId; }
    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }
    public String getPurchasedDate() { return purchasedDate; }
    public void setPurchasedDate(String purchasedDate) { this.purchasedDate = purchasedDate; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}
