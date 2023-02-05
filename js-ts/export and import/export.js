/*
    live binding: when a value is modified in the module that exports it, the update will be visible
                in the modules that import it

    there are two types of exports: named and default
    can only have one default export per module and as many named as we want
*/

// exporting individual features
export let var1; //importing a declaration
export let var2 = 8; //importing a declaration + init
export const var3 = "var3"; 
export var var4 = "foo";
export function foo(){ /* ... */ }
export class className { /* ... */ }    

//exporting multiple elems ( export list )
let a; var b; const c = 99;
export { a, b, c} // ONLY WORKS WITH IDENTIFIERS (a b c must be defined beforehand)
//                    cant have something like export { let a = 7,  const vf }

//exporting with name
export {a as variableA, b as variableB };

//exporting obj with destructructing assignment with renaming
let obj = { foo: bar, doo: tar }
export const { foo: foo2 , tar: tar2 } = obj;

//exporting obj with destructructing assignment without renaming
export const { foo, tar} = obj;
// DO NOT FORGET A CONST OR VAR OR LET AFTER THE EXPORT

//its mandatory to use the same as the export name during the import for named exports
// not for default exports, the importer can use whatever name he wanted

//default export : used when we wanna export ONE value or have a fallback value
export default something //NO SEMICOLON, not even sure about this semicolon thang
export { name as default};
//something could be anything: a variable, a class, an obj, a function etc

//aggregating exports is some bs i wont even bother learning unless i needed it

