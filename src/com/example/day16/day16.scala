package com.example.day16

import java.io.File

import com.example.day15.day15.getClass

import scala.collection.mutable
import scala.io.Source

object day16 {
  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay16.txt").getPath)
    var input = Source.fromFile(f).getLines().toList

    var tickets = input.drop(input.indexWhere(_.equals("nearby tickets:")) + 1)
    var constraints = input.take(input.indexWhere(_.equals("your ticket:")) - 1)
      .map(text => text.trim.split(":").drop(1).toList)
      .flatMap(_.toList)
      .map(_.trim)

    val listOfConStraint = constraints.map(_.split(" or "))
      .flatMap(_.toList)
      .map(_.split("-"))
      .flatMap(_.toList)
      .map(_.toInt)

    var max: Int = listOfConStraint.max
    val allowedTicketValues: Array[Boolean] = Array.fill(max+1)(false)

    var i = 0
    while (i < listOfConStraint.size - 1) {
      for (index <- listOfConStraint(i) until (listOfConStraint(i+1)+1)) {
        allowedTicketValues(index) = true
      }
      i += 2
    }

    val ints = tickets.map(_.split(",")).flatMap(_.toList).map(_.trim).map(_.toInt)
    var sum = ints.filter( s => s > max || !allowedTicketValues(s)).sum
    println(sum)
    
    
    
  }
}
