//difference btwn var and let

let a = "let and const are block scoped like most langs";
var b = "var is function scoped"
const c = "vars with var are attached to the window object if they are defined in global scope "

let [a,b,,,c] = [1,2,3,4,5,6];
//a = 1, b = 2, c = 5

[a,b, ...c] = [4,5,6,7,8,9,10]
//a = 4, b = 5, c = [6,7,8,9,10]


let obj = { prop1 : "value1", prop2 : "value2", prop3 : "value3" }
// let value1 = obj.prop1; 
// let value2 = obj.prop2; 
// let randomname = obj.prop3; 
//is equivalent to : 
let { prop1: value1, prop2: value2, prop3: randomname } = obj;
//let { prop1, prop2, prop3 } = obj; is correct but the variables will have the same name 
//as the property

function func(...args) //rest parameter allows us to create funcs that take a variabel number of args 
{
 //we can use any array method on args
 console.log(args[0]);
 console.log(args.length);
 return args.filter( a => a > 0);
}
//console.log(func(-3,-2,-1,0,1,2,3,4,6));

//spread operator: ...arr, expand arrays and other expressions in places where multiple comma separated
//parameteres are needed (like functions arguments)

let array1 = [1,2,3,4,5];
let array2 = [...array1]; //equivalent to array2 = [1,2,3,4,5]
// or call func(array1);
//console.log(array2)


//getters and setters
class fruit{
    constructor(name,price)
    {
        this._name = name;
        this._price = price;
    }

    set price(p)
    {
        this._price = p/2;
    }

    get price()
    {
        return this._price;
    }
}

let banana = new fruit("banana", "60 riyal");
banana.price = 150  //it's counterituitive, because at first glance it seems like it should be
                    //banana.price(150) 
console.log(banana.price)

/*
    <script type="module" src="./jstest.js">
    makes the script able to use export and import
*/

//every module script that imports this function will be able to use it
export function printcrap()
{
    console.log("currently printing crap to the console");
    //to use it: import {printcrap} from "./ec6crap.js" or import * from "./ec6crap.js"
}

export default function()
{
    console.log("default anonymous func");
    //import randomname form "./ec6crap.js"
    // randomname() will call this default func

}


//promises, and other bullshit to be covered later


//closures: not really ec6 but i didn't want to make a whole new file jsut for them
function addto(number)
{
    let localvariable = 7
    function add(value)
        {
            return number + value + localvariable;
        }
    
    return add
}

let addto1 = addto(1);  
//addto1 is a closure (fermeture), elle enferme la fonction add et son environnement
// environnement = variables locales accessibles 

// function values : contain both the code in their body and the environment in which they are
// created. When called, the function body sees the environment in which it was created, not the
// environment in which it is called.

(function () { console.log("printing bs"); })()
//we just called an anon func right after decalring it, this is called an iife
// immediately invoked function expression



