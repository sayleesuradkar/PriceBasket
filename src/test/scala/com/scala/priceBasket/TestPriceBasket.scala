package com.scala.priceBasket

import com.scala.priceBasket.Shopping.{showOffers, showProducts, takeOrders}
import com.scala.priceBasket.utils.variables.offerList
import org.scalatest.flatspec.AnyFlatSpec

class TestPriceBasket extends AnyFlatSpec{
  var productList: List[String] = List()
  "An incorrect product file provided" should "have size 0" in {
    showProducts(productList.::("src/main/resources/products1.csv"))
    assert(productList.isEmpty)
  }

  "An incorrect Offer file provided" should "have size 0" in {
    showOffers(offerList.::("src/main/resources/discounts1.csv"))
    assert(offerList.isEmpty)
  }
}
