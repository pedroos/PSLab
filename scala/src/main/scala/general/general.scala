package pslab.general

// Default test equality checking procedure
def assEq[R](testName: String, result: () => R, expected: R): String = {
  val res = result()
  if (res != expected) 
    s"${testName} ${res} != ${expected}".padTo(30, ' ') + " (FAILED)" 
  else 
    s"${testName} == ${expected}".padTo(30, ' ') + " (OK)"
}

def joinTests(testRes: Array[String]): String = testRes.mkString(java.lang.System.lineSeparator)

class Ex (msg: String) {
    override def toString() = msg
}