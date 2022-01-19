package com.example.demo.shipment;

import com.example.demo.shipment_inventory_item.ShipmentInventoryItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shipment")
@Data
public class Shipment {
    @Id
    @SequenceGenerator(name="shipment_seq",
            sequenceName="shipment_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="shipment_seq")
    private Long id;

    @OneToMany(mappedBy = "inventoryItem")
    private List<ShipmentInventoryItem> shipmentInventoryItems;

    @Column(nullable = false)
    private String destination;
}
