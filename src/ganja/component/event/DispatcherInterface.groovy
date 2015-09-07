package ganja.component.event

import java.util.concurrent.Callable

public interface DispatcherInterface {

    EventInterface dispatch(String eventName, EventInterface event)

    DispatcherInterface addListener(String eventName, Callable listener, Integer priority)

    void addSubscriber(SubscriberInterface subscriber)

    void removeListener(String eventName, Callable listener)

    void removeSubscriber(SubscriberInterface subscriber)

    List getListeners(String eventName)

    Map getListeners()

    Boolean hasListeners(String eventName)
}