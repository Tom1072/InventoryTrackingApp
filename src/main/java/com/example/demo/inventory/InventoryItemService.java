package com.example.demo.inventory;

import com.example.demo.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryItemService {

    InventoryItemRepository repository;

    @Autowired
    public InventoryItemService(InventoryItemRepository repository) {
        this.repository = repository;
    }

    public List<InventoryItem> getInventoryItems() {
        return repository.findAll();
    }

    public void addInventoryItem(InventoryItem inventoryItem) {
        if (inventoryItem.getItemName() == null)
            throw new BadRequestException("Missing itemName");

        if (repository.findByItemName(inventoryItem.getItemName()).isPresent())
            throw new BadRequestException(String.format("Item with name %s already exist", inventoryItem.getItemName()));

        repository.save(inventoryItem);
    }

    public void deleteInventoryItem(Long itemId) {
        if (!repository.existsById(itemId))
            throw new BadRequestException(String.format("Item with id %d doesn't exist", itemId));
        repository.deleteById(itemId);
    }

    @Transactional
    public void updateInventoryItem(Long itemId, String itemName, Integer unitInStock, Double unitPrice) {
        Optional<InventoryItem> inventoryItem = repository.findById(itemId);

        if (inventoryItem.isEmpty())
            throw new BadRequestException(String.format("Item with id %d doesn't exist", itemId));

        if (itemName != null && itemName.length() > 0) {
            if (repository.findByItemName(itemName).isPresent())
                throw new BadRequestException(String.format("Item with name %s already existed", itemName));
            else
                inventoryItem.get().setItemName(itemName);
        }

        if (unitInStock != null) {
            if (unitInStock < 0)
                throw new BadRequestException("Unit in stock can not be negative");
            else
                inventoryItem.get().setUnitInStock(unitInStock);
        }

        if (unitPrice != null) {
            if (unitPrice < 0)
                throw new BadRequestException("Price cannot be negative");
            else
                inventoryItem.get().setUnitPrice(unitPrice);
        }
    }
}
