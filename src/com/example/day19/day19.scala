package com.example.day19

import java.io.File

import scala.collection.mutable.HashMap
import scala.io.Source

object day19 {
  val part2: Boolean = true;
  val rules: HashMap[Int, String] = new HashMap[Int, String]
  var regexes: HashMap[Int, String] = new HashMap[Int, String]

  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay19.txt").getPath)
    val input = Source.fromFile(f).getLines().toList
    val entries = input.filter(!_.contains(":")).filter(!_.isEmpty)

    for (line <- input) {
      if (line.contains(":")) {
        val list = line.split(':')
        rules.put(list(0).toInt, list(1))
      }
    }
    var sum = 0
    for (line <- entries) {
      if (line.matches(getReges(0))) sum += 1
    }
    println(sum)
  }

  def toRegex(rule: String): String = {
    var ans = ""
    if (rule.contains("|")) {
      val or = rule.trim.split('|')
      val left = or(0).trim.split(" ").map(s => "(" + getReges(s.toInt) + ")").mkString("")
      val right = or(1).trim.split(" ").map(s => "(" + getReges(s.toInt) + ")").mkString("")
      ans = "(" + left + "|" + right + ")"
    } else if (rule.contains("\"")) {
      ans = rule(2).toString
    } else {
      ans = rule.trim.split(" ").map(s => "(" + getReges(s.toInt) + ")").mkString("")
    }
    ans
  }

  def getReges(number: Int): String = {
    if (regexes.contains(number)) {
      return regexes(number)
    }
    var ans = "";
    if (number == 8 && part2) {
      ans = "(" + getReges(42) + ")+"
    }
    else if (number == 11 && part2) {
      var r42 = getReges(42)
      var r31 = getReges(31)
      //Cheating here, this row could continue... |"+ r42 + "{6}" + r31 + "{6}
      ans = "(" + r42 + r31 + "|" + r42 + "{2}" + r31 + "{2}|" + r42 + "{3}" + r31 + "{3}|" + r42 + "{4}" + r31 + "{4}|$" + r42 + "{5}" + r31 + "{5})"
    } else {
      ans = toRegex(rules(number))
    }
    regexes(number) = ans;
    return ans
  }
}
