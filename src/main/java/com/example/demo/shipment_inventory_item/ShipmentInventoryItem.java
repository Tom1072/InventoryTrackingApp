package com.example.demo.shipment_inventory_item;

import com.example.demo.inventory.InventoryItem;
import com.example.demo.shipment.Shipment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * Association class between Shipment and InventoryItem
 */
@Entity
@Table(name = "shipment_inventory")
@IdClass(ShipmentInventoryItemId.class)
@Data
public class ShipmentInventoryItem {
    @Id
    @ManyToOne
    @JoinColumn(
            name = "shipment_id",
            referencedColumnName = "id"
    )
    @JsonIgnore
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

    @Override
    public String toString() {
        String s = "ShipmentInventoryItem{";

        if (shipment != null)
            s += "shipment.id=" + shipment.getId();
        if (inventoryItem != null)
            s += ", inventoryItem.id=" + inventoryItem.getId();

        s += ", amountOfItem=" + amountOfItem + '}';

        return s;
    }
}
