package shop

import org.scalatest.{WordSpec, Matchers}

class CheckoutSpec extends WordSpec with Matchers {

  val shoppingBasket = Seq[SKU](A, A, A, A, A, A, A, B, B, B, B, C, C, D)
  val myCheckout = Checkout(shoppingBasket)

  "A Checkout" should {
    "be instantiated with a shopping basket" in {
      myCheckout.shoppingBasket should be(Seq(A, A, A, A, A, A, A, B, B, B, B, C, C, D))
    }

  }
}
