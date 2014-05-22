package ganja.component.event

import java.util.concurrent.Callable

class Dispatcher implements DispatcherInterface {

    Map<String,List> listeners = [:]

    @Override
    DispatcherInterface addListener(String eventName, Callable listener, Integer priority = 0) {

        if( ! listeners[eventName])
            listeners[eventName] = []

        listeners[eventName] << [ priority: priority, listener: listener ]

        this
    }

    @Override
    List getListeners(String eventName) {

        listeners[eventName]?.sort({ - it.priority })*.listener
    }

    @Override
    Map getListeners() {

        Map output = [:]

        for(def entry in listeners)
            output[entry.key] = getListeners(entry.key)

        output
    }

    @Override
    Boolean hasListeners(String eventName) {

        listeners[eventName]?.size() as Boolean
    }

    @Override
    void addSubscriber(SubscriberInterface subscriber) {

        for(event in subscriber.getSubscriberEvents()) {

            addListener(event.key, subscriber[event.value.getAt(0)], event.value.getAt(1))
        }
    }

    @Override
    void removeListener(String eventName, Callable listener) {

    }

    @Override
    void removeSubscriber(SubscriberInterface subscriber) {

    }

    @Override
    EventInterface dispatch(String eventName, EventInterface event = null) {

        if( ! event) event = new Event()

        event.with {
            setName(eventName)
            setDispatcher(this)
        }

        if( ! hasListeners(eventName)) {

            return event
        }

        for(def listener in getListeners(eventName)) {

            listener(event)

            if(event.isPropagationStopped()) {

                break
            }
        }

        event
    }
}
