package shop

class Checkout(val stockKeepingUnit: SKU*) {

  val stockedItems: Seq[String] = stockKeepingUnit.map(_.toString)

  def specialPrice(item: SKU): (Quantity, Pence) = item match {
    case A => (3, 130)
    case B => (2, 45)
    case _ => (1, item.unitPrice)
  }

  import scala.math.Integral.Implicits._

  private def calculateItemCost(item: SKU, quantity: Int)(offer: (Quantity, Pence)): Pence = {
    val (quotient, remainder) = quantity /% offer._1
    val totalItemCost = quotient * offer._2 + remainder * item.unitPrice
    totalItemCost
  }

  def parse(item: String): Either[(String, Boolean), Boolean] = {
    if (stockedItems.contains(item)) {
      Right(true)
    } else {
      Left((s"Unknown item in shopping basket: $item", false))
    }
  }

  def isUserInputValid(args: Array[String]): Boolean = {
    val results = args.map(arg => parse(arg))
    val listLefts = results.filter(_ != Right(true))
    listLefts.isEmpty
//    results.exists(!_.isLeft)
  }

  def calculateTotalCost(items: Seq[SKU]): BigDecimal = {

    val numberOfEachItem = items.groupBy(identity).mapValues(_.size)
    val itemsCost = numberOfEachItem.map(item => calculateItemCost(item._1, item._2)(specialPrice(item._1)))
    val totalCost = itemsCost.sum
    val totalCostPounds = BigDecimal(totalCost.toDouble / 100).setScale(2)
    totalCostPounds
  }

}


object Checkout extends Checkout(
  // items stocked in shop
  A, B, C, D) with App {

  println("Checkout ...")

  lazy val argsToSKUs: Map[String, SKU] = stockKeepingUnit.map(sku => (sku.toString, sku)).toMap

  if (isUserInputValid(args)) {
    val shoppingBasket: Seq[SKU] = args.map(item => argsToSKUs(item)).toSeq
    val cost = calculateTotalCost(shoppingBasket)

    println(s"total cost of items in shopping basket: £$cost")
  } else {
    val errorList = args.map(arg => parse(arg)).toList
    val listOfLefts = errorList.filter(_ != Right(true))
    listOfLefts.foreach(println) // close as i can get to error message
  }

}
