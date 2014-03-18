package ganja.component.event

import spock.lang.Specification
import spock.lang.MockingApi

import java.util.concurrent.Callable

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
        def listener = Mock(ListenerInterface)

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
        def subscriber = GroovyMock(SubscriberInterface) {
            getSubscriberEvents() >> [ foo: [ 'onFoo', 5 ]]
            getAt('onFoo') >> {}
        }

        when:
        dispatcher.addSubscriber(subscriber)

        then:
        dispatcher.getListeners() != [:]
    }

    void "it returns listeners sorted by priority"() {

        given:
        def dispatcher = new Dispatcher()

        def listener1 = Mock(ListenerInterface)
        def listener2 = Mock(ListenerInterface)
        def listener3 = Mock(ListenerInterface)
        def listener4 = Mock(ListenerInterface)
        def listener5 = Mock(ListenerInterface)
        def listener6 = Mock(ListenerInterface)

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

    void "it can dispatch event to listeners"() {

        given:
        def listener = GroovyMock(ListenerInterface)
        def event = Mock(EventInterface)
        def dispatcher = new Dispatcher()
        dispatcher.addListener('foo', listener)

        when:
        dispatcher.dispatch('foo', event)

        then:
        1 * listener(event)
    }
}