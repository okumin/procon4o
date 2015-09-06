
object Main {
  val hois: Array[String] = Array("N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW")
  def hoi(deg: Int): String = hois(((deg * 10 + 1125) / 2250) % 16)

  def furyoku(dis: Int): Int = math.round(dis * 10.0 / 60.0) match {
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
    val dis = scanner.nextInt()
    val (dir, w) = solve(deg, dis)
    println(dir + " " + w)
  }

  def solve(deg: Int, dis: Int): (String, Int) = {
    val w = furyoku(dis)
    val dir = if (w == 0) "C" else hoi(deg)
    (dir, w)
  }
}

