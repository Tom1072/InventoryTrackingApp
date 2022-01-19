package com.example.demo.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    @Query("SELECT item FROM InventoryItem item WHERE item.itemName = ?1")
    Optional<InventoryItem> findByItemName(String itemName);
}
