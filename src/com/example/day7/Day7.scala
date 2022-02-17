package day7

import java.io.File

import scala.collection.mutable.HashMap
import scala.io.Source

object Day7 {
  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay7.txt").getPath)
    val input = Source.fromFile(f).getLines().toList.map(s => s.replace(".", ""))
    val parsedInput = input.map(s => s.replaceAll("(bags|bag)", "").trim)
                           .map(s => s.trim.split("(contain|,)").toList)
    
    val map : HashMap[String, List[Bag]] = new HashMap[String, List[Bag]]
    
    for (bags <- parsedInput) {
      val nameBag = bags(0).trim
      val list: List[Bag] = bags.drop(1)
                                .toStream
                                .filter(!_.contains("no other"))
                                .map(s => new Bag(s.substring(3).trim, s.substring(0, 2).trim.toInt))
                                .toList
      map.put(nameBag, list)
    }
    
    println(map)
    
    var sum=0
    for (bags <-  map) {
      if(containsShinyGoldBag(map, bags._2)) {
        sum +=1
      }
    }
    println(sum)
    println(countBags(map, "shiny gold") - 1)
  }

  def containsShinyGoldBag(map : HashMap[String, List[Bag]], input: List[Bag]): Boolean = {
    for(bag :Bag <- input) {
      if(bag.name.contains("shiny gold")) {
        return true
      } else if(map.contains(bag.name) && containsShinyGoldBag(map, map(bag.name))) {
        return true
      }
    }
    false
  }

  def countBags(map: HashMap[String, List[Bag]], bag: String): Int = {
    if (map.contains(bag)) {
      return 1 + map(bag).toStream.map(bag => bag.amountOfBags * countBags(map, bag.name)).sum
    }
    return 1
  }
  
  class Bag(val initName: String, val initValue: Int) {
    var name: String = initName
    var amountOfBags: Int = initValue
  }
}


