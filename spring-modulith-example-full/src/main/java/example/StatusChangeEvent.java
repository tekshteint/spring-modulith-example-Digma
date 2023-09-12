package example;
import org.springframework.context.ApplicationEvent;

public class StatusChangeEvent extends ApplicationEvent {
    private final String domain;
    private final String newState;

    public StatusChangeEvent(Object source, String domain, String newState) {
        super(source);
        this.domain = domain;
        this.newState = newState;
    }

    public String getDomain() {
        return domain;
    }

    public String getNewState() {
        return newState;
    }

    @Override
    public String toString() {
        return "StatusChangeEvent{" +
                "domain='" + domain + '\'' +
                ", newState='" + newState + '\'' +
                '}';
    }
}
