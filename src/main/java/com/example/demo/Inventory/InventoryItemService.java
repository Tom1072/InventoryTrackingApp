package com.example.demo.Inventory;

import com.example.demo.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new BadRequestException("itemName already exist");

        repository.save(inventoryItem);
    }
}
