package pl.allegro.jug.silesia

import java.lang.System.exit
import java.math.BigDecimal
import kotlin.random.Random

object Function {
    /*
     * 0. f: X -> Y
     *
     *         ...
     *      -1 -> 0
     *       0 -> 1
     *       1 -> 2
     *         ...
     */

    // 1. g: Z -> Z
    fun g(int: Int): Int = TODO()

    // 2. h: String -> String

    // 3. i: String -> (String -> String)
    fun i(string: String): (String) -> String = TODO()

    // 4. j: (String, Int) -> String

    // 5. k: VeryComplexObject -> Boolean
}

object ReferentialTransparency {
    /*
    * 0. Side-effects
    *    - Not self-explainable...
    *    - Hard to test!
    *    - How about many tickets?
    */
    interface CreditCard {
        fun charge(price: BigDecimal): Unit
    }

    data class Ticket(val price: BigDecimal = BigDecimal(0))

    fun buyTicket(creditCard: CreditCard): Ticket {
        val ticket = Ticket()
        creditCard.charge(ticket.price)
        return ticket
    }

    /*
     * 1. Substitution model
     *    - Replace the function call with its result
     */
    fun buyTicket(): Unit {
        val creditCard: CreditCard = TODO()
        val ticket = buyTicketFunc()
        println("I've bought a ticket! See? ${Ticket()}")
    }

    /*
    * 2. Functional solution
    *    - Factor the side-effect out of the function
    *    - ???
    */
     data class Charge(val amount: BigDecimal)

    fun buyTicketFunc(): Pair<Ticket, Charge> {
        val ticket = Ticket()
        return ticket to Charge(ticket.price)
    }

    /*
    * 3. Other side-effects
    */
    fun sideEffects() {
        println("123")

        class MutableObject(var name: String, var age: Int)

        fun mutate(mutableObject: MutableObject): Unit {
            mutableObject.name += " suffix"
        }

        System.getProperty("")

        System.setProperty("key", "newValue")

        Random.nextInt()

        Thread({ TODO() }).start()

        fun count(): Int {
            var counter = 1;
            while (counter <= 10) {
                counter += 1
            }
            return counter
        }

        throw RuntimeException()
    }
}

object Totality {
    /*
    * 0. Loss of control
    */
    fun divide(dividend: Double, divisor: Double): Double =
        dividend / divisor

    val ten = divide(dividend = 20.0, divisor = 2.0) // 10
    val exception = divide(dividend = 20.0, divisor = 0.0) // ArithmeticException!

    fun process(): Unit =
        if (TODO()) exit(0)

    /*
    * 1. Functional solution
    *    - Encode exceptional states as data (Option, Either, less general arguments)
    */
}

object Reasoning {
    /*
    * 0. Mathematical reasoning
    */
    fun f(int1: Int, int2: Int, int3: Int): Int = TODO()

    fun g(int: Int): Int = TODO()

    fun h(int1: Int, int2: Int): Int = TODO()

    fun i(int: Int): Int = TODO()

    val gValue = g(1)
    val result = f(gValue, h(i(2), h(gValue, i(2))), i(2))

    /*
    * 1. Local reasoning
    *    - Program consists of smaller independent programs
    *    - Analysis requires knowledge about a smaller part of code
    */
    val anotherResult = f(
        g(1),
        h(
            i(2),
            h(
                g(1),
                i(2)
            )
        ),
        i(2)
    )

    /*
    * 2. Self-explainability
    */
    interface Option<T>
    data class Some<T>(val value: T) : Option<T>
    object None : Option<Nothing>

    fun divide(dividend: Int, divisor: Int): Option<Double> = TODO()

    interface Try<T>
    data class Success<T>(val value: T) : Try<T>
    data class Failure(val exception: Exception) : Try<Nothing>

    fun validateWithTry(string: String): Try<String> = TODO()

    data class ValidationError(val cause: String)

    interface Either<E, A>
    data class Left<E>(val left: E) : Either<E, Nothing>
    data class Right<A>(val right: A) : Either<Nothing, A>

    fun validateWithEither(string: String): Either<ValidationError, String> = TODO()
}
