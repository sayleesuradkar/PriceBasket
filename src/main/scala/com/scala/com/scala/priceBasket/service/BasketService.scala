package com.scala.priceBasket.service

import com.scala.priceBasket.utils.ReadFile.{readCSVToList, readDiscountFromCSV, readPriceFromCSV}
import com.scala.priceBasket.utils.variables._

import java.io.FileNotFoundException
import scala.annotation.switch

class BasketService {

  def showOptions(): Unit = {

    do {
      System.out.println("==========OPTIONS==========")
      System.out.print("1. Show Products | ")
      System.out.print("2. Show Offers | ")
      System.out.print("3. Take Order | ")
      System.out.print("4. Show Basket | ")
      System.out.println("5. Exit System")
      System.out.print("Option: ")
      var option = scala.io.StdIn.readLine().toInt

      (option: @switch) match {
        case 1 =>
          productList = "src/main/resources/products.csv" ::productList
          showProducts(productList)
        case 2 =>
          offerList = "src/main/resources/discounts.csv"::offerList
          showOffers(offerList)
        case 3 => takeOrders()
        case 4 => showBasket()
        case 5 => continue = false
        case _ => System.out.println("Invalid Option")
      }

    }
    while (continue == true)
  }

  def showProducts(path :List[String]): List[String] = {
    try {
      productList = readCSVToList(path.mkString(""))
      println(productList)
    }
    catch
      {
        case e: FileNotFoundException => println("File Not found")
      }
    return productList
  }

  def showOffers(offers :List[String]): List[String] = {
    try {
      offerList = readCSVToList(offers.mkString(""))
      println(offerList)
    }
    catch {
      case e: FileNotFoundException => println("File Not found")
    }
    return offerList

  }


  def takeOrders(): Unit = {
    var continueOrder = true
    do {
      System.out.println("Enter Products")
      orderList += scala.io.StdIn.readLine()
      println("Do you still want to add more item Yes/No ? ")
      val option = scala.io.StdIn.readLine
      (option: @switch) match {
        case "Yes" => continueOrder = true
        case "No" => continueOrder = false
        case _ => println("Invalid Option ")
      }
    } while (continueOrder == true)
  }


  def showBasket(): Unit = {
    println("Your basket is : \n")
    var discQualifiedProduct = false
    for (element <- orderList) {
      if (element.equalsIgnoreCase("Milk")) {
        price = readPriceFromCSV("src/main/resources/prices.csv", "Milk")
        println(element + " =  " + price)
        total = total + price
      }
      if (element.equalsIgnoreCase("Soup")) {
        price = readPriceFromCSV("src/main/resources/prices.csv", "Soup")
        println(element + " =  " + price)
        total = total + price
      }
      if (element.equalsIgnoreCase("Apple")) {
        price = readPriceFromCSV("src/main/resources/prices.csv", "Apple")
        println(element + " =  " + price)
        total = total + price
      }
      if (element.equalsIgnoreCase("Bread")) {
        price = readPriceFromCSV("src/main/resources/prices.csv", "Bread")
        println(element + " =  " + price)
        total = total + price
      }

    }
    println("----------------------")
    println("Subtotal = " + total.toFloat)
    println("\n")
    discountedTotal(total.toFloat)
    println("----------------------")
    println("Total = " + total.toFloat)

  }

  def discountedTotal(discTotal: Float): Unit = {

    if (orderList.contains("Soup")) {
      quantity = readDiscountFromCSV("src/main/resources/discounts.csv", "Soup").apply("Quantity").toInt
      discountedItem = readDiscountFromCSV("src/main/resources/discounts.csv", "Soup").apply("DiscountedItem").toString
      discount = readDiscountFromCSV("src/main/resources/discounts.csv", "Soup").apply("Discount").toDouble
      discount = discount * (orderList.groupBy(identity).mapValues(_.size)("Soup") / 2)

      if (orderList.contains(discountedItem)) {
        if (orderList.groupBy(identity).mapValues(_.size)("Soup") % quantity >= 0) {
          if (orderList.groupBy(identity).mapValues(_.size)("Soup") / quantity == 1) {
            if (orderList.contains(discountedItem)) {
              total = total - discount
            }
          }
          else if (orderList.groupBy(identity).mapValues(_.size)("Soup") / quantity > 1) {
            if (orderList.contains(discountedItem)) {
              total = total - (discount * (orderList.groupBy(identity).mapValues(_.size)("Soup") / quantity))
            }
          }
        }
        println("----------------------")
        println("Soup Discount = " + discount)
      }
      else {
        println("----------------------")
        println("Soup discount not applied")

      }
      println("----------------------")
      println("Total = " + total.toFloat)


    }
    if (orderList.contains("Apple")) {
      quantity = readDiscountFromCSV("src/main/resources/discounts.csv", "Apple").apply("Quantity").toInt
      discount = readDiscountFromCSV("src/main/resources/discounts.csv", "Apple").apply("Discount").toDouble
      discount = discount * orderList.groupBy(identity).mapValues(_.size)("Apple")
      if (orderList.groupBy(identity).mapValues(_.size)("Apple") > 0) {
        total = total - discount
      }
      println("----------------------")
      println("Apple Discount = " + discount)

    }
    else {
      println("----------------------")
      println("Apple discount not applied")
    }
  }
}
