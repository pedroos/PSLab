package pslab.main

import pslab.general.{joinTests}
import pslab.mathalgos.{Tests => MaTests}
import pslab.geom.{Tests => GeomTests}

@main def Main() = {
  //println(joinTests(MaTests.all()))
  println(joinTests(GeomTests.all()))
}