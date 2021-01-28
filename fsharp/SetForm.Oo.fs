namespace Pslab.Limits.SetForm

module Oo = 
    // Object-oriented Sets with Set elements.

    type Oset<'t> (elems: List<Oset<'t>>) = 
        member this.elems = elems
        
    // SetElement is a subtype of Set with a single particular value and no Sets. It initializes an empty Set only for 
    // the subtyping. Not optimal Object-Oriented form. This corresponds to an Urelement in set theory.

    type SetElement<'t> (x: option<'t>) = 
        inherit Oset<'t>([])
        member this.x = x
    
    // Alternative: the below Set Element assigns its value to its Set object instead of an individual property, thus 
    // reconciling Object Orientation. However, it recurses infinitely at runtime.

    type SetElement2<'t> (x: option<'t>) = 
        inherit Oset<'t>([SetElement2<'t>(x)])
        member this.x = x
        
    // OO Tuples as Sets. Each arity needs individual definition.
    
    type Otuple2<'t>(a: Oset<'t>, b: Oset<'t>) = 
        inherit Oset<'t>([a; Oset<'t>([a; b])])
        member this.a = a
        member this.b = b
            
    type Otuple3<'t>(a: Oset<'t>, b: Oset<'t>, c: Oset<'t>) = 
        inherit Otuple2<'t>(Otuple2<'t>(a, b), c)
                    
    type Otuple4<'t>(a: Oset<'t>, b: Oset<'t>, c: Oset<'t>, d: Oset<'t>) = 
        inherit Otuple2<'t>(Otuple2<'t>(Otuple2<'t>(a, b), c), d)

    let construct() = 
        // Non-Element Osets must be formed of Elements, which are formed of values:

        Oset<int>([
            SetElement<int>(Some(0)); 
            SetElement<int>(Some(1))
        ]) |> ignore

        // An Osets's element entails an Element element:
        
        let s = 
            Oset<int>([
                Oset<int>([
                    SetElement<int>(Some(0))
                ]); 
                Oset<int>([
                    SetElement<int>(Some(1))
                ]); 
                SetElement<int>(Some(2))
            ])
        //(s :> IPrintable).print()
        
        // Recurses infinitely
        //let s = 
        //    Oset<int>([
        //        Oset<int>([
        //            SetElement2<int>(Some(0))
        //        ]); 
        //        Oset<int>([
        //            SetElement2<int>(Some(1))
        //        ]); 
        //        SetElement2<int>(Some(2))
        //    ])
        //(s :> IPrintable).print()

        // It is possible to have multiple empty Elements at the same level in an Oset:

        Oset<int>([
            SetElement<int>(None); 
            SetElement<int>(None)
        ]) |> ignore
            
        ()

    let print() = 
        ()