package com.example.demo.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/inventory")
public class InventoryItemController {

    @Autowired
    private final InventoryItemService inventoryItemService;

    public InventoryItemController(InventoryItemService inventoryItemService) {
        this.inventoryItemService = inventoryItemService;
    }

    @GetMapping
    public List<InventoryItem> getInventoryItems() {
        return inventoryItemService.getInventoryItems();
    }

    @PostMapping
    public void addInventoryItem(@RequestBody InventoryItem inventoryItem) {
        inventoryItemService.addInventoryItem(inventoryItem);
    }

}
