package com.example.demo.shipment.request_body_template;

import lombok.Data;

@Data
public class ShipmentInventoryItemTemplate {
    private Long itemId;
    private int amountOfItem;
}
