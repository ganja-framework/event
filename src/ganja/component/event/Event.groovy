package ganja.component.event

class Event implements EventInterface {

    String name
    DispatcherInterface dispatcher
    Boolean propagationStopped = false

    def subject

    Boolean isPropagationStopped() {

        propagationStopped
    }

    void stopPropagation() {

        propagationStopped = true
    }
}
