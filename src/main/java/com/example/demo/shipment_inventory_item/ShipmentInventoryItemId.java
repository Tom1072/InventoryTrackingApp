package com.example.demo.shipment_inventory_item;

import lombok.Data;

import java.io.Serializable;

/**
 * Primary key class for ShipmentInventory association class
 */
@Data
public class ShipmentInventoryItemId implements Serializable {
    private Long inventoryItem;
    private Long shipment;
}
