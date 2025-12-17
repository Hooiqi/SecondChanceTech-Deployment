// Cart.java
package org.secondchancetech.model;

public class Cart {
    private int cartId;
    private int userId;
    private int productId;
    private int quantity;
    private String status; // "DRAFT", "PAID"

    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
