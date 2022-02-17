package day6

import java.io.File

import day5.Day5.getClass

import scala.io.Source

object day6 {
  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay6.txt").getPath)
    val input = Source.fromFile(f).mkString
    val parsedInput = input.split("\r\n\r\n").toList
    val parsed = parsedInput.toStream.map(_.replace("\r\n", " ").trim).toList

    println(parsed)
    var sum = 0;
    var sum2 = 0
    for (group <- parsed) {
      sum += group.distinct.replace(" ", "").length
      val alphabetOccurrence: Array[Int] = new Array[Int](26)
      val list = group.split(" ").toList
      list.foreach(s => s.foreach(char => alphabetOccurrence(char.toInt % 26) += 1))
      sum2 += alphabetOccurrence.toStream.filter(_ == list.size).size
    }
    println(sum)
    println(sum2)
  }
}
