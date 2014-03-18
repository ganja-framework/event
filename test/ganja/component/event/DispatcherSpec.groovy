package ganja.component.event

import spock.lang.Specification

class DispatcherSpec extends Specification {

    void "it is initializable"() {

        when:
        def dispatcher = new Dispatcher()

        then:
        dispatcher instanceof Dispatcher
        dispatcher.getListeners() == [:]
        ! dispatcher.hasListeners('any')
    }

    void "it can accept listener"() {

        given:
        def dispatcher = new Dispatcher()
        def listener = new Listener()

        when:
        dispatcher.addListener('event.name', listener)

        then:
        dispatcher.getListeners('event.name') == [ listener ]
        dispatcher.getListeners() == [ 'event.name': [ listener ]]
        dispatcher.hasListeners('event.name')
    }

    void "it can accept subscriber"() {

        given:
        def dispatcher = new Dispatcher()
        def subscriber = new Subscriber()

        when:
        dispatcher.addSubscriber(subscriber)

        then:
        dispatcher.getListeners() != [:]
    }

    void "it returns listeners sorted by priority"() {

        given:
        def dispatcher = new Dispatcher()

        def listener1 = new Listener()
        def listener2 = new Listener()
        def listener3 = new Listener()
        def listener4 = new Listener()
        def listener5 = new Listener()
        def listener6 = new Listener()

        when:
        dispatcher.addListener('foo', listener1, -10)
        dispatcher.addListener('foo', listener2)
        dispatcher.addListener('foo', listener3, 10)

        dispatcher.addListener('bar', listener4, -10)
        dispatcher.addListener('bar', listener5)
        dispatcher.addListener('bar', listener6, 10)

        then:
        dispatcher.getListeners('foo') == [ listener3, listener2, listener1 ]
        dispatcher.getListeners('bar') == [ listener6, listener5, listener4 ]

        dispatcher.getListeners() == [
                foo: [ listener3, listener2, listener1 ],
                bar: [ listener6, listener5, listener4 ]
            ]
    }
}