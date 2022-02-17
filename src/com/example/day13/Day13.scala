package com.example.day13

import java.io.File

import com.example.day15.day15.getClass

import scala.collection.mutable
import scala.io.Source

object Day13 {
  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay13.txt").getPath)
    var input = Source.fromFile(f).getLines().toList
    println(input)
    var time = input(0).toInt
    var busId = input(1).replace(",x", "").split(",").map(s => s.toInt).toList

    println(busId)
    println(time)
    var min = 9999
    var number = 0
    var id = 0
    for (i <- busId) {
      var number = i - (time % i)
      if (number < min) {
        min = number
        id = i
      }
    }
    println(min * id)
  }
}
