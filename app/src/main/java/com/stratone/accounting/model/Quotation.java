package com.stratone.accounting.model;

public class Quotation {
    String ItemQuotation;
    String Description;
    int Quantity;
    float UnitPrice;
    float Amount;

    public Quotation(String itemQuotation, String description, int quantity, float unitPrice, float amount) {
        ItemQuotation = itemQuotation;
        Description = description;
        Quantity = quantity;
        UnitPrice = unitPrice;
        Amount = amount;
    }

    public String getItemQuotation() {
        return ItemQuotation;
    }

    public void setItemQuotation(String itemQuotation) {
        ItemQuotation = itemQuotation;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public float getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        UnitPrice = unitPrice;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }
}
