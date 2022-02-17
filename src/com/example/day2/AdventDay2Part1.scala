package day2

import java.io.File

import scala.io.Source

object AdventDay2Part1 {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
    val f = new File(getClass.getClassLoader.getResource("com/example/input1.txte/input1.txt").getPath)
    val input = Source.fromFile(f).getLines.toList

    println(input)

    var goodPasswords = 0;
    for {a <- input} {
      val list = a.split(" ").toList
      val countSpecificLetter = list(2).count(_ == list(1).charAt(0))
      val bounds = list(0).split("-")
      val lowerbound = bounds(0).toInt
      val upperbound = bounds(1).toInt
      println(lowerbound + " " + upperbound + " " + countSpecificLetter)
      if (lowerbound <= countSpecificLetter && upperbound >= countSpecificLetter) {
        goodPasswords += 1
      }
    }
    println(goodPasswords)
  }
}
