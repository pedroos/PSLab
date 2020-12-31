package pslab.mathalgos.integers

import pslab.general.{assEq}
// import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
// import scala.scalajs.js.{Array => JsArray}

def tailDigit(n: Int) = n.toString().takeRight(1).toInt

def digits(n: Int) = n.toString().split("").map {_.toInt}

def longRem(a: Int, b: Int): Int = 
  if (a >= b) 
    longRem(a - b, b) 
    else a

def divisible(a: Int, b: Int): Boolean = 
  //if (a > 10) None else 
  if (b == 2) 
    Array(0, 2, 4, 6, 8).contains(tailDigit(a)) 
  else if (b == 3)
    if (a > 10) 
      divisible(digits(a).fold(0)((a, b) => a + b), 3) 
    else 
      longRem(a, 3) == 0
  else if (b == 5) 
    Array(0, 5).contains(tailDigit(a))
  else if (b == 6)
    divisible(a, 2) && divisible(a, 3)
  else if (b == 10)
    tailDigit(a) == 0
  else false

def primeFac(n: Int) = n+1

// object TestExport {
//   @JSExportTopLevel("pslab_mathalgos_all_tests")
//   def all() = JsArray(
//     divisiblTests(), 
//     primeFactorTests()
//   ).flatten

//   @JSExportTopLevel("pslab_mathalgos_divisible_tests")
//   def divisiblTests() = JsArray(
//     Tests.longRemTests(), 
//     Tests.divisibleTests()
//   ).flatten

//   @JSExportTopLevel("pslab_mathalgos_primefactor_tests")
//   def primeFactorTests() = JsArray(
//     Tests.primeFacTests(), 
//   ).flatten
// }

object Tests {
  def all() = Array(
    longRemTests(), 
    divisibleTests(), 
    primeFacTests()
  ).flatten

  def longRemTests() = Array(
    assEq("longRem1", () => longRem(10,  3),  1), 
    assEq("longRem2", () => longRem(12,  4),  0), 
    assEq("longRem3", () => longRem(203, 17), 16)
  )

  def divisibleTests() = Array(
    assEq("divisible1",  () => divisible(20, 10), true), 
    assEq("divisible1b", () => divisible(31, 10), false), 
    assEq("divisible2",  () => divisible(4,  2),  true), 
    assEq("divisible2b", () => divisible(5,  2),  false), 
    assEq("divisible1b", () => divisible(31, 10), false), 
    assEq("divisible3",  () => divisible(15, 5),  true), 
    assEq("divisible3b", () => divisible(19, 5),  false), 
    assEq("divisible1b", () => divisible(31, 10), false), 
    assEq("divisible4",  () => divisible(12, 6),  true), 
    assEq("divisible4b", () => divisible(13, 6),  false), 
    assEq("divisible1b", () => divisible(31, 10), false), 
    assEq("divisible5",  () => divisible(18, 3),  true), 
    assEq("divisible5b", () => divisible(17, 3),  false), 
  )
  
  def primeFacTests() = Array(
    assEq("primeFac1", () => primeFac(87), 88)
  )
}