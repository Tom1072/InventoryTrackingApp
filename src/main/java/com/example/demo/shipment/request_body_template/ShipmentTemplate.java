package com.example.demo.shipment.request_body_template;

import lombok.Data;

import java.util.List;

@Data
public class ShipmentTemplate {
    private String destination;
    private List<ShipmentInventoryItemTemplate> shipmentItems;
}

