package example.shipping;

import org.jmolecules.event.types.DomainEvent;

public record ShippingCompleted(Shipping.ShippingIdent shippingId) implements DomainEvent {
}
