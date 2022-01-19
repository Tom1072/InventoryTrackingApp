package com.example.demo.Inventory;

public class InventoryItem {
    private long id;
    private String itemName;
    private int unitInStock;
    private double unitPrice;

    public InventoryItem() {
    }

    public InventoryItem(long id, String itemName, int unitInStock, double unitPrice) {
        this.id = id;
        this.itemName = itemName;
        this.unitInStock = unitInStock;
        this.unitPrice = unitPrice;
    }

    public InventoryItem(String itemName, int unitInStock, double unitPrice) {
        this.itemName = itemName;
        this.unitInStock = unitInStock;
        this.unitPrice = unitPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getUnitInStock() {
        return unitInStock;
    }

    public void setUnitInStock(int unitInStock) {
        this.unitInStock = unitInStock;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
