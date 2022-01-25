package pl.allegro.jug.silesia

import pl.allegro.jug.silesia.FunctionalEffects.Lazy

object FunctionalEffects {
    /*
    * 0. Basic effects
    *    - Option
    *    - List
    *    - Try
    *    - Id
    */

    /*
    * 1. Deferred evaluation
    */
    class Lazy<out A>(val unsafeRun: () -> A) {
        fun <B> map(f: (A) -> B): Lazy<B> = Lazy {
            f(unsafeRun())
        }

        fun <B> flatMap(f: (A) -> Lazy<B>): Lazy<B> = Lazy {
            f(unsafeRun()).unsafeRun()
        }

        companion object {
            fun <A> pure(a: A): Lazy<A> =
                Lazy { a }

            fun <A> effect(a: () -> A): Lazy<A> =
                Lazy(a)
        }
    }
}

fun main(): Unit {
    /*
    * 2. Composing effects
    *    - Pure values
    *    - Side-effectful values
    *    - Collections of effects
    */
    val result = Lazy
        .pure(1)
        .map {
            println("Map: $it")
            it + 1
        }
        .flatMap {
            println("Flat map: $it")
            Lazy.pure(it + 2)
        }
        .unsafeRun()
    println("Result: $result")
}
