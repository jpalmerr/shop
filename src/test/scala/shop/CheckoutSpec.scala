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

  "Special Price" should {
    "return a quantity and a price" in {
      myCheckout.specialPrice(A) should be(3, 130)
      myCheckout.specialPrice(B) should be(2, 45)
      myCheckout.specialPrice(C) should be(1, 20)
      myCheckout.specialPrice(D) should be(1, 15)
    }
  }
}
