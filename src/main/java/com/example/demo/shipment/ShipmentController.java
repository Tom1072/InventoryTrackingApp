package com.example.demo.shipment;

import com.example.demo.shipment.request_body_template.ShipmentTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/shipment")
public class ShipmentController {

    @Autowired
    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping
    public List<Shipment> getShipments() {
        return shipmentService.getShipments();

    }

    @PostMapping
    public void createShipment(@RequestBody ShipmentTemplate shipmentTemplate) {
        System.out.println(shipmentTemplate);
        shipmentService.createShipment(shipmentTemplate);

    }
}
