package ganja.component.event

class Event implements EventInterface {

    String name
    DispatcherInterface dispatcher
    Boolean propagationStopped = false

    def subject

    @Override
    Boolean isPropagationStopped() {

        propagationStopped
    }

    @Override
    void stopPropagation() {

        propagationStopped = true
    }
}
