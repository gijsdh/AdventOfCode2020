package com.example.day16

import java.io.File
import java.util

import com.example.day16.day16.getClass

import scala.io.Source

object part22 {
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
    val allowedTicketValues: Array[Boolean] = Array.fill(max + 1)(false)
    CreateAllowedValuesList(listOfConStraint, allowedTicketValues)

    val validTickets = tickets.filter(s => s.split(",")
                              .map(_.trim)
                              .map(_.toInt)
                              .filter(s => s > max || !allowedTicketValues(s)).isEmpty)
                              .map(_.split(",")
                                    .map(_.toInt)
                                    .toList)
    val columns: Array[Array[Int]] = Array.ofDim[Int](validTickets(0).size, 20)

    println(listOfConStraint)

    for (i <- 0 until validTickets(0).size) {
      for (j <- 0 until validTickets.size) {
        var value = validTickets(j)(i)
        for (k <- 0 until 20) {
          if (checkValid(listOfConStraint, value, k * 4)) {
            columns(i)(k) += 1
          }
        }
      }
    }
    
    columns.map(_.count(_==190)).foreach(println(_))

    val mapping: Array[Boolean] = Array.fill(20)(false)
    val result: Array[Int] = new Array[Int](20)

    var s = true
    var count = 0;
    while (s) {
      for ((column, i) <- columns.zipWithIndex) {
        var sum = 0
        var index = 0
        for ((number, k) <- column.zipWithIndex) {
          if (!mapping(k) && number.equals(190)) {
            sum += 1
            index = k
          }
        }
        if (sum.equals(1)) {
          mapping(index) = true
          result(index) = i
          count += 1
        }
      }
      if (count == 20) {
        s = false
      }
    }

    println(result.toList)
    val ticktfinal = "89,179,173,167,157,127,163,113,137,109,151,131,97,149,107,83,79,139,59,53".split(",").map(_.toLong)
    var sum2 = 1L
    for (i <- result.take(6)) {
      sum2 *= ticktfinal(i)
    }
    print(sum2)
  }

  def checkValid(listOfConStraint: List[Int], number: Int, set: Int): Boolean = {
    if (number >= listOfConStraint(set) && number <= listOfConStraint(set + 1)) return true
    if (number >= listOfConStraint(set + 2) && number <= listOfConStraint(set + 3)) return true
    false
  }

  private def CreateAllowedValuesList(listOfConStraint: List[Int], allowedTicketValues: Array[Boolean]) = {
    var i = 0
    while (i < listOfConStraint.size - 1) {
      for (index <- listOfConStraint(i) until (listOfConStraint(i + 1) + 1)) {
        allowedTicketValues(index) = true
      }
      i += 2
    }
  }
}
