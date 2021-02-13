const Jscpp2 = require("../lib/binding.js");
const assert = require("assert");

assert(Jscpp2, "The expected function is undefined");

function testBasic()
{
    const result =  Jscpp2("hello");
    console.log(result)
    assert.strictEqual(result, "world", "Unexpected value returned");
}

assert.doesNotThrow(testBasic, undefined, "testBasic threw an expection");

console.log("Tests passed- everything looks OK!");