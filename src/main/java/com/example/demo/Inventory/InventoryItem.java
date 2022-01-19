package com.example.demo.Inventory;

import javax.persistence.*;

@Entity
@Table(name = "Inventory")
public class InventoryItem {
    @Id
    @SequenceGenerator(name="inventory_item_seq",
            sequenceName="inventory_item_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="inventory_item_seq")
    private Long id;

    @Column(nullable = false)
    private String itemName;

    private Integer unitInStock;
    private Double unitPrice;

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

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "InventoryItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", unitInStock=" + unitInStock +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
