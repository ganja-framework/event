package ganja.component.event

class Subscriber implements SubscriberInterface {

    @Override
    Map getSubscriberEvents() {

        [ foo: [ 'onFoo', 5 ]]
    }

    Closure onFoo = {

    }
}
