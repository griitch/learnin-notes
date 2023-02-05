/*
    the import statement can't be used in a unless we had the "type" ="module" attribute
    in package.json

    imported values are readonly
*/

import { g } from './lib.js'; // imports g
//console.log(g);
//g++; error: imported values are readonly
 
//import b from './lib.js'; // no { }, imports the default export
//console.log(b);
//import b, { namedExport1, namedExport 2 ...} from ... : imports the default + named exports
//import b, * as griitch from ... : imports the default + everythang else
//this last one is redundant because griitch.default === b 

import * as lename from './lib.js'; //import everythang from the module as an object 

//console.log(lename.default);
//console.log(lename.g);

import { foo as alias } from './lib.js'; //import with an alias
//alias();

import { age, name } from './lib.js'; //importing multiple exports
//import { age as alias2, name as fff} from './lib.js'; importing multiple exports with aliases

import './lib.js'
//runs the imported module's global code without importing anything
//runs the commented console log in lib.js

//dynamic import: see later on mdn page
//https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/import