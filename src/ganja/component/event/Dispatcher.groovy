package ganja.component.event

class Dispatcher {

    Map<String,List> listeners = [:]

    void addListener(String eventName, Listener listener) {

        if(listeners[eventName])
            listeners[eventName].add(listener)
        else
            listeners[eventName] = [ listener ]
    }

    List getListeners(String eventName) {

        listeners[eventName]
    }
}
