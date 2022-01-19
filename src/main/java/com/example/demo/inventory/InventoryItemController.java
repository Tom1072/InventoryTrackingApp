package com.example.demo.inventory;

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

    @PutMapping(path = "{itemId}")
    public void updateInventoryItem(@PathVariable("itemId") Long itemId,
                                    @RequestParam(required = false) String itemName,
                                    @RequestParam(required = false) Integer unitInStock,
                                    @RequestParam(required = false) Double unitPrice) {
        inventoryItemService.updateInventoryItem(itemId, itemName, unitInStock, unitPrice);
    }

    @DeleteMapping(path = "{itemId}")
    public void deleteInventoryItem(@PathVariable("itemId") Long itemId) {
        inventoryItemService.deleteInventoryItem(itemId);
    }

}
