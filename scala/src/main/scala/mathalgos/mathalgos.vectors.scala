package pslab.mathalgos.vectors

import pslab.general.{assEq}
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scala.scalajs.js.{Array => JsArray}

case class Vec2 (a: Int, b: Int) extends Numeric[Int] {
  def +(that: Vec2) = Vec2(a + that.a, b + that.b)
}

// def sumManyVecs(vs: List[Vec2]): Vec2 = vs.sum

object Tests {
  @JSExportTopLevel("pslab_mathalgos_vectors_tests")
  def exportAll() = JsArray(
    all()
  )

  def all() = Array(
    sumTests()
  ).flatten

  // Remaining value tests
  def sumTests() = Array(
    assEq("sum1", () => Vec2(2, 3) + Vec2(3, 4), Vec2(5, 7))
    assEq("sum2", () => {Seq(
      Vec2(2, 1), 
      Vec2(3, 1), 
      Vec2(1, 2)
    ).sum()}, Vec2(6, 4))
    // assEq("longRem2", () => longRem(12,  4),  0), 
    // assEq("longRem3", () => longRem(203, 17), 16)
  )
}