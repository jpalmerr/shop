package shop

import org.scalatest.{WordSpec, Matchers}

class CheckoutSpec extends WordSpec with Matchers {

  val shoppingBasket = Seq[SKU](A, A, A, A, A, A, A, B, B, B, B, C, C, D)
  val userCheckout = new Checkout(A, B, C, D)

  "Special Price" should {
    "return a quantity and a price" in {
      userCheckout.specialPrice(A) should be(3, 130)
      userCheckout.specialPrice(B) should be(2, 45)
      userCheckout.specialPrice(C) should be(1, 20)
      userCheckout.specialPrice(D) should be(1, 15)
    }
  }

  "Calculate Total Cost" should {
    "return an accurate value converted to pounds" in {
      userCheckout.calculateTotalCost(shoppingBasket) should be(4.55)
      userCheckout.calculateTotalCost(shoppingBasket) shouldBe a[BigDecimal]
    }
  }


  "Parse item" should {
    "return a true for values stored" in {
      userCheckout.parse("A") should be (None)
      userCheckout.parse("B") should be (None)
      userCheckout.parse("C") should be (None)
      userCheckout.parse("D") should be (None)
      userCheckout.parse("X") should be (Some("Unknown item in shopping basket: X"))
    }
  }

  "User input" should {
    "return a true for A" in {
      userCheckout.isUserInputValid(Array("A")) should be (true)
      userCheckout.isUserInputValid(Array("B")) should be (true)
      userCheckout.isUserInputValid(Array("C")) should be (true)
      userCheckout.isUserInputValid(Array("D")) should be (true)
      userCheckout.isUserInputValid(Array("AA")) should be (false)
    }
  }
}
