package pl.allegro.jug.silesia

import arrow.fx.IO
import arrow.fx.extensions.fx
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.concurrent.TimeUnit
import arrow.fx.typeclasses.Duration as ArrowDuration

object Coroutines {

    /*
    * 0. Coroutines
    *    - ???
    */

    suspend fun callExternalApi(): String {
        delay(1000)
        println("Abc")
        return "Abc"
    }

    fun showResult(result: String): Unit =
        println("Result: $result")

    suspend fun main() = coroutineScope {
        val result = callExternalApi()
        showResult(callExternalApi())
        showResult(callExternalApi())
    }
}

object ProjectReactor {

/*
* 1. Project Reactor
*    - ???
*/

    fun callExternalApi(): Mono<String> =
        Mono.just("Abc")
            .delayElement(Duration.ofMillis(1000))
            .doOnNext { println("Abc") }

    fun showResult(result: String): Unit =
        println("Result: $result")

    fun main() {
        callExternalApi()
            .map { result ->
                showResult(result)
                showResult(result)
            }
            .block()
    }
}

object RxJava {

    /*
    * 2. RxJava
    *    - ???
    */

    fun callExternalApi(): Single<String> =
        Single.just("Abs")
            .delay(1000, TimeUnit.MILLISECONDS)
            .doOnSuccess { println("Abc") }

    fun showResult(result: Single<String>): Single<*> =
        result.doOnSuccess { println("Result: $it") }

    fun main() {
        val result = callExternalApi()
        Single.merge(
            showResult(result),
            showResult(result)
        ).blockingSubscribe()
    }
}

//object ArrowKt {

/*
* 3. ArrowKt
*    - ???
*/

fun callExternalApi(): IO<String> =
    IO.sleep(ArrowDuration(1000, TimeUnit.MILLISECONDS))
        .followedBy(IO.effect { println("Abc") })
        .followedBy(IO.just("Abc"))

fun showResult(result: String): IO<Unit> =
    IO.effect { println("Result: $result") }

fun main(): Unit {

//    val result = callExternalApi()
//    IO.racePair(
//        Dispatchers.Default,
//        showResult(result),
//        showResult(result)
//    ).unsafeRunSync()

    IO.fx {
        val result = !callExternalApi()
        !showResult(result)
        !showResult(result)
    }.unsafeRunSync()
}
//}
