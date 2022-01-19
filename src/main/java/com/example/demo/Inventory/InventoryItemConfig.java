package com.example.demo.Inventory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Add InventoryItem to the inventory Table in postgres
 */
@Configuration
public class InventoryItemConfig {

    @Bean
    CommandLineRunner commandLineRunner(InventoryItemRepository repository) {
        return args -> {
            repository.saveAll(List.of(
                new InventoryItem("butter", 10, 20),
                new InventoryItem("egg", 20, 20)
            ));
        };

    }

}
