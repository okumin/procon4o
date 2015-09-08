
import java.util.Scanner

object Main {
  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    val x1, y1, x2, y2, x3, y3 = sc.nextInt()
    println(solve(x1, y1, x2, y2, x3, y3))
  }

  def solve(x1: Int, y1: Int, x2: Int, y2: Int, x3: Int, y3: Int): Double = {
    val a = x2 - x1
    val b = y2 - y1
    val c = x3 - x1
    val d = y3 - y1
    math.abs(a * d - b * c) / 2.0
  }
}

