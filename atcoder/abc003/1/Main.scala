
import java.util.Scanner

object Main {
  def main(args: Array[String]): Unit = {
    val n = new Scanner(System.in).nextInt()
    println((10000 + n * 10000) / 2)
  }
}

