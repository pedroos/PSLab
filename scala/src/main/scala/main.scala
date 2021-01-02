package pslab.main

import pslab.general.{joinTests}
import pslab.general.node.{Tests => NodeTests}
import pslab.mathalgos.integers.{Tests => MaIntTests}
import pslab.mathalgos.vectors.{Tests => MaVecTests}
import pslab.geom.{Tests => GeomTests}
import pslab.index.{Tests => IndexTests}
import pslab.zon.vargraph.{Tests => ZonVarGraphTests}

@main def Main() =
  println(Seq(
    // joinTests(MaIntTests.all()), 
    // joinTests(MaVecTests.all()), 
    // joinTests(GeomTests.all()), 
    // IndexTests.test1(), 
    ZonVarGraphTests.allTests()
    // NodeTests.allTests()
  ).flatten.mkString(java.lang.System.lineSeparator))