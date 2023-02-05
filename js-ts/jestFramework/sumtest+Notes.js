// add this script to package.json
// {
//     "scripts": {
//       "test": "jest",
//        "watch": "jest watch *.js "
//     }
// }
/*
   - test script: run all the tests in files that end with test.js in the current dir and 
    its descendants
    
    - the way i understand the tdd cycle, start by writing test cases for the most basic
      use case, implement enough code to make it pass, and then refactor it, then add corner
      cases and implement code for them if they don't pass, and so on, when adding a new feature
      write the test for it first then code to make it pass ( alongside the old tests )
    - don't write a single line of code unless there was a failing test that demands it

    - what to test:  
      -> prioritze pure functions as they are easily testable (pure = no side effects and for the same x, f(x) is always the same)
        side effects: interaction w the outside world from withing the function 
        https://medium.com/@jamesjefferyuk/javascript-what-are-pure-functions-4d4d5392d49c 

      -> for "query" funcs (funcs that return something and do not modify the state of the obj
        and that don't have side effects
        xmpl get somethang ):
        test the what they return, and test the inteface not the implementation so that when u 
        change the implementation, the tests don't break

      -> for "command" funcs that have side effects and return nothing ( xmpl set something ) :
       make assertions about direct public side effects
      xmpl setAge(78); assert(age == 78)
      
*/

// read testing async code later

// repeating setup for all tests
// more on setup : https://jestjs.io/docs/setup-teardown
beforeEach(() => {
  //afterEach() too
  //foo()
});

//1 time setup : beforeAll() and afterAll();

// before and after are scoped with describe blocks:

// matchers : https://jestjs.io/docs/expect for complete reference

const sum = require("./sum");

describe("testing describe blocks", () => {
  test("toBe", () => {
    let a = { a: 5 };
    let b = a;
    expect(a).toBe(b); //passes
    // exact (referential) equality, uses Object.is(a,b) which returns true if a and b are identical
    // https://developer.mozilla.org/fr/docs/Web/JavaScript/Reference/Global_Objects/Object/is
  });

  test("toEqual", () => {
    let a = { a: 5 };
    let b = { a: 5 };
    expect(a).toEqual(b); // passes
    // toEqual checks every value of arrays/objects for equality
  });
});

test("adds 1 + 2 to not equal 4", () => {
  expect(sum(1, 2)).not.toBe(4);
});
// negation is done with not before the matcher

// precise matchers (btw can have multiple excepts in a test callback)
test("null", () => {
  const n = null;
  expect(n).toBeNull();
  expect(n).toBeDefined();
  expect(n).not.toBeUndefined();
  expect(n).not.toBeTruthy();
  expect(n).toBeFalsy();
});

// numbers :

test("two plus two", () => {
  const value = 2 + 2.1;
  expect(value).toBeGreaterThan(3);
  expect(value).toBeGreaterThanOrEqual(3.5);
  expect(value).toBeLessThan(5);
  expect(value).toBeLessThanOrEqual(4.5);
  expect(value).toBeCloseTo(4.1); //use to be close to for floats to avoid precision errors
  // toBe and toEqual are equivalent for numbers
  expect(value).toBe(4.1); //in this case its not recommended because im adding floats
  expect(value).toEqual(4.1); // use tobecloseto for floats !!!!!!!!
});

//Strings: test for matchin regex

test("string regex matching", () => {
  expect("omar").toMatch(/^o.*/);
});

//arrays and iterables: expect(iterable).toContain(value)

// excepctions: expect(function).toThrow()
