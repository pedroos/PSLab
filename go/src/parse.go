package main

import (
		//"fmt"
		"go/ast"
		"go/parser"
		//"go/printer"
		"go/token"
		"log"
		//"os"
)

func main() {
		fset := token.NewFileSet()
		node, err := parser.ParseFile(fset, "pslab/struct.go", nil, parser.ParseComments)
		if err != nil {
			log.Fatal(err)
		}

		ast.Print(fset, node)

		//ast.Inspect(node, func(n ast.Node) bool {
		//		sc, ok := n.(*ast.Scope)
		//		if ok {
		//			println(sc)
		//		}
		//		//st, ok := n.(*ast.StructType)
		//		//if ok {
		//		//	println(st.Name)
		//		//}
		//		return true
		//})
}
