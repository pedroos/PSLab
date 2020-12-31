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

class Node[T] (
  var parent: Option[Node[T]] = None, 
  val children: Option[Seq[Node[T]]] = None, 
  val level: Option[Int] = None, 
  val v: T
) {
  children foreach {_.foreach {_.parent = Some(this)}}

  def this(v: T, children: Option[Seq[Node[T]]]) = this(None, children, None, v)
  def this(nd: Node[T], lvl: Int) = this(nd.parent, nd.children, Some(lvl), nd.v)
  override def toString() = v.toString
  override def equals(that: Any) = that match { case th: Node[T] => th.v equals v }
}

object Node {
}

// def tree[T](init: Node[T], children: Option[Seq[Node[T]]] = None) = {
//   children foreach {_.foreach {_.parent = Some(init)}}
//   // init.children = ch
//   // init.copy(children = ch)
// }

def depthFirst[T](node: Node[T], lvl: Int, path: ListBuffer[Node[T]]): Unit = {
  path += Node[T](node, lvl)
  node.children foreach {_.foreach {depthFirst(_, lvl + 1, path)} }
}

def depthFirst[T](node: Node[T], lvl: Int, path: ListBuffer[Node[T]], stop: ListBuffer[Node[T]] => Boolean): Unit = {
  path += Node[T](node, lvl)
  println(path)
  println(path.length)
  node.children foreach {_.foreach { if (!stop(path)) depthFirst(_, lvl + 1, path, stop) }}
}

object Tests {
  def allTests() = Seq(
    // eqTest1(), 
    // eqTest2(), 
    // parentTest(), 
    // dfTest1(), 
    // dfTest2()
    dfTest2b()
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
    
    val path = ListBuffer[Node[Int]]()
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
    
    val path = ListBuffer[Node[Int]]()
    depthFirst(tr1, 0, path, (pt: ListBuffer[Node[Int]]) => false)
    path map {_.v} equals Seq(1, 2, 3, 4, 5)
  }

  def dfTest2b() = {
    val tr1 = 
      Node(1, Some(Seq(
        Node(2, Some(Seq(
          Node(3, None), 
          Node(4, None)))), 
        Node(5, None))))

    val path = ListBuffer[Node[Int]]()
    depthFirst(tr1, 0, path, (pt: ListBuffer[Node[Int]]) => pt.length >= 3)
    path map {_.v} equals ListBuffer(1, 2, 3)
  }
}
