namespace Pslab.Limits.SetForm

module Mixed = 
    // Stst types consume Oo types in their recursive position, thus mixing Stst and Oo.
    // The consequence is that the hierarchy is OO + STST simultaneously.
    // It can be traversed via inheritance, or via pattern matching on all non-terminal nodes.
    // Only terminal nodes are either OO or STST, and this determines their reachability (by inheritance or pattern 
    // matching).

    type Oset<'t> (elems: List<Sset<'t>>) = 
        member this.elems = elems
          
    and SetElement<'t> (x: option<'t>) = 
        inherit Oset<'t>([])
        member this.x = x

    and Sset<'t> = T of option<'t> | S of Oset<'t> // <--- Admits Sets and Urelements (and Tuples via inheritance)
    
    // Unlike in non-mixed Stst, Stuples are also Sets. The only difference between Sets and Tuples here is their 
    // cardinality.

    type Otuple2<'t>(a: Sset<'t>, b: Sset<'t>) = 
        inherit Oset<'t>([
            a; 
            Sset.S(Oset<'t>([
                a; 
                b
            ]))
        ])
        member this.a = a
        member this.b = b
        
    type Otuple3<'t>(a: Sset<'t>, b: Sset<'t>, c: Sset<'t>) = 
        inherit Otuple2<'t>(
            Sset.S(Otuple2<'t>(a, b) :> Oset<'t>), 
            c
        )
                    
    and Otuple4<'t>(a: Sset<'t>, b: Sset<'t>, c: Sset<'t>, d: Sset<'t>) = 
        inherit Otuple2<'t>(
            Sset.S(Otuple2<'t>(
                Sset.S(Otuple2<'t>(a, b) :> Oset<'t>), 
                c
            ) :> Oset<'t>), 
            d
        )
        
    // Stuple2.T ends up not castable into Sset2.T, so Stuple2.T can't be used; Sset2.T is the canonical Stst terminus.
    
    // Stuple2.S also can't be used, except as outer envelope type to an Otuple hierarchy; inner Tuples are Ssets, not 
    // Stuples. So abdicating Stuple entirely and adopting Sset for Stst canonicaly.

    //and Stuple2<'t> = T of option<'t> | S of Otuple2<'t> // <--- Admits any size Otuple

    // Unification: defines a single Stst type for Sets, Tuples, or any other. (Just for kicks.)
    
    // However, single Stst only differs from canonical Sset by being specific about its T component, which is a 
    // generic type; but as Otuple (and eventual others) should be children to Sset, this is in the wrong direction; 
    // Stst T being Sset is more generic, obviating unification.
    
    //type Stst<'otype, 't> = T of option<'t> | S of 'otype // <--- Variance on S would eliminate the need to cast 'otype 
    //    // subtypes as exactly 'otype. But the cast can still be made in user code, so acceptable for now.
    
    //and OsetUnf<'t> (elems: List<Stst<OsetUnf<'t>, 't>>) = 
    //    member this.elems = elems
            
    //type SetElementUnf<'t> (x: option<'t>) = 
    //    inherit OsetUnf<'t>([])
    //    member this.x = x

    // So, things are either Osets or Otuples, with Otuples being Osets and either being Ssets.

    let construct() =         
        Sset.S(Oset<int>([
            Sset.T(None); 
            Sset.T(Some(1))
        ])) |> ignore // Two STST terminus
        
        Sset.S(Oset<int>([
            Sset.S(SetElement(None)); 
            Sset.S(SetElement(Some(1)))
        ])) |> ignore // Two OO terminus
        
        Sset.S(Oset<int>([
            Sset.S(SetElement(None)); 
            Sset.T(Some(1))
        ])) |> ignore // One STST and one OO terminus

        Sset.S(Oset<int>([
            Sset.S(SetElement(None)); 
            Sset.S(Oset<int>([
                Sset.S(SetElement(Some(1)))
            ]))
        ])) |> ignore
        
        Sset.S(Oset<int>([
            Sset.T(None); 
            Sset.S(Oset<int>([
                Sset.T(None)
            ]))
        ])) |> ignore

        Oset<int>([
            Sset.T(None)
        ]) |> ignore

        Otuple2<int>(
            Sset.T(None), 
            Sset.T(None)
        ) |> ignore
        
        Sset.S(Otuple2<int>(
            Sset.T(None), 
            Sset.T(None)
        )) |> ignore
        
        Sset.S(Otuple2<int>(
            Sset.S(Oset<int>([Sset.T(None)])), 
            Sset.T(None)
        )) |> ignore
        
        Sset.S(Otuple2<int>(
            Sset.S(Otuple2<int>(
                Sset.T(None), 
                Sset.S(Oset<int>([Sset.T(None)]))
            )), 
            Sset.T(None)
        )) |> ignore

        let _: Sset<int> = 
            Sset.S(Otuple2<int>(
                Sset.S(Otuple2<int>(
                    Sset.T(Some(1)), 
                    Sset.T(Some(2))
                )), 
                Sset.S(Otuple2<int>(
                    Sset.S(Otuple2<int>(
                        Sset.T(Some(3)), 
                        Sset.T(Some(4))
                    )), 
                    Sset.T(Some(4))
                ))
            ))

        ()

    let print() = 
        ()