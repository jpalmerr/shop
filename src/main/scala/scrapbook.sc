import shop.{A, B, C, D, SKU}


val a = A.unitPrice
val E = "bug"
val basket = Seq[SKU](A, B, C, D, A, B)
// val basket1 = Seq[SKU](E, A, B, C, D, A, B)
val basket2 = Seq(A, B, C, D, A, B)
val basket3 = Seq(E, A, B, C)

val shoppingBasket = Seq[SKU](A, A, A, A, A, A, A, B, B, B, B, C, C, D) //455

val numberOfEachItem = shoppingBasket.groupBy(identity).mapValues(_.size)

// store the offers
def specialPrice(item: SKU): (Int, Int) = item match {
  case A => (3, 130)
  case B => (2, 45)
  case _ => (1, item.unitPrice) // no special offer
}

// access and calculate

import scala.math.Integral.Implicits._

10 /% 2
11 /% 2
val (quotient, remainder) = 11 /% 2

def calculateItemCost(item: SKU, quantity: Int)(offer: (Int, Int)) :Int = {
  val (quotient, remainder) = quantity /% offer._1
  println(s"quotient: $quotient , remainder: $remainder")
  val totalItemCost = quotient * offer._2 + remainder * item.unitPrice
  totalItemCost
}

calculateItemCost(A, 7)(3, 120) // 290