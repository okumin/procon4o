package procon4s.queue

import scala.collection.generic.GenericCompanion
import scala.collection.mutable
import scala.collection.mutable.ListBuffer


object HoodMelvilleQueue {
  import scala.collection.LinearSeqOptimized
  import scala.collection.generic.{GenericTraversableTemplate, SeqFactory}
  import scala.collection.immutable.LinearSeq
  case class Queue[A](lenf: Int, working: List[A], state: RotationState[A], lenr: Int, rear: List[A])
    extends LinearSeq[A]
    with LinearSeqOptimized[A, Queue[A]]
    with GenericTraversableTemplate[A, Queue] {

    private[this] def exec(state: RotationState[A]): RotationState[A] = state match {
      case Reversing(ok, x :: xs, fs, y :: ys, rs) => Reversing(ok + 1, xs, x :: fs, ys, y :: rs)
      case Reversing(ok, Nil, fs, y :: Nil, rs) => Appending(ok, fs, y :: rs)
      case Appending(0, _, rs) => Done(rs)
      case Appending(ok, f :: fs, rs) => Appending(ok - 1, fs, f :: rs)
      case s => s
    }

    private[this] def invalidate(state: RotationState[A]): RotationState[A] = state match {
      case Reversing(ok, xs, fs, ys, rs) => Reversing(ok - 1, xs, fs, ys, rs)
      case Appending(0, _, r :: rs) => Done(rs)
      case Appending(ok, fs, rs) => Appending(ok - 1, fs, rs)
      case s => s
    }

    private[this] def execute(queue: Queue[A]): Queue[A] = {
      exec(queue.state) match {
        case Done(xs) => Queue(queue.lenf, xs, Idle[A](), queue.lenr, queue.rear)
        case newState => Queue(queue.lenf, queue.working, newState, queue.lenr, queue.rear)
      }
    }

    private[this] def check(queue: Queue[A]): Queue[A] = queue match {
      case Queue(lf, ws, Idle(), lr, rs) if lf < lr =>
        execute(execute(Queue(lf + lr, ws, Reversing(0, ws, Nil, rs, Nil), 0, Nil)))
      case q => execute(q)
    }

    override def companion: GenericCompanion[Queue] = Queue

    override def size: Int = lenf + lenr

    override def isEmpty: Boolean = size == 0

    override def head: A = peek

    override def tail: Queue[A] = dequeue._2

    def peek: A = working match {
      case Nil => sys.error("Queue is empty")
      case x :: _ => x
    }

    def peekOption: Option[A] = if (isEmpty) Some(peek) else None

    def enqueue(elem: A): Queue[A] = {
      check(Queue(lenf, working, state, lenr + 1, elem :: rear))
    }

    def dequeue: (A, Queue[A]) = working match {
      case Nil => sys.error("Queue is empty")
      case x :: xs => (x, check(Queue(lenf - 1, xs, invalidate(state), lenr, rear)))
    }
  }

  sealed abstract class RotationState[A]
  case class Reversing[A](ok: Int,
                          xs: List[A],
                          fs: List[A],
                          ys: List[A],
                          rs: List[A]) extends RotationState[A]
  case class Appending[A](ok: Int, fs: List[A], rs: List[A]) extends RotationState[A]
  case class Done[A](xs: List[A]) extends RotationState[A]
  case class Idle[A]() extends RotationState[A]

  object Queue extends SeqFactory[Queue] {
    override def newBuilder[A]: mutable.Builder[A, Queue[A]] = {
      ListBuffer.empty[A].mapResult { xs => Queue[A](xs.size, xs, Idle[A](), 0, Nil) }
    }
  }
}
