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

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    public void createShipment(ShipmentTemplate shipmentTemplate) {
        if (shipmentTemplate.getDestination() == null || shipmentTemplate.getDestination().length() == 0)
            throw new BadRequestException("Missing or Invalid shipment destination");

        shipmentTemplate.getShipmentItems().forEach((item) -> {
            Optional<InventoryItem> itemInRepo = inventoryItemRepository.findById(item.getItemId());
            if (itemInRepo.isEmpty())
                throw new BadRequestException(String.format("Item with id %d doesn't exist", item.getItemId()));
            if (item.getAmountOfItem() <= 0)
                throw new BadRequestException("Missing or Invalid amountOfItem");
            if (itemInRepo.get().getUnitInStock() - item.getAmountOfItem() < 0)
                return;
        });

        // Create new Shipment row
        Shipment shipment = new Shipment();
        shipment.setDestination(shipmentTemplate.getDestination());
        shipmentRepository.save(shipment);

        shipmentTemplate.getShipmentItems().forEach((item) -> {
            // Create new ShipmentInventoryItem row
            ShipmentInventoryItem shipmentInventoryItem = new ShipmentInventoryItem();
            InventoryItem itemToShip = inventoryItemRepository.getById(item.getItemId());

            shipmentInventoryItem.setInventoryItem(itemToShip);
            shipmentInventoryItem.setAmountOfItem(item.getAmountOfItem());
            shipmentInventoryItem.setShipment(shipment);

            shipmentInventoryItemRepository.save(shipmentInventoryItem);

            // Deduct inventory count of each item
            itemToShip.setUnitInStock(itemToShip.getUnitInStock() - item.getAmountOfItem());
        });

    }
}
