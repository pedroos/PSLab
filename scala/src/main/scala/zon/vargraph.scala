package pslab.zon.vargraph

import pslab.general.node.{Node, depthFirst}
import scala.collection.mutable.{ListBuffer}

enum Ftype { case 
  Const, 
  Finite, 
  Comp, 
  Symb
}

enum FtypeError { case 
  A
}

object Universe {

}

def complexity(n: Node[Ftype]): Ftype | FtypeError = {
  FtypeError.A
}

object Tests {
  def test1() = {
    val tr1 = 
      Node(Ftype.Const, Some(Seq(
        Node(Ftype.Const, Some(Seq(
          Node(Ftype.Finite, None), 
          Node(Ftype.Comp, None)))), 
        Node(Ftype.Symb, None))))

    val path = ListBuffer[Node[Ftype]]()
    depthFirst(tr1, 0, path)
    !(path map {_.v} equals ListBuffer(Ftype.Const, Ftype.Const, Ftype.Finite, Ftype.Comp, Ftype.Symb))
  }

  /**
   * finite tailDigit(n) = n.str.right(1).int
   *
   * finite digits(n) = n.str.chars => int
   *
   * finite longRem(a, b) = longRem(a - b, b) if a >= b else a      // Recursive
   *
   * finite divisible(a, b) =
   *  b 2 ? 
   *    tailDigit(a) in {0, 2, 4, 6, 8} : 
   *  b 3 ? 
   *    (if a > 10 then 
   *      divisible(digits(a).sum, 3) 
   *    else 
   *      longRem(a, 3) 0) :  
   *  b 5 ? 
   *    tailDigit(a) in {0, 5} :  
   *  b 6 ? 
   *    divisible(a, 2) and divisible(a, 3) :  
   *  b 10 ? 
   *    tailDigita(a) 0 : 
   *  false
   */
  
  object Div1 {
    val tailDigitVars = 
      Node(Ftype.Finite, Some(Seq(              // tailDigit
        Node(Ftype.Finite, Some(Seq(            // str
          Node(Ftype.Finite, Some(Seq(          // right
            Node(Ftype.Finite, None))))))))))   // int
    
    // val vars2 = 
    //   Node(Ftype.Finite, Some(Seq(              // digits
    //     Node(Ftype.Finite, Some(Seq(            // str
    //       Node(Ftype.Finite, Some(Seq(          // chars
    //         Node(Ftype.Finite, None))))))))))   // int
    
    val longRemVars = 
      Node(Ftype.Finite, Some(Seq(              // longRem
        Node(Ftype.Finite, None))))             // >=
  }
  
  def div1Test1() = {
    
  }
}