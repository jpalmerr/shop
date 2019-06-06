package shop

case class Checkout(shoppingBasket: Seq[SKU]) {

  def specialPrice(item: SKU): (Int, Int) = item match {
    case A => (3, 130)
    case B => (2, 45)
    case _ => (1, item.unitPrice)
  }

  import scala.math.Integral.Implicits._

  def calculateItemCost(item: SKU, quantity: Int)(offer: (Int, Int)) :Int = {
    val (quotient, remainder) = quantity /% offer._1
    val totalItemCost = quotient * offer._2 + remainder * item.unitPrice
    totalItemCost
  }

  def calculateTotalCost: BigDecimal = {
    val numberOfEachItem = shoppingBasket.groupBy(identity).mapValues(_.size)

    val itemsCost = numberOfEachItem.map(item => calculateItemCost(item._1, item._2)(specialPrice(item._1)))
    val totalCost = itemsCost.sum
    val totalCostPounds = BigDecimal(totalCost.toDouble / 100).setScale(2)
    totalCostPounds
  }

}

object Checkout extends App {
  println("Checkout")
}
