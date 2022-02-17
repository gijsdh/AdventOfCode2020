package com.example.day12

import java.io.File

import scala.io.Source

object day12 {
  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay12.txt").getPath)
    var input = Source.fromFile(f).getLines().toList
    println(input)
    var postion = Array(0, 0)
    var direction = 90

    for (line <- input) {
      var action = line.substring(0, 1)
      var move = line.substring(1).toInt

      if (action.equals("N")) {
        postion(0) += move
      }
      if (action.equals("S")) {
        postion(0) -= move
      }
      if (action.equals("E")) {
        postion(1) += move
      }
      if (action.equals("W")) {
        postion(1) -= move
      }
      if (action.equals("F")) {
        val (y, x) = getDirection(direction)
        postion(0) += y * move
        postion(1) += x * move
      }
      if (action.equals("R")) {
        direction += move
      }
      if (action.equals("L")) {
        direction -= move
      }

    }
    println(postion(0).abs + " " + postion(1).abs)
    println(postion(0).abs + postion(1).abs)
  }
  
  def getDirection(direction: Int): (Int, Int) = {
    val dir : Int = direction % 360
    if (dir.equals(90) ||  dir.equals(-270)) {
      return (0, 1)
    }
    if (dir.equals(180) || dir.equals(-180)) {
      return (-1, 0)
    }
    if (dir.equals(270) || dir.equals(-90)) {
      return (0, -1)
    }
    if (dir.equals(0)) {
      return (1, 0)
    }
    println("this should not happen "+ dir)
    (0,0)
  }
}
