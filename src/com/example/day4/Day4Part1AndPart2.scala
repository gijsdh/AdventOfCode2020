package day4

import java.io.File



import scala.io.Source

object Day4Part1AndPart2 {
  def main(args: Array[String]): Unit = {
    val f = new File(getClass.getClassLoader.getResource("inputDay4.txt").getPath)
    val input = Source.fromFile(f).mkString
    val testinput = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\nbyr:1937 iyr:2017 cid:147 hgt:183cm\n\niyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\nhcl:#cfa07d byr:1929\n\nhcl:#ae17e1 iyr:2013\neyr:2024\necl:brn pid:760753108 byr:1931\nhgt:179cm\n\nhcl:#cfa07d eyr:2025 pid:166559648\niyr:2011 ecl:brn hgt:59in"
    
    val parsedInput = input.split("\r\n\r\n").toList
    val parsed = parsedInput.toStream.map(_.replace("\r\n", " ")).toList
    
    var count = 0
    for {a <- parsed} {
      var map = a.split(" ").map(text => text.trim.split(":")).map(a => (a(0) -> a(1))).toMap
     // println(map)
      
      if (map.size == 8 && checkPasport(map)) {
        count += 1
        println(map("hgt"));
      } else if(map.size == 7 && !map.contains("cid") && checkPasport(map)) {
        println(map("hgt"));
        count += 1
      }
    }
    
    println(count)
  }

  //153
  def checkPasport(map: Map[String, String]): Boolean = {

    val byr = map("byr").toInt
    val year = map("iyr").toInt
    val eyr = map("eyr").toInt
    val height = map("hgt")
    

    if(byr > 2002 || byr < 1920) {
   //   println(byr)
      return false
    }
    
    if(year > 2020 || year < 2010) {
     // println(year)
      return false
    }
    
    if(eyr > 2030 || eyr < 2020) {
    //  println(eyr)
      return false
    }
    
    if(!height.contains("in") && !height.contains("cm")) {
      return false;
    }
    
    if (height.contains("in")) {
      val heightInt = height.replace("in", "").toInt
      if (heightInt < 59 || heightInt > 76) {
      //  println(heightInt)
        return false;
      }
    }

    if (height.contains("cm")) {
      val heightInt = height.replace("cm", "").toInt
      if (heightInt < 150 || heightInt > 193) {
       // println(heightInt)
        return false;
      }
    }
    
    if(!"amb,blu,brn,gry,grn,hzl,oth".split(",").toSet.toSet.contains(map("ecl")) ) {
     // println(map("ecl"))
      return false
    }
    
    val hcl = map("hcl")
    if (!hcl.matches("#[0-9a-f]{6}")) {
     // println(hcl)
      return false
    }

    val pid = map("pid")
    if(pid.length != 9) {
       //println(pid)
       return false
    }
    true
  }
}
