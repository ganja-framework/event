package ganja.component.event

import spock.lang.Specification

class DispatcherSpec extends Specification {

    void "it is initializable"() {

        when:
        def dispatcher = new Dispatcher()

        then:

        dispatcher instanceof Dispatcher
    }

    void "it can accept listener"() {

        given:
        def dispatcher = new Dispatcher()
        def listener = new Listener()

        when:
        dispatcher.addListener('event.name', listener)

        then:
        dispatcher.getListeners('event.name') == [ listener ]
    }
}