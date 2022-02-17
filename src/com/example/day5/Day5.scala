package day5

import java.io.File
import scala.io.Source

object Day5 {
  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay5.txt").getPath)
    val input = Source.fromFile(f).getLines().toList
    //println(input)
    var max = 0
    println(input.size)
    val seats = scala.collection.mutable.Set(1)
    for (i <- 1 until 127*8) seats.add(i)
    
    for (line <- input) {
      val row = Integer.parseInt(line.substring(0, 7).replace("B", "1").replace("F", "0"), 2)
      val column = Integer.parseInt(line.substring(7, 10).replace("R", "1").replace("L", "0"), 2)
      val id = (row * 8) + column
      seats.remove(id)
      if (max < id) {
        max = id
      }
      
    }
    println(seats.toList.sorted.filter(_<800).filter(_>100))
    println(max)
  }
}
