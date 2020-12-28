package pslab.main

import pslab.general.{joinTests}
import pslab.mathalgos.integers.{Tests => MaIntTests}
import pslab.mathalgos.vectors.{Tests => MaVecTests}
import pslab.geom.{Tests => GeomTests}

@main def Main() = {
  //println(joinTests(MaIntTests.all()))
  println(joinTests(MaVecTests.all()))
  // println(joinTests(GeomTests.all()))
}