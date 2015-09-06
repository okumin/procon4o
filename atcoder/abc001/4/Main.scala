
object Main {
  def main(args: Array[String]): Unit = {
    val scanner = new java.util.Scanner(System.in)
    val n = scanner.nextInt()
    val rains = (1 to n).map { _ => 
      val line = scanner.next()
      val Array(s, e) = line.split("-")
      (s.toInt, e.toInt)
    }.toArray
    solve(rains).foreach {
      case (s, e) => println("%04d-%04d".format(s, e))
    }
  }

  def solve(rains: Array[(Int, Int)]): List[(Int, Int)] = {
    normalize(rains).foldLeft[List[(Int, Int)]](Nil) {
      case (Nil, rain) => rain :: Nil
      case ((hs, he) :: t, (s, e)) if s <= he => (hs, he max e) :: t
      case (acc, rain) => rain :: acc
    }.reverse
  }

  def normalize(rains: Array[(Int, Int)]): Array[(Int, Int)] = {
    rains.map {
      case (s, e) =>
        val start = s - s % 5
        val end = e + (5 - e % 5) % 5 match {
          case x if (x - 60) % 100 == 0 => x - 60 + 100
          case x => x
        }
        (s - s % 5, e + (5 - e % 5) % 5)
    }.sortBy {
      case (s, _) => s
    }
  }
}

