// UserPayment.java
package org.secondchancetech.model;

public class UserPayment {
    private int userPaymentId;
    private String cardholderName;
    private String cardNumber;
    private String expDate;
    private String cvv;

    public int getUserPaymentId() { return userPaymentId; }
    public void setUserPaymentId(int userPaymentId) { this.userPaymentId = userPaymentId; }
    public String getCardholderName() { return cardholderName; }
    public void setCardholderName(String cardholderName) { this.cardholderName = cardholderName; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getExpDate() { return expDate; }
    public void setExpDate(String expDate) { this.expDate = expDate; }
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
}
