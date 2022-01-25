package pl.allegro.jug.silesia


object TypeClasses {
  val either: Either[List[Int], Boolean] = ???

  /*
  * 0. Equality type class usage
  *    - Pair equals
  */
}

object HigherKindedTypes {

  /*
  * 0. Definition
  *    - Set of rules
  *    - Ad-hoc polymorphism
  */

  trait Collection[T[_]] {
    def wrap[A](a: A): T[A]

    def first[B](b: T[B]): B

    def map[C, D](c: T[C])(f: C => D): T[D]
  }

  val collection: Collection[List] = ???
}
