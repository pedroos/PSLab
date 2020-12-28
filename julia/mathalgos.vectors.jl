using StaticArrays
using Test

@testset "VecTests" begin
    # Test that the sum vec of the vectors composing the path is 
    # the last point in the path
    
    # Expected: (6, 4)
    
    @test sum([
        SA[2, 1], 
        SA[3, 1], 
        SA[1, 2]
    ]) == SA[6, 4]

    # Test that the cumulative sum of the vectors in the path 
    # yield the points in the path
    
    # Expected: (2,1), (5, 2) and (6, 4)

    @test collect(Iterators.accumulate(+, [
        SA[2, 1], 
        SA[3, 1], 
        SA[1, 2], 
    ])) == [SA[2, 1], SA[5, 2], SA[6, 4]]

    # Test that the cumulative subtraction of the points in the path 
    # from the final sum vector yield the vectors in the path

    # Expected: (2, 1), (3, 1) and (1, 2)

    @test collect(Iterators.accumulate(-, Iterators.reverse([
        SA[2, 1], 
        SA[5, 2], 
        SA[6, 4]
    ]))) == [SA[1, 2], SA[3, 1], SA[2, 1]]
end