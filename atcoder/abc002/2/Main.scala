
import java.util.Scanner

object Main {
  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    val w = sc.next()
    val boin = Set('a', 'i', 'u', 'e', 'o')
    println(w.filter { x => !boin.contains(x) })
  }
}

