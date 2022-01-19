package com.example.demo;

import com.example.demo.inventory.InventoryItem;
import com.example.demo.inventory.InventoryItemRepository;
import com.example.demo.shipment.Shipment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Add InventoryItem to the inventory Table in postgres
 */
@Configuration
public class DatabaseConfig {

    @Bean
    CommandLineRunner commandLineRunner(InventoryItemRepository repository) {
        return args -> {
            List<InventoryItem> inventoryItems = List.of(
                new InventoryItem("butter", 10, 20.0),
                new InventoryItem("egg", 20, 20.0),
                new InventoryItem("cheese", 30, 2.0),
                new InventoryItem("milk bag", 30, 5.99)
            );
            repository.saveAll(inventoryItems);

//            List<Shipment> shipments = List.of(
//                new Shipment(
//                    List.of(
//                        inventoryItems.get(0),
//                        inventoryItems.get(1)),
//                    "Ottawa"
//                ),
//                new Shipment(
//                        List.of(
//                                inventoryItems.get(1),
//                                inventoryItems.get(2)),
//                        "Toronto"
//
//                )
//            );
        };

    }

}
