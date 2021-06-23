package example

import java.time.LocalTime

object ReadableCode {
  val Refrigerated = true
  val Unrefrigerated = false

  implicit class RichTime(time: String) {
    def oClock: LocalTime = LocalTime.parse(time)
  }

  implicit class RichMoney(amount: Int) {
    def pound(p: Int): Double = s"$amount.$p".toDouble
    def pounds(p: Int): Double = pound(p)
    def pence: Int = amount
  }
}
