package com.example.demo.inventory;

import com.example.demo.shipment_inventory_item.ShipmentInventoryItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "inventory")
@Data
@NoArgsConstructor
public class InventoryItem {
    @Id
    @SequenceGenerator(
        name="inventory_item_seq",
        sequenceName="inventory_item_seq",
        allocationSize=1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator="inventory_item_seq"
    )
    private Long id;

    @Column(
        name = "item_name",
        nullable = false
    )
    private String itemName;

    private Integer unitInStock;
    private Double unitPrice;

    @OneToMany(mappedBy = "shipment")
    private List<ShipmentInventoryItem> shipments;

    public InventoryItem(String itemName, Integer unitInStock, Double unitPrice) {
        this.itemName = itemName;
        this.unitInStock = unitInStock;
        this.unitPrice = unitPrice;
    }
}
