package example.shipping;

import example.StatusChangeEvent;
import lombok.Getter;
import org.jmolecules.ddd.types.Identifier;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Shipping {


    private @Getter ShippingIdent id = new ShippingIdent(UUID.randomUUID());

    public static record ShippingIdent(UUID id) implements Identifier {}
    @EventListener
    @Async
    public void handleStatusChangeEvent(StatusChangeEvent event) {
        if ("inactive".equals(event.getNewState())){
            if ("orderMangement".equals(event.getDomain())) {
                System.out.println(event.toString() + "\n\nAsync Event Listener from Shipping\n\n");
            }
        }
    }
}
