package example.controllers;

import example.StatusChangeEvent;
import example.shipping.Shipping;
import example.shipping.ShippingManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Tom Ekshtein
 */

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    private final ShippingManagement ShippingManagement;

    public ShippingController(ShippingManagement ShippingManagement) {
        this.ShippingManagement = ShippingManagement;
    }

    @GetMapping("/complete")
    public String Ship() {
        Shipping shipping = new Shipping();

        ShippingManagement.complete(shipping);

        return "Shipping completed with ID: " + shipping.getId();
    }
}
