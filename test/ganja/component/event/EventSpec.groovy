package ganja.component.event

import spock.lang.Specification

class EventSpec extends Specification {

    void "it is initialisable"() {

        given:
        def event = new Event()

        expect:
        event instanceof Event
    }

    void "it can transport subject"() {

        given:
        def event = new Event()

        when:
        event.subject = "test subject"

        then:
        event.subject == "test subject"

        when:
        event.subject = 5

        then:
        event.subject == 5
    }
}