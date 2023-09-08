package example.controllers;

import example.order.Order;
import example.order.OrderManagement;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Tom Ekshtein
 */

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final OrderManagement orderManagement;

    public InventoryController(OrderManagement orderManagement) {
        this.orderManagement = orderManagement;
    }

    @GetMapping("/create")
    public String createOrder() {
        Order order = new Order();

        orderManagement.complete(order);

        return "Order created with ID: " + order.getId();
    }
}
