package shop

case class Checkout(shoppingBasket: Seq[SKU])

object Checkout extends App {
  println("Checkout")
}
