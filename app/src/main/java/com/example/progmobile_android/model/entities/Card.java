package com.example.progmobile_android.model.entities;

public class Card {

    private String cardHolderName;
    private String cardNumber;
    private String valid;
    private Integer securityCode;

    public Card() {
    }

    public Card(String cardHolderName, String cardNumber, String valid, Integer securityCode) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.valid = valid;
        this.securityCode = securityCode;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public Integer getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(Integer securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardHolderName='" + cardHolderName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", valid='" + valid + '\'' +
                ", securityCode=" + securityCode +
                '}';
    }
}
