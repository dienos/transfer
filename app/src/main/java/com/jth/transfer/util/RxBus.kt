package com.jth.transfer.util

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

object RxBus {

    private val bus = BehaviorSubject.create<Any>()

    fun send(o: Any) {
        bus.onNext(o)
    }

    fun <T> listen(eventType: Class<T>): Observable<T> = bus.ofType(eventType)
}