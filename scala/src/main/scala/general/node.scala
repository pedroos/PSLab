package pslab.general.node

// import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
// import scala.scalajs.js.{Array}
import scala.collection.mutable.{ListBuffer}

// @JSExportTopLevel("pslab_compose2_tests")
// def tests() = Array(
//     s"test1: ${test1()}", 
//     "asd", 
//     (2 * 5).toString()
// ).flatten

// class ElementDoesntExistEx(where: Option[String]) extends Ex(
//     s"ElementDoesntExistException: ${if (where.isDefined) " " + where else ""}")

class Node (
  var parent: Option[Node] = None, 
  val children: Option[Seq[Node]] = None, 
  val level: Option[Int] = None, 
  val v: Any
) {
  children foreach {_.foreach {_.parent = Some(this)}}

  def this(v: Any, children: Option[Seq[Node]]) = this(None, children, None, v)
  def this(nd: Node, lvl: Int) = this(nd.parent, nd.children, Some(lvl), nd.v)
  override def toString() = v.toString
  override def equals(that: Any) = that match { case th: Node => th.v equals v }
}

object Node {
}

// def tree(init: Node, children: Option[Seq[Node]] = None) = {
//   children foreach {_.foreach {_.parent = Some(init)}}
//   // init.children = ch
//   // init.copy(children = ch)
// }

def depthFirst(node: Node, lvl: Int, path: ListBuffer[Node]): Unit = {
  path += Node(node, lvl)
  node.children foreach {_.foreach {depthFirst(_, lvl + 1, path)} }
}

object Tests {
  def allTests() = Seq(
    eqTest1(), 
    eqTest2(), 
    parentTest(), 
    dfTest1(), 
    dfTest2()
  )

  def eqTest1() = {
    val nd1 = Node(0, None)
    val nd2 = Node(1, None)
    !(nd1 equals nd2)
  }

  def eqTest2() = {
    val nd1 = Node(1, None)
    val nd2 = Node(1, None)
    nd1 equals nd2
  }

  def parentTest() = {
    val tr1 = 
      Node(1, Some(Seq(
        Node(2, Some(Seq(
          Node(3, None), 
          Node(4, None)))), 
        Node(5, None))))
    
    tr1.parent equals None // 1
    tr1.children.head.head.parent.head equals tr1  // 2
    tr1.children.head.head.children.head.head.parent.head equals tr1.children.head.head // 3
    tr1.children.head.head.children.head.drop(1).head.parent.head equals tr1.children.head.head // 4
    tr1.children.head.drop(1).head.parent.head equals tr1 // 5
  }

  def dfTest1() = {
    val tr1 = 
      Node(1, Some(Seq(
        Node(2, Some(Seq(
          Node(3, None), 
          Node(4, None)))), 
        Node(5, None))))
    
    val path = ListBuffer[Node]()
    depthFirst(tr1, 0, path)
    path map {_.v} equals Seq(1, 2, 3, 4, 5)
  }

  def dfTest2() = {
    val tr1 = 
      Node(1, Some(Seq(
        Node(2, Some(Seq(
          Node(3, None), 
          Node(4, None)))), 
        Node(5, None))))
    
    val path = ListBuffer[Node]()
    depthFirst(tr1, 0, path)
    path map {_.v} equals Seq(1, 2, 3, 4, 5)
  }
}
