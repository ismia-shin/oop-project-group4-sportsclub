package com.group4.sportsclub.Suzane.MarketingManager;

public class Merchandise {
    private String itemName;
    private int stockLevel;
    private double price;
    private boolean onSale;

    public Merchandise(String itemName, int stockLevel, double price) {
        this.itemName = itemName;
        this.stockLevel = stockLevel;
        this.price = price;
        this.onSale = false;
    }
    // Getters and Setters
    public String getItemName() { return itemName; }
    public int getStockLevel() { return stockLevel; }
    public void setPrice(double price) { this.price = price; }
    public void setOnSale(boolean onSale) { this.onSale = onSale; }

}
