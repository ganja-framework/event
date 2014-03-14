package ganja.component.event

import spock.lang.Specification

class DispatcherSpec extends Specification {

    void "it is initializable"() {

        when:
        def dispatcher = new Dispatcher()

        then:

        dispatcher instanceof Dispatcher

    }
}