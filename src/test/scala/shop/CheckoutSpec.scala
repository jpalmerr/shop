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

//  "Calculate Item Cost" should {
//    "take an item and a special price, return the cost" in {
//      myCheckout.calculateItemCost(A, 7)(3, 130) should be(310)
//      myCheckout.calculateItemCost(B, 1)(2, 45) should be(30)
//    }
//  }

  "Calculate Total Cost" should {
    "return an accurate value converted to pounds" in {
      myCheckout.calculateTotalCost should be(4.55)
      myCheckout.calculateTotalCost shouldBe a[BigDecimal]
    }
  }

}
