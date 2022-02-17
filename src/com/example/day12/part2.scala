package com.example.day12

import java.io.File

import com.example.day12.day12.getClass

import scala.io.Source

object part2 {

  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay12.txt").getPath)
    val input = Source.fromFile(f).getLines().toList
    val postion = Array(0, 0)
    val waypoint = Array(10,1)
    
    for (line <- input) {
      var action = line.substring(0, 1)
      var move = line.substring(1).toInt

      if (action.equals("N")) {
        waypoint(1) += move
      }
      if (action.equals("S")) {
        waypoint(1) -= move
      }
      if (action.equals("E")) {
        waypoint(0) += move
      }
      if (action.equals("W")) {
        waypoint(0) -= move
      }
      if (action.equals("F")) {
        postion(0) += waypoint(0) * move
        postion(1) += waypoint(1) * move
      }
      if (action.equals("R")) {
        val (y, x) = changeWayPoint(move, waypoint(0), waypoint(1))
        waypoint(0) = y
        waypoint(1) = x
      }
      if (action.equals("L")) {
        val (x, y) = changeWayPoint(-1 * move, waypoint(0), waypoint(1))
        waypoint(0) = x
        waypoint(1) = y
      }
    }
    println(postion(0).abs + " " + postion(1).abs)
    println(postion(0).abs + postion(1).abs)
  }

  def changeWayPoint(direction: Int, x: Int, y: Int): (Int, Int) = {
    val dir: Int = direction % 360
    var dirInRadians = 0.0
    if (dir.equals(90) || dir.equals(-270)) {
      dirInRadians = Math.PI / 2
    }
    if (dir.equals(180) || dir.equals(-180)) {
      dirInRadians = -Math.PI
    }
    if (dir.equals(270) || dir.equals(-90)) {
      dirInRadians = -Math.PI / 2
    }
    if (dir.equals(0)) {
      dirInRadians = Math.PI
    }
    (x * math.cos(dirInRadians).toInt + y * math.sin(dirInRadians).toInt, -1 * x * math.sin(dirInRadians).toInt + y * math.cos(dirInRadians).toInt)
  }

}
