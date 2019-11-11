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

println("look here")
3 /% 4
10 /% 2
// 11 /% 2
val (quotient, remainder) = 11 /% 2

def calculateItemCost(item: SKU, quantity: Int)(offer: (Int, Int)) :Int = {
  val (quotient, remainder) = quantity /% offer._1
//  println(s"quotient: $quotient , remainder: $remainder")
  val totalItemCost = quotient * offer._2 + remainder * item.unitPrice
  totalItemCost
}

calculateItemCost(A, 7)(3, 120) // 290

val itemCost = numberOfEachItem.map(item => calculateItemCost(item._1, item._2)(specialPrice(item._1)))

val totalCost = itemCost.sum

val totalCostPounds = BigDecimal(totalCost.toDouble / 100).setScale(2)


val stockedItems: Seq[String] = Seq("A", "B", "C", "D")

def parse(item: String): Either[String, Boolean] = {
  if (stockedItems.contains(item)) {
    Right(true)
  } else {
    Left(s"Unknown item in shopping basket: $item")
  }

}

 def userInputValid(args: Array[String]): Boolean = {
   for (item <- args) {
     val result = parse(item)
     if (result.isLeft) {
       println(result.left.getOrElse("Exiting application!"))
       System.exit(0)
      }
     }
    true
   }


def parse1(item: String): Boolean = {
  if (stockedItems.contains(item)) {
    true
  } else {
    println(s"Unknown item in shopping basket: $item")
    false
  }
}

def parse2(item: String): Either[(String, Boolean), Boolean] = {
  if (stockedItems.contains(item)) {
    Right(true)
  } else {
    Left((s"Unknown item in shopping basket: $item", false))
  }
}

val args = Array("A", "B", "CC", "DD")
println("result:")
//val results: String = args.map(args => parse2(args)).toList
val results: List[Either[(String, Boolean), Boolean]] = args.map(args => parse2(args)).toList
val r1 = results.contains(Right(true: Boolean))
val r2 = results.contains(Left("Unknown item in shopping basket: CC", false))
val r3 = results.contains(Left(_: String, _: Boolean)) // should be true !!

println("------")

println("exists")
results.exists(_.isLeft)
val result2: List[Either[(String, Boolean),Boolean]] = List(Right(true), Right(true))
result2.exists(_.isLeft)
println("--------")

val listLefts = results.filter(_ != Right(true))
println("FINAL")
listLefts.isEmpty

//def isUserInputValid(args: Array[String]): Boolean = {
//  val result = args.map(args => parse1(args))
//
//}
//
//val finalBoolean = isUserInputValid(args)
//println(".........")
//finalBoolean

val listOfLefts = results.filter(_ != Right(true))

results match {
  case Left(message) => println(message)
  case Right _ =>
}



