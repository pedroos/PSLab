namespace Pslab.Limits.SetForm

module MixedRename = 
    type Set<'t> (elems: List<Rec<'t>>) = 
        member this.elems = elems
          
    and SetElement<'t> (x: option<'t>) = 
        inherit Set<'t>([])
        member this.x = x

    and Rec<'t> = Su of Set<'t> | Te of option<'t>

    // Use Pair Set form {a,b} here, instead of {{a},{a,b}}, for straightforwardness of iterating as Sets.
        
    type Pair<'t>(a: Rec<'t>, b: Rec<'t>) = 
        inherit Set<'t>([
            a; 
            b
        ])
        member this.a = a
        member this.b = b
        
    type Tuple3<'t>(a: Rec<'t>, b: Rec<'t>, c: Rec<'t>) = 
        inherit Pair<'t>(
            Su(Pair<'t>(
                a, 
                b
            )), 
            c
        )
                    
    and Tuple4<'t>(a: Rec<'t>, b: Rec<'t>, c: Rec<'t>, d: Rec<'t>) = 
        inherit Pair<'t>(
            Su(Pair<'t>(
                Su(Pair<'t>(
                    a, 
                    b
                )), 
                c
            )), 
            d
        )
        
    // An Universal Rec to try to avoid a runtime cast from Pair back to Set, for example, when iterating over Pairs 
    // as Pairs. The exact type of the recursive component is specified.

    type Uset<'t> (elems: List<Urec<Uset<'t>, 't>>) = 
        member this.elems = elems

    and Urec<'su, 't> = Usu of 'su | Ute of option<'t> 
    
    // Doesn't work: lack of covariance means Urec<Uset> is not a supertype of Urec<Pair>. 
    // OCaml supports covariance.

    //type Upair<'t>(a: Urec<Upair<'t>, 't>, b: Urec<Upair<'t>, 't>) = 
    //    inherit Uset<'t>([
    //        a :> Urec<Uset<'t>, 't>; 
    //        b :> Urec<Uset<'t>, 't>
    //    ])
    //    member this.a = a
    //    member this.b = b

    let construct() =         
        Su(Set<int>([
            Te(None); 
            Te(Some(1))
        ])) |> ignore
        
        Su(Set<int>([
            Su(SetElement(None)); 
            Su(SetElement(Some(1)))
        ])) |> ignore
        
        Su(Set<int>([
            Su(SetElement(None)); 
            Te(Some(1))
        ])) |> ignore

        Su(Set<int>([
            Su(SetElement(None)); 
            Su(Set<int>([
                Su(SetElement(Some(1)))
            ]))
        ])) |> ignore
        
        Su(Set<int>([
            Te(None); 
            Su(Set<int>([
                Te(None)
            ]))
        ])) |> ignore

        Set<int>([
            Te(None)
        ]) |> ignore

        Pair<int>(
            Te(None), 
            Te(None)
        ) |> ignore
        
        Su(Pair<int>(
            Te(None), 
            Te(None)
        )) |> ignore
        
        Su(Pair<int>(
            Su(Set<int>([Te(None)])), 
            Te(None)
        )) |> ignore
        
        Su(Pair<int>(
            Su(Pair<int>(
                Te(None), 
                Su(Set<int>([Te(None)]))
            )), 
            Te(None)
        )) |> ignore

        let _: Rec<int> = 
            Su(Pair<int>(
                Su(Pair<int>(
                    Te(Some(1)), 
                    Te(Some(2))
                )), 
                Su(Pair<int>(
                    Su(Pair<int>(
                        Te(Some(3)), 
                        Te(Some(4))
                    )), 
                    Te(Some(5))
                ))
            ))

        ()

    let print() = 
        ()