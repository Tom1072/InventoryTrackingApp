package com.example.demo.Inventory;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryItemService {

//    private InventoryItem inventoryItem;

    public List<InventoryItem> getInventoryItems() {
        return List.of(
                new InventoryItem("butter", 10, 20),
                new InventoryItem("egg", 20, 20)
        );
    }
}
