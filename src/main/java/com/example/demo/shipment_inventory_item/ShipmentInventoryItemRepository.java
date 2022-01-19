package com.example.demo.shipment_inventory_item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentInventoryItemRepository extends JpaRepository<ShipmentInventoryItem, Long> {
}
