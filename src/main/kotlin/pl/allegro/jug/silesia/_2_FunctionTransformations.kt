package pl.allegro.jug.silesia

object HigherOrderFunctions {

    /*
    * 0. Taking functions as arguments
    */
    fun <A, B> map(list: List<A>, f: (A) -> B): List<B> = TODO()

    /*
    * 1. Returning functions as values
    */
    fun <A> wrap(value: A): () -> A =
        { value }

    /*
    * 2. Existing examples
    *    - List
    *    - Thread
    *    - ...
    */
    val a = listOf(1,2,3)

    /*
    * 3. Loops
    */
    fun factorialIterative(number: Int): Int {
        var accumulator: Int = 1
        for (current in 1..number) {
            accumulator *= current
        }
        return accumulator
    }

    fun factorialRecursive(number: Int): Int {
        tailrec fun go(current: Int, accumulator: Int): Int =
            if (current <= 0) accumulator
            else go(current - 1, current * accumulator)
        return go(current = number, accumulator = 1)
    }
}

object PartialApplication {

    /*
    * 0. Partial application
    */
    fun <A, B, C> partially(a: A, f: (A, B) -> C): (B) -> C =
        { b: B -> f(a, b)}

    infix fun <A, B, C> ((A, B) -> C).provide(a: A): (B) -> C = TODO()

    val function: (String, Int) -> String = { string: String, times: Int -> string.repeat(times) }
    val firstPartialFunction: (Int) -> String = partially("Hello!", function)
    val secondPartialFunction: (Int) -> String = function.provide("Hello!")

    /*
    * 1. Currying
    */
    fun <A, B, C> ((A, B) -> C).curry(): (A) -> (B) -> C = TODO()

    val curriedFunction: (String) -> (Int) -> String = function.curry()
}

object FunctionComposition {

    /*
    * 0. Function composition
    */
    infix fun <A, B, C> ((A) -> B).andThen(f: (B) -> C): (A) -> C =
        {a:A -> f(this(a)) }

}
