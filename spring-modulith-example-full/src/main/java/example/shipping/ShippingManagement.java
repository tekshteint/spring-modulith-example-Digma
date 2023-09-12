package example.shipping;

import example.customer.Customer;
import example.order.Order;
import example.shipping.internal.ShippingInternal;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import example.order.OrderManagement;

@Service
@RequiredArgsConstructor
public class ShippingManagement {

    private final @NonNull ApplicationEventPublisher events;
    private final @NonNull ShippingInternal dependency;
    private final OrderManagement orderManagement;

    @Transactional
    @Async
    public void complete(Shipping shipping){
        events.publishEvent(new ShippingCompleted(shipping.getId()));
        System.out.println("\n\nAsync Event Published from Shipping Management\n\n");
        System.out.println(getCustomerInfo(shipping));
    }

    public String getCustomerInfo(Shipping shipping){
        Customer customer = new Customer();
        customer.setShipping(shipping);
        return customer.getCustomerInfo();
    }


}
