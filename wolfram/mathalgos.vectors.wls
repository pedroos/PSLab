On@Assert

(* Test that the sum vector of the vectors composing the path is 
   the last point in the path

   Expected: (6, 4)
*)

Assert[
    Total@{{2,1},{3,1},{1,2}} == {6,4}
]

(* Test that the cumulative sum of the vectors in the path 
   yield the points in the path

   Expected: (2,1), (5, 2) and (6, 4)
*)

Assert[
    Accumulate@{{2,1},{3,1},{1,2}} == {{2,1},{5,2},{6,4}}
]

(* Test that the cumulative subtraction of the points in the path from 
the final (sum) vector yield the vectors in the path, in reverse order

    Expected: (1, 2), (3, 1) and (2, 1)
*)

Assert[
    #[[2]] & /@ MovingMap[(#Values[[1]] - #Values[[2]]) &, 
    MapIndexed[{First@#2, #1} &, (Reverse@{{0,0},{2,1},{5,2},{6,4}})], 1] == {{1,2},{3,1},{2,1}}
]