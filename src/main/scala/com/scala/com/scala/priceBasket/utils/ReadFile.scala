package com.scala.priceBasket.utils

object ReadFile {

  var itemDiscountMap: Map[String, String] = Map[String, String]()
  var productList: List[String] = List()

  def readCSVToList(path: String): List[String] = {
    List
      val bufferedSource = io.Source.fromFile(path)
      for (line <- bufferedSource.getLines) {
        val cols = line.split(",").map(_.trim)
        productList = s"${cols(0)}|${cols(1)}|${cols(2)}|${cols(3)}" :: productList
      }
      bufferedSource.close
      return productList
    }



  def readPriceFromCSV(path: String, item: String): Double = {

    var itemPrice: Double = 0.0
    val bufferedSource = io.Source.fromFile(path)
    for (line <- bufferedSource.getLines) {
      val cols = line.split(",").map(_.trim)
      if (s"${cols(0)}".equalsIgnoreCase(item)) {
        itemPrice = s"${cols(1)}".toDouble
      }
    }
    bufferedSource.close
    return itemPrice
  }

  def readDiscountFromCSV(path: String, item: String): Map[String, String] = {


    val bufferedSource = io.Source.fromFile(path)
    for (line <- bufferedSource.getLines) {
      val cols = line.split(",").map(_.trim)
      if (s"${cols(0)}".equalsIgnoreCase(item)) {
        itemDiscountMap = itemDiscountMap + ("Item" -> s"${cols(0)}", "Quantity" -> s"${cols(1)}", "Discount" -> s"${cols(2)}", "DiscountedItem" -> s"${cols(3)}")

      }
    }
    bufferedSource.close
    return itemDiscountMap
  }
}