package com.example.day14

import java.io.File

import com.example.day14.day14.getClass

import scala.collection.BitSet.empty.until
import scala.collection.mutable
import scala.io.Source

object Part2 {

  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay14.txt").getPath)
    var input = Source.fromFile(f).getLines().toList
    var map: mutable.HashMap[Long, Long] = new mutable.HashMap[Long, Long]
    var mask = "";
    var value = 0L
    for (line <- input) {
      if (line.startsWith("mask")) {
        mask = line.split("=")(1).trim
      } else {
        var mem = line.substring(line.indexOf("[") + 1, line.indexOf("]")).toLong
        var value = line.split("=")(1).trim.toLong
        var newValue : String = "";
        for ((bit, i) <- mask.reverse.split("").zipWithIndex) {
          if (bit.equals("X")) {
            newValue += "X"
          } else if (bit.equals("1")) {
            newValue += "1"
          } else {
            if ((mem & scala.math.pow(2, i).toLong) > 0L) {
              newValue += "1"
            } else {
              newValue += "0"
            }
          }
        }
       
        for (j <- 0 until scala.math.pow(2, newValue.count(_.equals('X'))).toInt) {
          var binary = j.toBinaryString
          var tempValue = newValue
          for (k <- 0 until newValue.count(_.equals('X'))) {
            if(binary.size > k ) {
              tempValue = tempValue.replaceFirst("X{1}", binary.reverse.charAt(k).toString)
            } else {
              tempValue = tempValue.replaceFirst("X{1}", "0")
            }
          }
          var sum : Long = 0
          for ((elem, n) <- tempValue.zipWithIndex) {
            if (elem.equals('1')) {
              sum += scala.math.pow(2, n).toLong
            }
          }
          map(sum)= value
        }
      }
    }
    print(map.foldLeft(0L) { case (a, (k, v)) => a + v })
  }
}
