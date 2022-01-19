package com.example.demo.shipment_inventory_item;

import com.example.demo.inventory.InventoryItem;
import com.example.demo.shipment.Shipment;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 * Association class between Shipment and InventoryItem
 */
@Entity
@Table(name = "shipment_inventory_item")
@IdClass(ShipmentInventoryItemId.class)
@Data
public class ShipmentInventoryItem {
    @Id
    @ManyToOne
    @JoinColumn(
            name = "shipment_id",
            referencedColumnName = "id"
    )
    private Shipment shipment;

    @Id
    @ManyToOne
    @JoinColumn(
            name = "inventory_item_id",
            referencedColumnName = "id"
    )
    private InventoryItem inventoryItem;

    @Column(
            name = "amount_of_item"
    )
    private int amountOfItem;

}
