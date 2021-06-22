package example

import java.time.LocalTime


object Run extends Calculate with App {
  println("\n\n\nThe cheapest courier is:\n")
  println(retrieveBest()(LocalTime.now.minusHours(3), distance = 2, chilled = false).name)
}

trait Calculate {

  final val couriers = Set(Bobby, Martin, Geoff)

  def retrieveBest()(implicit now: LocalTime, distance: Int, chilled: Boolean): Courier = {
    couriers
      .filter(_.isWorking)
      .filter(_.required)
      .filter(_.inRange)
      .minBy(_.cost)
  }

}


