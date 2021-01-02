package pslab.zon.vargraph

import pslab.general.node.{Node, depthFirst}
import scala.collection.mutable.{ListBuffer}

enum Ftype(val lvl: Int) extends Ordered[Ftype] {
  case Const     extends Ftype(100)
  case Finite    extends Ftype(200)
  case Comp      extends Ftype(300)
  case Symb      extends Ftype(400)

  def compare(that: Ftype) = this.lvl - that.lvl

  val GeqFinite = Ftype.Finite | Ftype.Comp | Ftype.Symb // PAROU AQUI
}

enum FtypeError { case 
  A
}

object Universe {

}

case class Fnode(ftype: Ftype, name: String)

object Fpaths {
  def pathType(nd: Node) = {
    val lb = ListBuffer[Node]()
    depthFirst(nd, 0, lb)
    lb.maxBy({_.v.asInstanceOf[Fnode].ftype.lvl}).v.asInstanceOf[Fnode].ftype
  }

  // def complexity(nd: Node): Ftype | FtypeError = {
  //   val lb = ListBuffer[Node]()
  //   depthFirst(nd, 0, lb)
  //   println(lb)
  
  //   FtypeError.A
  // }
}

object Tests {
  def allTests() = Seq(
    // nodeTest1(), 
    // ftypeOrderTest1(), 
    // ftypeOrderTest2(), 
    ftypeOrderTest3(), 
    // ftypePathTypeTest1(), 
    // ftypePathTypeTest2(),     
    // ftypePathTypeTest3(), 
    // ftypePathTypeTest4()
  )

  def nodeTest1() = {
    val tr1 = 
      Node(Fnode(Ftype.Const, ""), Some(Seq(
        Node(Fnode(Ftype.Const, ""), Some(Seq(
          Node(Fnode(Ftype.Finite, ""), None), 
          Node(Fnode(Ftype.Comp, ""), None)))), 
        Node(Fnode(Ftype.Symb, ""), None))))

    val path = ListBuffer[Node]()
    depthFirst(tr1, 0, path)
    path map {_.v.asInstanceOf[Fnode].ftype} equals Seq(Ftype.Const, Ftype.Const, Ftype.Finite, Ftype.Comp, Ftype.Symb)
  }

  def ftypeOrderTest1() = 
    Ftype.Const < Ftype.Finite && 
    Ftype.Finite < Ftype.Comp && 
    Ftype.Comp < Ftype.Symb

  def ftypeOrderTest2() = 
    Ftype.Const <= Ftype.Const && 
    Ftype.Const <= Ftype.Finite && 
    Ftype.Const <= Ftype.Comp && 
    Ftype.Const <= Ftype.Symb && 
    Ftype.Symb >= Ftype.Const && 
    Ftype.Symb >= Ftype.Finite && 
    Ftype.Symb >= Ftype.Comp && 
    Ftype.Symb >= Ftype.Symb

  def ftypeOrderTest3() = 
    Ftype.GeqFinite >= Ftype.Finite && 
    Ftype.GeqFinite < Ftype.Comp && 
    Ftype.LeqComp <= Ftype.Comp && 
    Ftype.LeqComp > Ftype.Finite

  def ftypePathTypeTest1() = {
    val p = 
      Node(Fnode(Ftype.Const, ""), None)
    Fpaths.pathType(p) equals Ftype.Const
  }

  def ftypePathTypeTest2() = {
    val p = 
      Node(Fnode(Ftype.Const, ""), Some(Seq(
        Node(Fnode(Ftype.Finite, ""), None))))
    Fpaths.pathType(p) equals Ftype.Finite
  }

  def ftypePathTypeTest3() = {
    val p = 
      Node(Fnode(Ftype.Comp, ""), Some(Seq(
        Node(Fnode(Ftype.Finite, ""), None))))
    Fpaths.pathType(p) equals Ftype.Comp
  }

  def ftypePathTypeTest4() = {
    val p = 
      Node(Fnode(Ftype.Symb, ""), Some(Seq(
        Node(Fnode(Ftype.Symb, ""), None))))
    Fpaths.pathType(p) equals Ftype.Symb
  }

  object Div1 { 
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

    val tailDigitVars = 
      Node(Fnode(Ftype.Finite, "tailDigit"), Some(Seq(
        Node(Fnode(Ftype.Finite, "str"), Some(Seq(
          Node(Fnode(Ftype.Finite, "right"), Some(Seq(
            Node(Fnode(Ftype.Finite, "int"), None))))))))))
    
    // val vars2 = 
    //   Node(Ftype.Finite, Some(Seq(              // digits
    //     Node(Ftype.Finite, Some(Seq(            // str
    //       Node(Ftype.Finite, Some(Seq(          // chars
    //         Node(Ftype.Finite, None))))))))))   // int
    
    val longRemVars = 
      Node(Fnode(Ftype.Finite, "longRem"), Some(Seq(
        Node(Fnode(Ftype.Finite, ">="), None))))
  }
  
  // def complexTest1() = {
  //   val complex = complexity(Div1.tailDigitVars)
  //   println(complex)
  // }
}