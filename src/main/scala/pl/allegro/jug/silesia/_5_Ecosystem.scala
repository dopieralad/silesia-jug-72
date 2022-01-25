package pl.allegro.jug.silesia

import monix.eval.Task

import scala.concurrent.duration.DurationInt

object ADTs {

  /*
  * 0. Plain Scala
  */

  /*
  * 1. Cats
  */
}

object Monix extends App {
  /*
  * 2. Monix
  *    - ???
  */

  import monix.execution.Scheduler.Implicits.global

  def callExternalApi(): Task[String] =
    Task.pure("Abc")
      .delayResult(1000.millis)
      .tapEval { _ => Task.eval(println("Abc")) }

  def showResult(result: String): Task[_] =
    Task.eval(println(s"Result: $result"))

  def showResult(result: Task[String]): Task[_] =
    result.tapEval { string => Task.eval(println(s"Result: $string")) }

//  val result = callExternalApi()
//  Task.parZip2(
//    showResult(result),
//    showResult(result)
//  ).runSyncUnsafe()

    {
      callExternalApi()
        .flatMap(result =>
          Task.parZip2(
            showResult(result),
            showResult(result)
          )
            .map(_ => ())
        )
    }.runSyncUnsafe()
}
