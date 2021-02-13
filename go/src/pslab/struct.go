package pslab

import (
	_ "fmt"
)

type A struct {
	bool
	A2 struct {
		bool
		int
		a2i []int
	}
	ai []struct {
		string
		aib []bool
	}
	ai2 []int
}

//func construct() {
//	a := A{
//		true,
//		struct {
//			bool
//			int
//			a2i []int
//		}{
//			false,
//			2,
//			[]int{1, 3},
//		},
//		[]struct {
//			string
//			aib []bool
//		}{
//			{
//				"asd",
//				[]bool{true, false},
//			},
//		},
//		[]int{2, 3},
//	}
//	_ = a
//}
