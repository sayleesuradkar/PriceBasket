package com.scala.priceBasket.utils

import scala.collection.mutable.ListBuffer

object variables {

  var continue = true
  var orderList = new ListBuffer[String]()
  var price: Double = 0.0
  var total: Double = 0.0
  var quantity: Integer = 1
  var discount: Double = 0.0
  var discountedItem: String = ""
  var productList: List[String] = List()
  var offerList: List[String] = List()

}
