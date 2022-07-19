## You don't know js - part 1 : get started

Many web / node apis such as `alert` and `fs.readSync` are not specified in ecmascript

TC39's mantra is "don't break the web"

js is backwards but not forwards compatible and that's why transpilers exist

forward compatibility means : code written in 2020 can run in a 2002 environment

js is compiled to bytecode before being executed by a vm, wow i did not know that one

ASM.js is the predecessor of WASM, but it's only a subset of js which is written in a certain way that the engine can run efficiently ( engineers at mozilla could run games at the browser w 60 fps )

WASM is a binary-packed program ready to be executed by js engines (skipping the parsing and making an AST phase )

WASM offers many possibilities, such as writing multithreaded code to run on the browser if the source was in golang

WASM is evolving to be a cross platform vm where programs can run in all sorts of environments and not just browsers

Ironically, js is not the type of langs that is suitable to source WASM even tho it runs on js engines, WASM relies heavily on static typing info

a quote from the book about strit mode

```
strict mode is like a linter reminding you how JS should be written to have the highest quality and best chance at performance
```

Each .js file has is its own program, they seem to be one big program running in the browser because they share the global scope which one file might act on

ECMA modules are singletons, if many scripts use `import` they will all get the same reference to the same module, if multiple instantiation was needed, export a module factory func

Only iterable objects can be spread with `...object`
