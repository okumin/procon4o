
import java.util.Scanner

object Main {
  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    val s = sc.next()
    val t = sc.next()
    val at = Set('@', 'a', 't', 'c', 'o', 'd', 'e', 'r')
    val win = (s zip t).forall {
      case ('@', x) => at.contains(x)
      case (x, '@') => at.contains(x)
      case (a, b) => a == b
    }
    println(if (win) "You can win" else "You will lose")
  }
}

