package ganja.component.event

import java.util.concurrent.Callable

class Dispatcher {

    Map<String,List> listeners = [:]

    void addListener(String eventName, Callable listener, Integer priority = 0) {

        if( ! listeners[eventName])
            listeners[eventName] = []

        listeners[eventName].add([ priority: priority, listener: listener ])
    }

    List getListeners(String eventName) {

        listeners[eventName]?.sort({ - it.priority })*.listener
    }

    Map getListeners() {

        Map output = [:]

        for(def entry in listeners)
            output[entry.key] = getListeners(entry.key)

        output
    }

    boolean hasListeners(String eventName) {

        listeners[eventName]?.size() as Boolean
    }

    void addSubscriber(SubscriberInterface subscriber) {

        for(event in subscriber.getSubscriberEvents()) {

            addListener(event.key, subscriber[event.value.getAt(0)], event.value.getAt(1))
        }
    }
}
