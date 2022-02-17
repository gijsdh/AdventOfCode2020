package com.example

import com.example.day18.Day18.applyOperator

package object day18 {
  def calcNumbers(list: List[String], index: Int): (Long, Int) = {
    var sum = 0L
    var partialSum = 0L
    var idx = 0
    while (index < list.size) {
      if (list(index).equals("(")) {
        var (first, idx) = calcNumbers(list, index + 1)
        var op = list(idx +1)
        var (second, jdx) = calcNumbers(list, idx + 2)
        return (applyOperator(first, second, op), jdx+1)
      }
      if (list(index+1).equals(")")) {
        return (list(index).toLong, index + 1)
      }
      print(index, list(index))
      var first = list(index).toLong
      var op = list(index + 1)
      var (second, idx) = calcNumbers(list, index + 2)
      return (applyOperator(first, second, op), idx)
    }
    (sum, index)
  }
}
