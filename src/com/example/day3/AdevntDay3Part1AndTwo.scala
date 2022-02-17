package day3

import java.io.File

import day2.AdventDay2Part2.getClass

import scala.io.Source

object AdevntDay3Part1AndTwo {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
    val f = new File(getClass.getClassLoader.getResource("com/example/day3/inputDay3.txt").getPath)
    val input = Source.fromFile(f).getLines.toList

    val testInput = "..##.......\n#...#...#..\n.#....#..#.\n..#.#...#.#\n.#...##..#.\n..#.##.....\n.#.#.#....#\n.#........#\n#.##...#...\n#...##....#\n.#..#...#.#"
    val  list = input // testInput.split("\n").toList
    val lengthString = list(0).length
    
    
    println(searchTrees(list, 3, 1))
    println(searchTrees(list, 1, 1)*searchTrees(list, 3, 1)*searchTrees(list, 5, 1)*searchTrees(list, 7, 1)*searchTrees(list, 1, 2))
    
    // 943764128 -> incorrect why?
  }

  def searchTrees(trees: List[String], dx: Int, dy: Int): Long = {
    var y = dy
    var x = dx
    val len = trees(0).length
    var count = 0
    while ( {
      y < trees.size
    }) {
      if (trees(y).charAt(x % len) == '#') count += 1
      y += dy
      x += dx
    }
    count
  }
  
}
