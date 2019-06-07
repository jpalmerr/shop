package shop

// stock keeping unit
sealed trait SKU {
  def unitPrice: Pence
}

case object A extends SKU {val unitPrice = 50}
case object B extends SKU {val unitPrice = 30}
case object C extends SKU {val unitPrice = 20}
case object D extends SKU {val unitPrice = 15}