package example.customer;

import example.StatusChangeEvent;
import example.shipping.Shipping;
import lombok.Getter;
import org.jmolecules.ddd.types.Identifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Customer {

    private @Getter Customer.CustomerIdentifier id = new Customer.CustomerIdentifier(UUID.randomUUID());
    private Shipping shipping;

    public static record CustomerIdentifier(UUID id) implements Identifier {}


    public String getCustomerInfo(){
        return this.id.toString() + getShipping().toString();
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    @EventListener
    public void handleStatusChangeEvent(StatusChangeEvent event) {
        if ("active".equals(event.getNewState())){
            if ("orderMangement".equals(event.getDomain())) {
                System.out.println(event.toString() + "\n\nEvent Listener from Customer\n\n");
            }
        }
    }
}