package com.example.day15

import java.io.File

import scala.collection.mutable
import scala.io.Source

object day15 {
  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay15.txt").getPath)
    var input = Source.fromFile(f).getLines().toList(0).split(",").map(s => s.toLong).toList
    println(input)

    var map: mutable.HashMap[Long, Long] = new mutable.HashMap[Long, Long]
    for ((l, i) <- input.zipWithIndex) {
      map.put(l, i)
    }

    var previousNumber = 0L
    for (i: Int <- input.size until 30000000 - 1) {
      var newNumber = 0L
      if (map.get(previousNumber).isDefined) {
        newNumber = i - map(previousNumber)
        map.put(previousNumber, i)
      } else {
        map.put(previousNumber, i)
      }
      previousNumber = newNumber
    }
    println("previousNumber " + previousNumber)
  }
}
