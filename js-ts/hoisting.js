// variable and functiondeclarations are hoisted
//conceptually, hoisted elements are moved to the top of the code,but what actually happens
// is that they are put in memory during the compile phase, (i guess it's a bad thing to have
// many hoisted elems for better memory usage)
// classes exrpessions and declarations are not hoisted
//examples:

 
//------------------------------------------------------------------------------------------------

// function and class expressions are not hoisted 

a(); //Uncaught ReferenceError: Cannot access 'a' before initialization

let a = function(){
  console.log("hi")
}

//---------------------------------------------------------------------------------------------------

//function declarations are hoisted

printsutff(); // prints "hi" to the cosole

function printsutff(){
    console.log("hi");
}

//--------------------------------------------------------------------------------------------------

    //variables declarations are hoisted, but not initialization
    //declarations using let and const are not hoisted   

console.log(a); // Uncaught ReferenceError: Cannot access 'a' before initialization
let a ; //declaration <==> a = undefined
a = 7; // initialization

c = 9; // initialization
const c; //Throws SyntaxError: Missing initializer in const declaration


//---------------------------------------------------------------------------------------------------

    //variable declarations with var are hoisted

console.log(b); //logs undefined
var b;
b = 9;


//------------------------------------------------------------------------------------------------------
    //important point:
    //even if we had declaration and initialization at the same expression, only the declaration is hoisted

console.log(y); //undefined
var y = 99;


//---------------------------------------------------
//classes are not hoisted, we need to declare and init them first before instanciating thems

let a = new rectangle(); //reference error

class rectangle {
    constructor() { /* .. */ }
};


let b = new kk(); //reference error

let kk = class {
    constructor() { /* .. */ }
}