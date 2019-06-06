package shop

case class Checkout(shoppingBasket: Seq[SKU]) {

def specialPrice(item: SKU): (Int, Int) = item match {
  case A => (3, 130)
  case B => (2, 45)
  case _ => (1, item.unitPrice)
  }

}

object Checkout extends App {
  println("Checkout")
}
