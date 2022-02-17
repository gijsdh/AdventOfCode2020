package day10

import java.io.File

import day9.day9.getClass

import scala.::
import scala.io.Source

object day10 {
  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay10.txt").getPath)
    val input = Source.fromFile(f).getLines().map(_.toLong).toList.sorted
    var sum1 = 1
    var sum3 = 1
    for (i <- 0 to input.length - 2) {
      val dif = input(i + 1) - input(i)
      if (dif == 1) sum1 += 1
      if (dif == 3) sum3 += 1
    }
    println(sum1*sum3)
    
    var calcgroup : Long = 0
    var sumCombinations : Long = 1
    val input2 : List[Long] =  0 :: input
    val input3 : List[Long] = input2 ::: List(input.max + 3)

    println(input3)
    for (i <- 0 to input3.length - 2) {
      val dif = input3(i + 1) - input3(i)
      if (dif == 1) calcgroup += 1
      if (dif == 3) {
        if (calcgroup == 4) sumCombinations *= (math.pow(2, calcgroup - 1).toLong - 1)
        if (calcgroup == 3 || calcgroup == 2) sumCombinations *= math.pow(2, calcgroup - 1).toLong
        calcgroup = 0
      }
    }
    println(sumCombinations)
  }
}
