package day11

import java.io.File

import scala.io.Source

object day11Part1 {
  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("test.txt").getPath)
    var input = Source.fromFile(f).getLines().map(_.split("").toArray).toArray
    input.foreach(s => println(s.mkString("")))

    var count = 1
    while (count > 0) {
      count = 0
      var output: Array[Array[String]] = Array.ofDim[String](input.size, input(0).size)
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
    }
    print(input.flatMap(_.toList).count(_.equals("#")))
  }

  private def checkNeighbours(input: Array[Array[String]], i: Int, j: Int, char: String): Boolean = {
    val ith = Array(0, 1, 1, -1, 0, -1, -1, 1)
    val jth = Array(1, 0, 1, 0, -1, -1, 1, -1)
    
    
    // All neighbours of cell
    var sumOccupied = 0
    for (k <- 0 until 8) {
      if (isValid(i + ith(k), j + jth(k), input.length, input(0).length)) {
        if (input(i + ith(k))(j + jth(k)).equals("#")) {
          sumOccupied += 1
        }
      }
    }
    if (char.equals("L") && sumOccupied == 0) {
      return true
    } else if (char.equals("#") && sumOccupied >= 4) {
      return true
    }
    false
  }

  private def isValid(i: Int, j: Int, l: Int, k: Int): Boolean = {
    if (i < 0 || j < 0 || i >= l || j >= k) return false
    true
  }

  def reverse(operator: String) = operator match {
    case "L" => "#"
    case "#" => "L"
    case "." => "."
  }
}
