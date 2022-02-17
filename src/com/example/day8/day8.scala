package day8

import java.io.File

import scala.io.Source

object day8 {


  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay8.txt").getPath)
    val input = Source.fromFile(f).getLines().map(s => s.split(" ")).toArray
    var visitedIndexes: Array[Boolean] = Array.fill(input.size)(false)
    println(loop(input, visitedIndexes, 0))

    println(fixProgram(input, 0))
  }

  def loop(list: Array[Array[String]], visited: Array[Boolean], index: Int): Int = {
    if (index.equals(list.size - 1)) {
      visited(index) = true
      if (list(index)(0).equals("acc")) return applyOperator(0, list(index)(1).substring(1).toInt, list(index)(1).substring(0, 1))
      return 0
    }
    if (visited(index)) return 0
    visited(index) = true
    val operation = list(index)(0)

    if (operation.equals("nop")) {
      return 0 + loop(list, visited, index + 1)
    }
    val number = list(index)(1).substring(1).toInt
    val operator = list(index)(1).substring(0, 1)

    if (operation.equals("jmp")) {
      return 0 + loop(list, visited, applyOperator(index, number, operator))
    }
    if (operation.equals("acc")) {
      return applyOperator(0, number, operator) + loop(list, visited, index + 1)
    }
    0
  }

  def fixProgram(input: Array[Array[String]], i: Int): Int = {
    val visitedIndexes: Array[Boolean] = Array.fill(input.size)(false)
    var output = loop(input, visitedIndexes, 0)
    val changedIndex = findNextNopOrJmp(input, i);
    if (!visitedIndexes.last) {
      output = fixProgram(input, changedIndex)
    }
    output
  }

  def findNextNopOrJmp(input: Array[Array[String]], index: Int): Int = {
    var newIndex = index
    //turn back  nop|jmp -> nop|jmp
    if (index != 0) input(index)(0) = reverse(input(index)(0));
    //find next index where nop|jmp is
    var skip = index + 1
    newIndex = input.drop(skip).indexWhere(_ (0).matches("(nop|jmp)")) + skip
    // switch nop|jmp
    input(newIndex)(0) = reverse(input(newIndex)(0));
    newIndex
  }

  def reverse(operator: String) = operator match {
    case "nop" => "jmp"
    case "jmp" => "nop"
  }


  def applyOperator(x: Int, y: Int, operator: String) = operator match {
    case "+" => x + y
    case "-" => x - y
    case _ => x + y
  }
}
