namespace Pslab.Limits.Tests

open System
open Microsoft.VisualStudio.TestTools.UnitTesting
//open Pslab.Limits.SetForm1

[<TestClass>]
type SetForm1Tests () = 
    class end
    //[<TestMethod>]
    //member this.SingletonToStringTest() = 
    //    let s = new Singleton<int>(Some(1))
    //    Assert.AreEqual("1", s.ToString())
        
    //[<TestMethod>]
    //member this.EmptyToStringTest() = 
    //    let s = new Singleton<int>(None)
    //    Assert.AreEqual("E", s.ToString())
        
    //[<TestMethod>]
    //member this.SetToStringTest() = 
    //    let s = new Set<int>([
    //        new Singleton<int>(Some(1)); 
    //        new Singleton<int>(Some(2))
    //    ])
    //    Assert.AreEqual("{1, 2}", s.ToString())

    //[<TestMethod>]
    //member this.UnarySetToStringTest() = 
    //    let s = new Set<int>([
    //        new Set<int>([
    //            new Set<int>([
    //                new Singleton<int>(Some(1))
    //            ])
    //        ])
    //    ])
    //    Assert.AreEqual("{{{1}}}", s.ToString())

    //[<TestMethod>]
    //member this.OrderedPairToStringTest() = 
    //    let p = new OrderedPair<int>(
    //        new Singleton<int>(Some(1)), 
    //        new Singleton<int>(Some(2))
    //    )
    //    Assert.AreEqual("{1, {1, 2}}", p.ToString())

    //[<TestMethod>]
    //member this.OrderedTripleToStringTest() = 
    //    let p = new OrderedTriple<int>(
    //        new Singleton<int>(Some(1)), 
    //        new Singleton<int>(Some(2)), 
    //        new Singleton<int>(Some(3))
    //    )
    //    Assert.AreEqual("{{1, {1, 2}}, {{1, {1, 2}}, 3}}", p.ToString())