package pslab.compose2

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scala.scalajs.js.{Array}
import scala.collection.mutable.{ListBuffer}

@JSExportTopLevel("pslab_compose2_tests")
def tests() = Array(
    s"test1: ${test1()}", 
    "asd", 
    (2 * 5).toString()
).flatten

class Ex (msg: String) {
    override def toString() = msg
}

class ElementDoesntExistEx(where: Option[String]) extends Ex(
    s"ElementDoesntExistException: ${if (where.isDefined) " " + where else ""}")

class ArgumentNullEx(arg: String) extends Ex(s"${arg} is null or undefined")

case class Node (
    id: Int, 
    parent: Option[Node] = None, 
    children: Option[Seq[Node]] = None, 
    level: Option[Int] = None
)

object Node {
    def apply(id: Int, children: Option[Seq[Node]]): Node = {
        Node(id, None, children, None)
    }
}

def tree(id: Int, children: Option[Seq[Node]] = None): Node = {
    val nd = Node(id)
    val ch = children map {_.map {_.copy(parent = Some(nd))}}
    nd.copy(children = ch)
}

def depthFirst(node: Node, lvl: Int, path: ListBuffer[Node]): Unit = {
    path += node.copy(level = Some(lvl))
    node.children foreach {_.foreach {depthFirst(_, lvl + 1, path)}}
}

def test1() = {
    val tr1 = Node(
        1, 
        Some(Seq(
            Node(
                2, 
                Some(Seq(
                    Node(
                        3, 
                        None
                    ), 
                    Node(
                        4, 
                        None
                    )
                ))
            ), 
            Node(
                5, 
                None
            )
        ))
    )

    val path = ListBuffer[Node]()
    depthFirst(tr1, 0, path)
    path map {_.id} equals ListBuffer[Int](1, 2, 3, 4, 5)
}