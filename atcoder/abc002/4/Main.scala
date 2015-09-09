
import java.util.Scanner

object Main {
  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    val n = sc.nextInt()
    val m = sc.nextInt()
    val relations = (1 to m).map { _ => (sc.nextInt(), sc.nextInt()) }.toSet
    println(solve(n, relations))
  }

  def solve(n: Int, relations: Set[(Int, Int)]): Int = {
    def max(i: Int, acc: List[Int]): Int = {
      if (i > n) {
        acc.size
      } else if (acc.isEmpty || acc.forall { x => relations.contains((x, i)) }) {
        max(i + 1, acc) max max(i + 1, i :: acc)
      } else {
        max(i + 1, acc)
      }
    }
    max(1, Nil)
  }
}

