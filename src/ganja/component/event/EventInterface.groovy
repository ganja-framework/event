package ganja.component.event

public interface EventInterface {

    void setName(String eventName)

    void setDispatcher(DispatcherInterface dispatcher)

    Boolean isPropagationStopped()

    void stopPropagation()

    def getSubject()

    void setSubject(def subject)
}