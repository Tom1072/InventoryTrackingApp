package com.example.demo.inventory;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.DataSizeUnit;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
@Data
@NoArgsConstructor
public class InventoryItem {
    @Id
    @SequenceGenerator( name="inventory_item_seq", sequenceName="inventory_item_seq", allocationSize=1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator="inventory_item_seq")
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "unit_in_stock", columnDefinition = "INTEGER CHECK (unit_in_stock >= 0)")
    private Integer unitInStock;

    @Column(name = "unit_price", columnDefinition = "REAL CHECK (unit_price >= 0)")
    private Double unitPrice;

    public InventoryItem(String itemName, Integer unitInStock, Double unitPrice) {
        this.itemName = itemName;
        this.unitInStock = unitInStock;
        this.unitPrice = unitPrice;
    }
}
