package pslab.index

import scala.collection.mutable.{Map}

class Index[VA,VB](val a: Map[Int, VA], val b: Map[Int, VB]) {
  val idxA = Map[Int, Int]()
  val idxB = Map[Int, Int]()

  if (!a.isEmpty) {} // Should do something about unindexed data?

  def add(aIdx: Int, bIdx: Int) = {
    idxA.addOne(aIdx, bIdx)
    idxB.addOne(bIdx, aIdx)
  }

  def getA(bIdx: Int) = a.getOrElse(
    idxA.getOrElse(
      idxB.getOrElse(bIdx, -1), -1), -1)
}

object Tests {
  def test1() = {
    val data1 = Map[Int, String]()
    data1.addOne(1, "AsdOne")
  
    val data2 = Map[Int, String]()
    data2.addOne(3, "AsdTwo")

    val idx = Index(data1, data2)
    idx.add(1, 3)

    // println(idx.getA(1))

    true
  }
}