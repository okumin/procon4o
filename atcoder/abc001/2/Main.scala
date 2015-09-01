
object Main {
  def main(args: Array[String]): Unit = {
    val scanner = new java.util.Scanner(System.in)
    val m = scanner.nextInt()
    println("%02d".format(solve(m)))
  }

  def solve(m: Int): Int = m match {
    case x if x < 100 => 0
    case x if x <= 5000 => x * 10 / 1000
    case x if x <= 30000 => x / 1000 + 50
    case x if x <= 70000 => (x / 1000 - 30) / 5 + 80
    case x => 89
  }
}

