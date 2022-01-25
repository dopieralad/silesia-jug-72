package pl.allegro.jug.silesia

import pl.allegro.jug.silesia.Reasoning.Option
import java.math.BigDecimal

object AlgebraicDataTypes {
    /*
    * 0. Basic ADTs
    *    - Option
    *    - Either
    *    - Try
    *    - List
    *    - Tree
    *    - ...
    */

    /*
    * 1. Product and sum types
    */
    interface Either<E, A>
    data class Left<E>(val left: E) : Either<E, Nothing>
    data class Right<A>(val right: A) : Either<Nothing, A>

    val left: Left<List<*>> = TODO()
    val either: Either<Object, Object> = TODO()

    /*
    * 2. More specific ADTs
    *    - Type-safety
    *    - Rich domain modelling
    */
    data class Offer(
        val id: String,
        val status: String,
        val price: Option<BigDecimal>,
        val initialPrice: Option<BigDecimal>,
        val minimalPrice: Option<BigDecimal>
    )

    sealed interface BaseOffer {
        val id: Id
        val status: Status
    }

    data class BuyNowOffer private constructor(
        override val id: Id,
        override val status: Status,
        val price: BigDecimal
    ) : BaseOffer


    fun a(auction: AuctionOffer): Unit = TODO()

    @JvmInline
    value class Id(val value:String)

    @JvmInline
    value class Status(val value:String)

    /*
    * 3. Smart constructors
    */
    data class AuctionOffer private constructor(
        override val id: Id,
        override val status: Status,
        val initialPrice: BigDecimal,
        val minimalPrice: BigDecimal
    ) : BaseOffer {
        companion object {
            fun create(
                 id: Id,
                status: Status,
                initialPrice: BigDecimal,
                minimalPrice: BigDecimal
            ): Either<Reasoning.ValidationError, AuctionOffer> = TODO()
        }
    }
}

object Variance {
    /*
    * 0. Invariance
    */
    data class Invariant<T>(val value: T)

    val invariantSubclass: Invariant<Int> = TODO()

    /*
    * 1. Covariance
    */
    data class Covariant<out T>(val value: T)

    val covariantSubclass: Covariant<Int> = TODO()
    val covariantSuperclass: Covariant<Number> = covariantSubclass

    /*
    * 2. Contravariance
    */
}

object TypeClasses {

    /*
    * 0. Basic type class
    */

    interface Eq<A> {
        fun areEqual(a: A, b: A): Boolean
    }

//    val intEq: Eq<Iterable<*>> = object: Eq<Int> {
//        override fun areEqual(a: Int, b: Int): Boolean {
//            TODO("Not yet implemented")
//        }
//    }
}

object HigherKindedTypes {
    /*
    * 0. Do not work in Kotlin out-of-the-box...
    */

/*
    interface Collection1<T<*>> {
        fun <A> wrap(a: A): T<A>
        fun <B> first(b: T<B>): B
    }

    interface Collection2<T<?>> {
        fun <A> wrap(a: A): T<A>
        fun <B> first(b: T<B>): B
    }

    interface Collection3<T> {
        fun <A> wrap(a: A): T<A>
        fun <B> first(b: T<B>): B
    }
*/
}
