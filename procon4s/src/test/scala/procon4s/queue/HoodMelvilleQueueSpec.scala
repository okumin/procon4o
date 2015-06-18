package procon4s.queue

import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.WordSpec
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import procon4s.queue.HoodMelvilleQueue.Queue

class HoodMelvilleQueueSpec extends WordSpec with GeneratorDrivenPropertyChecks {
  sealed abstract class Command
  case object Peek extends Command
  case class Enqueue(x: Int) extends Command
  case object Dequeue extends Command
  object Command {
    implicit val arbitrary: Arbitrary[Command] = Arbitrary {
      Gen.oneOf[Command](Peek, Arbitrary.arbitrary[Int].map(Enqueue), Dequeue)
    }
  }

  "A HoodMelvilleQueue" should {
    "behave like a FIFO queue" in {
      forAll { (elems: List[Int], commands: List[Command]) =>
        val zero = (Queue(elems: _*): Queue[Int], scala.collection.immutable.Queue(elems: _*))
        val (q, m) = commands.foldLeft(zero) {
          case ((queue, model), Peek) if queue.isEmpty && model.isEmpty =>
            (queue, model)
          case ((queue, model), Peek) =>
            assert(queue.peek === model.front)
            (queue, model)
          case ((queue, model), Enqueue(x)) =>
            val newQueue = queue.enqueue(x)
            val newModel = model.enqueue(x)
            assert(newQueue.size === newModel.size)
            assert(newQueue.isEmpty === newModel.isEmpty)
            assert(newQueue === newModel)
            (newQueue, newModel)
          case ((queue, model), Dequeue) if queue.isEmpty && model.isEmpty =>
            (queue, model)
          case ((queue, model), Dequeue) =>
            val (qx, newQueue) = queue.dequeue
            val (mx, newModel) = model.dequeue
            assert(qx === mx)
            assert(newQueue.size === newModel.size)
            assert(newQueue.isEmpty === newModel.isEmpty)
            assert(newQueue === newModel)
            (newQueue, newModel)
        }
        assert(q === m)
      }
    }
  }
}
