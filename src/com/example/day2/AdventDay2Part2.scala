package day2

import java.io.File

import scala.io.Source

object AdventDay2Part2 {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
    val f = new File(getClass.getClassLoader.getResource("com/example/input1.txt").getPath)
    val input = Source.fromFile(f).getLines.toList

    println(input)

    var goodPasswords = 0;
    for {a <- input} {
      val list = a.split(" ").toList
      val bounds =  list(0).split("-")
      val indexOne = bounds(0).toInt - 1
      val indexTwo = bounds(1).toInt - 1 //0 based
      val letter = list(1).charAt(0)
      
      if(list(2).trim.charAt(indexOne).equals(letter) ^ list(2).trim.charAt(indexTwo).equals(letter)) {
        goodPasswords += 1
        println(indexOne + " " + indexTwo + " " + list(2).trim)
      }
    }
    print(goodPasswords)
  }
  
}
