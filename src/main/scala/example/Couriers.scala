package example

import java.time.LocalTime


sealed abstract class Courier(workStart: LocalTime,
                              workEnd: LocalTime,
                              distanceInMiles: Int,
                              canChill: Boolean,
                              charge: Double) {

  private def isWorking(implicit now: LocalTime): Boolean  = workStart.isBefore(now) && workEnd.isAfter(now)
  private def inRange(implicit distance: Int): Boolean     = distance <= distanceInMiles
  private def required(implicit chilled: Boolean): Boolean = canChill >= chilled

  private def myNameIsGeoff: Boolean = name == "Geoff"

  def canDeliver(implicit now: LocalTime, distance: Int, chilled: Boolean): Boolean = isWorking && inRange && required
  def rankCost(implicit distance: Int): Double = if(myNameIsGeoff) cost - 1 else cost

  def cost(implicit distance: Int): Double = distance * charge
  def name: String = this.getClass.getSimpleName.dropRight(1)
}


import ReadableCode._

object Bobby  extends Courier("09:00:00".oClock, "13:00:00".oClock, distanceInMiles = 5, Refrigerated,   1 pound 75.pence)
object Martin extends Courier("09:00:00".oClock, "17:00:00".oClock, distanceInMiles = 3, Unrefrigerated, 1 pound 50.pence)
object Geoff  extends Courier("10:00:00".oClock, "16:00:00".oClock, distanceInMiles = 4, Refrigerated,   2 pounds 0.pence)
