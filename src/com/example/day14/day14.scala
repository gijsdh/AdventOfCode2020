package com.example.day14

import java.io.File

import com.example.day12.day12.getClass

import scala.collection.mutable
import scala.io.Source

object day14 {
  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay14.txt").getPath)
    var input = Source.fromFile(f).getLines().toList
    
    var map :  mutable.HashMap[Long, Long] = new mutable.HashMap[Long, Long]
    var mask = "";
    for(line <- input) {
      
      if(line.startsWith("mask")) {
        mask = line.split("=")(1).trim
      } else {
        var mem = line.substring(line.indexOf("[")+1, line.indexOf("]")).toLong
        var value = line.split("=")(1).trim.toLong
        var newValue = 0L;
        for((bit, i) <- mask.reverse.split("").zipWithIndex) {
          if(bit.equals("X")) {
            newValue += value & scala.math.pow(2,i).toLong
          } else if(bit.equals("1")) {
            newValue += scala.math.pow(2,i).toLong
          }
        }
        map(mem)= newValue
      }
    }
    print(map.foldLeft(0L){case (a, (k,  v )) => a + v })
  }
}
