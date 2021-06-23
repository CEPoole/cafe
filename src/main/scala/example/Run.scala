package example

import java.time.LocalTime


object Run extends Calculate with App {
  println("\n\n\nThe cheapest courier is:\n")
  println(rankDrivers()(LocalTime.now.plusHours(1), distance = 2, chilled = false)
    .zipWithIndex
    .foreach{ case (c, i) => println(s"Number ${i+1}: ${c.name} with price ${c.cost(2)}, suggested tip is £${tip(2)}")}
  )
}

trait Calculate {

  final val couriers = List(Bobby, Martin, Geoff)

  def retrieveBest()(implicit now: LocalTime, distance: Int, chilled: Boolean): Courier = {
    couriers
      .filter(_.canDeliver)
      .minBy(_.cost)
  }

  def rankDrivers()(implicit now: LocalTime, distance: Int, chilled: Boolean): List[Courier] = {
    couriers
      .filter(_.canDeliver)
      .sortBy(_.rankCost)
  }

  def tip(distance: Int): Double = {
    val (min, max) = 1 -> 3
    val money = 0.75 * distance

    if(money <= min) min else if (money >= max) max else money
  }

}


