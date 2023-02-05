## YDKJSY part 2 : scopes and closures

Scopes are defined at compile time, more precisely, at the lexing phase, hence, the name `lexical scope`

Scopes are not created (memory allocation) until runtime

The scope can be modified at runtime through the `eval` func and the `with` keyword which i did not know exists, both are disallowed in strict mode

```js
function foo() {
  eval("var x = 9;");
  console.log(x); // prints 9
}
```

the `with` keyword turns an object into a local scope, its props are treated as identifiers

```js
let foo = { a: 9, b: 3 };

with (foo) {
  console.log(a + b);
}
```

---

Scope lookup ( searching outer scope for ids if they were not found in the current scope ) does not happen at runtime, the scope in which an id lives is known at parsing/compilation

Corner case : working with multiple files, multiple imported js scripts all share the global scope, some scoping info might be deferred to runtime.

---

There is a distinction between global variables and global properties ( properties of the global object, `window` in browsers, `global` in node )

```js
let foo = 9;

window.foo = "bar";

window.foo === foo; // false
```

but, using var in global scope makes the value a global property

```js
var foo = 9;
window.foo === foo; // true
```

---

Web workers are a web platform extension which allows other js files to run on a separate thread, to avoid race conditions, web workers have no access to the DOM and certain apis.

Web workers do not share the global scope with the main js program, and the `window` object does not exist, there is a `self` object instead as an alias for the global scope

---

as of es2020 there is a standardized global scope alias object which is `globalThis`, which will point to either `global` or `self` or `window`

---

Unlike most js developers, the author is not against the use of `var`, he says that it's ok to use both `let/const` and `var, and i agree with him

---

FIB : function declarations in block
this code behaves differently based on the environment it runs in

```js
if (false) {
  function ask() {
    console.log("Does this run?");
  }
}
ask();
```

will it be a type or reference error or will it run just fine

**type error** : the id exists but is undefined  
**Reference error** : the id does not exist

according to the spec, function declarations are block scoped so it should be a reference error, but when i run it in node (or any browser that uses v8) i get a type error (ask is not a function )

fibs are function scoped, like variables declared with `var`

according to the author, browser engines such as v8 are allowed to go against the spec sometimes, in this case, they decided to keep function declared function scoped to keep backwards compatibility, (fibs being block scoped is an es2020 addition, engines had their own way of dealing with them prior to that)
