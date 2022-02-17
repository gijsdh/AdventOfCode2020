package com.example.day17

import java.io.File

import com.example.day16.day16.getClass
import com.example.day17.Day17.isValid
import day11.day11Part1.isValid

import scala.io.Source

object Day17 {

  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay17.txt").getPath)
    var input = Source.fromFile(f).getLines().toList.map(_.split("").toList)
    println(input)
    
    var matrixIn : Array[Array[Array[String]]] =  Array.fill(input.length + 12, input.length + 12, 13)(".")
   
    
    for (i <- 0 until  input.length) {
      for (j <- 0 until input.length) {
        matrixIn(i+6)(j+6)(6) = input(i)(j).toString
       
      }
    }
    
    printted(matrixIn, 6)
    println("z=-1")
    
    for (x <- 0 until 6) {
      var matrixOut : Array[Array[Array[String]]] =  Array.fill(input.length + 12, input.length + 12, 13)(".")
      for (i <- 0 until matrixIn.length) {
        for (j <- 0 until matrixIn(0).length) {
          for (k <- 0 until matrixIn(0)(0).length) {
            matrixOut(i)(j)(k) = checkNeighbours(matrixIn, i, j, k, matrixIn(i)(j)(k))
          }
        }
      }
      matrixIn = matrixOut
      
//      printted(matrixOut, 5)
//      println("z=0")
//      printted(matrixOut, 6)
//      println("z=1")
//      printted(matrixOut, 7)
//      println("z=-1")
    }

    println(matrixIn.flatMap(_.toList).flatMap(_.toList).toList.count(_.equals("#")))
    
  }

  private def printted(matrix: Array[Array[Array[String]]], depth : Int)  {
    for (i <- 0 until matrix.length; j <- 0 until matrix(0).length) {
      if(j==0)print(i +" " )
      print( matrix(i)(j)(depth) + ",")
      if (j == matrix(0).length - 1) {
        println()
      }
    }
  }

  private def checkNeighbours(input: Array[Array[Array[String]]], II: Int, JJ: Int, KK: Int, char: String): String = {
    val ith : Array[Int] = Array(1, 0, -1)
    val jth : Array[Int] = Array(1, 0, -1)
    val kth : Array[Int] = Array(1, 0, -1)


    // All neighbours of cell
    var sumOccupied = 0
    for (i <- 0 until 3) {
      for (j <- 0 until 3) {
        for (k <- 0 until 3) {
          val index_I = II + ith(i)
          val index_J = JJ + jth(j)
          val index_K = KK + kth(k)

//          println(index_I + "." + index_J + "." + index_K)
          if (!(ith(i) ==0 && jth(j)==0 && kth(k) == 0) && isValid(index_I, index_J, index_K, input.length, input(0).length, input(0)(0).length)) {
            if ( input(index_I)(index_J)(index_K).equals("#")) {
//              if (II == 8 && JJ == 6 && KK == 6) {
//                println(index_I + "." + index_J + "." + index_K)
//                println(ith(i) + "." + jth(j) + "." + kth(k))
//              }
              sumOccupied += 1
            }
          }
        }
      }
    }
    
    if (char.equals("#") && (sumOccupied == 2 || sumOccupied == 3)) {
     // println("#" +II + "." + JJ + "." + KK)
      return "#"
    }
    else if (char.equals(".") && (sumOccupied == 3)) {
    //  println("." + II + "." + JJ + "." + KK)
      return "#"
    }
    "."
  }

  private def isValid(i: Int, j: Int, k: Int, x: Int, y: Int, z: Int): Boolean = {
    if ( i < 0 || j < 0 || k < 0  || i >= x || j >= y || k >= z) return false
    true
  }
  
}
