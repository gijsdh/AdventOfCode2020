package com.example.day18

import java.io.File

import com.example.day17.Day17.getClass

import scala.::
import scala.collection.mutable
import scala.io.Source

object Day18 {

  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay18.txt").getPath)
    var input = Source.fromFile(f).getLines().toList.map(_.replace(" ", "")).toList
    
    var sum = 0L
    for (line <- input) {
      var list: mutable.MutableList[String] = new mutable.MutableList[String]
      list ++= line.split("").toList
      var temp =  calcNumberAdditionFirst(removeParenThesis(list, 0))
      println(temp)
     sum += temp
    }
    print(sum)
  }

  def applyOperator(x: Long, y: Long, operator: String) = operator match {
    case "+" => x + y
    case "*" => x * y
  }


  def removeParenThesis(list: mutable.MutableList[String], index: Int): mutable.MutableList[String] = {
    var count = 0
    var indexLeft = 0
    var list3: mutable.MutableList[String] = list
    var localIndex = index

    while (list3.contains("(")) {
      if (list3(localIndex).equals("(")) {
        if (count > 0) {
          list3 = removeParenThesis(list3, localIndex)
          count = 0
          localIndex = 0
          indexLeft = 0
        } else {
          indexLeft = localIndex
          count += 1
        }
      }
      if (list3(localIndex).equals(")")) {
        val part = list3.slice(indexLeft + 1, localIndex)
        var temp: mutable.MutableList[String] = new mutable.MutableList[String]
        temp += calcNumberAdditionFirst(part).toString
        return removeParenThesis(list3.take(indexLeft) ++ temp ++ list3.takeRight(list3.size - (localIndex + 1)), 0)
      }
      localIndex += 1
    }
    list3
  }

  def calcNumber(list: mutable.MutableList[String]): (Long) = {
    var sum = 0L
    var index = 0
    while (index < list.size) {
      if (sum == 0) {
        sum += applyOperator(list(index).toLong, list(index + 2).toLong, list(index + 1))
        index += 3
      }
      else {
        sum = applyOperator(sum, list(index + 1).toLong, list(index))
        index += 2
      }
    }
    sum
  }

  def calcNumberAdditionFirst(list: mutable.MutableList[String]): (Long) = {
    var index = 1
    var workList: mutable.MutableList[String] = list
    while (workList.contains("+")) {
      if (workList(index).equals("+")) {
        val Temp = applyOperator(workList(index - 1).toLong, workList(index + 1).toLong, workList(index))
        var tempList: mutable.MutableList[String] = new mutable.MutableList[String]
        tempList += Temp.toString
        workList = workList.take(index - 1) ++ tempList ++ workList.takeRight(workList.size - (index + 2))
        index -= 2
      }
      index += 2
    }
    
    if (workList.size > 1) {
      return calcNumber(workList)
    }
    workList.map(_.toLong).sum
  }
  
}
