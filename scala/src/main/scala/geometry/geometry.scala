package pslab.geom

import pslab.general.{assEq}

/** Decides variables */
class Universe {
  var pointCount: Int = 0
  def nextVar():Char = {
    val chr = (97 + pointCount).asInstanceOf[Char]
    pointCount += 1
    chr
  }
}

/** The default universe */
var universe: Universe = Universe();

/** Represents a point */
class Point private (val name: Char) {
  def this() = this(universe.nextVar())
  def getName() = name

  /** A point is equal to another point if it posesses the same variable */
  override def equals(that: Any) = that match { case p: Point => p.name == name }
}

/** 
Represents a segment. 
A segment can also represent abstract distances.
*/
class Segment(val a: Point, val b: Point) {
  val length: Set[Point] | Int = Set(a, b)

  /** Finds the midpoint of a segment */
  def midPt(): Point = {

    val circ1 = Circle.crc(a, b)

    val circ2 = Circle.crc(b, a)

    val inters = Intersection.circles(circ1, circ2);

    // val midLine = Segment(inters._1, inters._2)

    Point()
  }

  override def equals(that: Any) = that match { case s: Segment => 
    (s.a == a && s.b == b) || 
    (s.b == a && s.a == b) }
}

/** Represents a center and radius circle (crc) */
class Circle (val center: Point, val radiusPt: Point) 
{
  val radiusSeg = Segment(center, radiusPt)

  /** A crc is equal to another crc if it has the same center and radius */
  override def equals(that: Any) = that match { case c: Circle => 
    c.center == center && c.radiusSeg.length == radiusSeg.length }
}

object Circle {
  /** Draws a circle with specified center and radius points */
  def crc(center: Point, radiusPt: Point): Circle = 
    Circle(
      center = center, 
      radiusPt = radiusPt
    )
}

object Intersection {
  /** Kinds of intersections between two circles */
  enum CrcIntersection { case 
    /** There is no intersection */
    Null, 
    /** The intersection is any of the circles as they're equal */
    Circle, 
    /** The intersection is a pair of points */
    Partial, 
    /** The intersection is the smaller radius circle */
    Full
  }

  /** 
  Intersects two circles.
  */
  def circles(a: Circle, b: Circle): (CrcIntersection, Option[(Point, Point) | Circle]) = {
    // TODO:
    
    if (a == b) return (CrcIntersection.Circle, Some(a))

    (CrcIntersection.Partial, Some(Point(), Point()))
  }
}

// Square a number.
// See http://github.com/pedroos/Cursos/tree/master/geometry/Irrationality_of_Square_Root_of_Two.pdf

/** Squares a number */
def squareNumber(n: Segment, sideTwo: Boolean = true): Segment = {
  // 1. Find the segment's middle point.
  // Depends on:
  // - Drawing a circle with a center on a point
  // - Intersecting two circles

  val mid = n.midPt()
  
  // val c = Point()

  // Segment(sidePt, c)

  Segment(Point(), Point())
}

object Tests {
  def all() = Array(
    universeTests(), 
    equalityTests()
  ).flatten

  def universeTests() = Array(
    assEq("universe1", () => {universe = Universe(); val a = Point(); a.getName()}, 'a'), 
    assEq("universe1b", () => {universe = Universe(); val a = Point(); val b = Point(); b.getName()}, 'b')
  )

  def equalityTests() = Array(
    assEq("pointEq", () => {val a = Point(); val b = Point(); a != b}, true), 
    assEq("pointEq2", () => {val a = Point(); a == a}, true), 
    assEq("segEq", () => {val a = Point(); val b = Point(); Segment(a, b) == Segment(a, b)}, true), 
    assEq("segEq3", () => {val a = Point(); val b = Point(); Segment(a, b) == Segment(b, a)}, true), 
    assEq("segEq4", () => {val a = Point(); val b = Point(); val c = Point(); Segment(a, b) != Segment(a, c)}, true), 
  )
}