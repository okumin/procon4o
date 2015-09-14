
import java.util.Scanner

object Main {
  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    val n = sc.nextInt()
    val k = sc.nextInt()
    val rs = (1 to n).map(_ => sc.nextInt())
    println(solve(k, rs))
  }

  def solve(k: Int, rs: Seq[Int]): Double = {
    rs.sorted.reverse.take(k).zipWithIndex.foldLeft(0.0) {
      case (acc, (r, i)) => acc + math.pow(0.5, i + 1) * r
    }
  }
}

