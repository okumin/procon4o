package procon4s.unionfind

object UnionFind {
  class UnionFind(n: Int) {
    val par: Array[Int] = (0 until n).toArray
    val rank: Array[Int] = Array.fill(n)(0)

    def root(x: Int): Int = par(x) match {
      case `x` => x
      case v =>
        par(x) = root(v)
        v
    }

    def same(x: Int, y: Int): Boolean = root(x) == root(y)

    def unite(x: Int, y: Int): Unit = {
      (root(x), root(y)) match {
        case (a, b) if a == b =>
        case (a, b) if rank(a) < rank(b) => par(a) = b
        case (a, b) if rank(a) > rank(b) => par(b) = a
        case (a, b) if rank(a) == rank(b) =>
          par(a) = b
          rank(b) += 1
      }
    }

    override def toString: String = s"par = $par, rank = $rank"
  }
}
