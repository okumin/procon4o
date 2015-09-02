
object Main {
  def hoi(deg: Int): String = deg match {
    case x if x <= 112 => "N"
    case x if x > 112 && x <= 337 => "NNE"
    case x if x <= 562 => "NE"
    case x if x <= 787 => "ENE"
    case x if x <= 1012 => "E"
    case x if x <= 1237 => "ESE"
    case x if x <= 1462 => "SE"
    case x if x <= 1687 => "SSE"
    case x if x <= 1912 => "S"
    case x if x <= 2137 => "SSW"
    case x if x <= 2362 => "SW"
    case x if x <= 2587 => "WSW"
    case x if x <= 2812 => "W"
    case x if x <= 3037 => "WNW"
    case x if x <= 3262 => "NW"
    case x if x <= 3487 => "NNW"
    case _ => "N"
  }
  def furyoku(w: Int): Int = math.round(w * 10.0 / 60.0) match {
    case x if x <= 2 => 0
    case x if x <= 15 => 1
    case x if x <= 33 => 2
    case x if x <= 54 => 3
    case x if x <= 79 => 4
    case x if x <= 107 => 5
    case x if x <= 138 => 6
    case x if x <= 171 => 7
    case x if x <= 207 => 8
    case x if x <= 244 => 9
    case x if x <= 284 => 10
    case x if x <= 326 => 11
    case _ => 12
  }

  def main(args: Array[String]): Unit = {
    val scanner = new java.util.Scanner(System.in)
    val deg = scanner.nextInt()
    val w = scanner.nextInt()
    val (h, f) = solve(deg, w)
    println(h + " " + f)
  }

  def solve(deg: Int, w: Int): (String, Int) = {
    val f = furyoku(w)
    val h = if (f == 0) "C" else hoi(deg)
    (h, f)
  }
}

