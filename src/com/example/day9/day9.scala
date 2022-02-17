package day9

import java.io.File

import day8.day8.{getClass, reverse}

import scala.io.Source

object day9 {
  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay9.txt").getPath)
    val input = Source.fromFile(f).getLines().map(_.toLong).toList
    var index = 25
    var band = 25
    while (index < input.size && hasSumInPreviousNumbers(input.slice(index - band, index).toSet, input(index))) {
      index += 1
    }
    val answerFirstQuestion = input(index)
    println(answerFirstQuestion)
    
    index = 3
    band = 3
    while (band < 100 && input.slice(index - band, index).sum != answerFirstQuestion) {
      band += 1
      index = band
      while (input.indexOf(answerFirstQuestion) > index - 3 && input.slice(index - band, index).sum != answerFirstQuestion) {
        index += 1
      }
    }
    println(input.slice(index - band, index).max + input.slice(index - band, index).min)
  }

  def hasSumInPreviousNumbers(inputSet: Set[Long], sumTo: Long): Boolean = {
    val iterator = inputSet.iterator
    var value: Long = 0;
    var found = false;
    while (iterator.hasNext && !found) {
      value = iterator.next
      if (inputSet.contains(sumTo - value)) {
        found = true
      }
    }
    found
  }

  def oneLiner(inputSet: Set[Long], sumTo: Long): Boolean = {
    null != inputSet.toStream.find(long => inputSet.contains(sumTo - long)).getOrElse(null)
  }
}
