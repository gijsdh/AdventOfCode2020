package com.example.day13

import java.io.File

import com.example.day13.Day13.getClass

import scala.io.Source

object part2 {
  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay13.txt").getPath)
    val lines = Source.fromFile(f).getLines().toSeq
    
    val buses = lines(1).split(",").toSeq

    print(buses.zipWithIndex.filter(_._1!="x").map(b => (b._1.toInt, b._2)))
    
    
    
   

  }

  def modInv(a: Int, m: Int, x:Int = 1, y:Int = 0) : Int = if (m == 0) x else modInv(m, a%m, y, x - y*(a/m))
  
  def gcdExt(u: Int, v: Int): (Int, Int, Int) = {
    def aux(a: Int, b: Int, x: Int, y: Int, x1: Int, x2: Int, y1: Int, y2: Int): (Int, Int, Int) = {
      if(b == 0) (x, y, a) else {
        val (q, r) = (a / b, a % b)
        aux(b, r, x2 - q * x1, y2 - q * y1, x, x1, y, y1)
      }
    }
    aux(u, v, 1, 0, 0, 1, 1, 0)
  }

  def modInv(a: Int, m: Int): Option[Int] = {
    val (i, j, g) = gcdExt(a, m)
    if (g == 1) Option(if (i < 0) i + m else i) else Option.empty
  }
  
  // all primes, so we can use the modular inverse.
  //ax = 1 mod m
  //ax = 1 + km
}
