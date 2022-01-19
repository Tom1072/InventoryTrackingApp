package com.example.demo.shipment;

import com.example.demo.exception.BadRequestException;
import com.example.demo.inventory.InventoryItem;
import com.example.demo.inventory.InventoryItemRepository;
import com.example.demo.shipment.request_body_template.ShipmentTemplate;
import com.example.demo.shipment_inventory_item.ShipmentInventoryItem;
import com.example.demo.shipment_inventory_item.ShipmentInventoryItemRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    @Autowired
    private final ShipmentRepository shipmentRepository;

    @Autowired
    private final InventoryItemRepository inventoryItemRepository;

    @Autowired
    private final ShipmentInventoryItemRepository shipmentInventoryItemRepository;

    public ShipmentService(ShipmentRepository shipmentRepository,
                           InventoryItemRepository inventoryItemRepository,
                           ShipmentInventoryItemRepository shipmentInventoryItemRepository) {
        this.shipmentRepository = shipmentRepository;
        this.inventoryItemRepository = inventoryItemRepository;
        this.shipmentInventoryItemRepository = shipmentInventoryItemRepository;
    }

    public List<Shipment> getShipments() {
        return shipmentRepository.findAll();
    }

    public void createShipment(ShipmentTemplate shipmentTemplate) {
        if (shipmentTemplate.getDestination() == null || shipmentTemplate.getDestination().length() == 0)
            throw new BadRequestException("Missing or Invalid shipment destination");

        shipmentTemplate.getShipmentItems().forEach((item) -> {
            if (!inventoryItemRepository.existsById(item.getItemId()))
                throw new BadRequestException(String.format("Item with id %d doesn't exist", item.getItemId()));
            if (item.getAmountOfItem() <= 0)
                throw new BadRequestException("Missing or Invalid amountOfItem");
        });

        Shipment shipment = new Shipment();
        shipment.setDestination(shipmentTemplate.getDestination());
        shipmentRepository.save(shipment);

        shipmentTemplate.getShipmentItems().forEach((item) -> {
            ShipmentInventoryItem shipmentInventoryItem = new ShipmentInventoryItem();

            shipmentInventoryItem.setInventoryItem(inventoryItemRepository.getById(item.getItemId()));
            shipmentInventoryItem.setAmountOfItem(item.getAmountOfItem());
            shipmentInventoryItem.setShipment(shipment);

            shipmentInventoryItemRepository.save(shipmentInventoryItem);
        });


    }
}
