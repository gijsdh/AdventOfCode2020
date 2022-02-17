package com.example.day11

import java.io.File

import scala.io.Source

object part2 {

  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay11.txt").getPath)
    var input = Source.fromFile(f).getLines().map(_.split("").toArray).toArray
    input.foreach(s => println(s.mkString("")))


    var count = 1
    while (count > 0) {
      count = 0
      var output : Array[Array[String]] =Array.ofDim[String](input.size, input(0).size)
      for (i <- 0 until input.size) {
        for (j <- 0 until input(0).size) {
          var value = input(i)(j)
          if (!value.equals(".") && checkNeighbours(input, i, j, value)) {
            output(i)(j) = reverse(value)
            count += 1
          } else {
            output(i)(j) = input(i)(j)
          }
        }
      }

      input = output.clone()
    
      println("----------------------------------------------")
      input.foreach(s => println(s.mkString("")))
    }
    print(input.flatMap(_.toList).count(_.equals("#")))
  }

  private def checkNeighbours(input: Array[Array[String]], i: Int, j: Int, char: String): Boolean = {
    val ith = Array(0, 1, 1, -1, 0, -1, -1, 1)
    val jth = Array(1, 0, 1, 0, -1, -1, 1, -1)
    // All neighbours of cell
    var sumOccupied = 0
    for (k <- 0 until 8) {
      var factor = 1
      var found = false
      while (factor < math.max(input.length, input(0).length) && !found) {
        val index_I = i + (ith(k) * factor)
        val index_J = j + (jth(k) * factor)
        if (isValidIndex(index_I, index_J, input.length, input(0).length)) {
          if (input(index_I)(index_J).equals("#")) {
            sumOccupied += 1
            found = true
          } else if (input(index_I)(index_J).equals("L")) {
            found = true
          }
        }
        factor += 1
      }
    }
    if (char.equals("L") && sumOccupied == 0) {
      return true
    } else if (char.equals("#") && sumOccupied >= 5) {
      return true
    }
    false
  }

  private def isValidIndex(i: Int, j: Int, l: Int, k: Int): Boolean = {
    if (i < 0 || j < 0 || i >= l || j >= k) return false
    true
  }

  def reverse(operator: String) = operator match {
    case "L" => "#"
    case "#" => "L"
    case "." => "."
  }
}
