 //rule: if you need one and only one of something, use a module, if you need multiple use factories instead
 
 
 //factory function example
  //replace the constructor which is, according to top, error prone

const humanFactory = function (name,age){

    let crap ="crap" //crap is a "private" attribute

    const  printcrap = function() {
        console.log("printing "+ crap )
    }
    return {name,age,printcrap};

}

//inheritance w factories
const studentFactory = function(name,age)
{
    const {printcrap} = humanFactory(name,age) //inherited stuff method
    const printshite = ()=> console.log("shite") //additional stuff

    return {name,age,printcrap,printshite};
}


//inherit all of an object methods with the assign method
//Object.assign(target, ...sources)
//copies all enumerable own properties from the sources objects into the target 
//properties in the target are overwritten if they have the same name
//enumerable prop: can  be accessed in a (for elem in object) loop
// interesting examples at https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/assign

const studentFactory2 = (name,age) => {
    const source = humanFactory(name,age)
    const printshite = () => console.log('printing shite');
    return Object.assign({}, {printshite}, source);
    //assign the 2 objects to an empty object
    //rappel: obj = {prop : value};  let {prop} = obj is equivalent to let prop = obj.prop (same name!!)


}

//the module pattern (not the module w export import thing, the module design pattern)
//modules are like factories but instead of having a factory that keeps creating objects, we create only one with a IIFE
//Modules are commonly used as singleton style objects where only one instance exists

const myModule = ( function(){
    var privatePropety = "hi i'm Private";
    var privateMethod = () => console.log("hi i can't be called directly from outside");

    return {
        publicAttribute: "hi i'm public",
        publicMethod( ) { console.log("hi im a public method "); },
        iranoutofnames() { console.log(privatePropety); privateMethod(); }
    }
})();


//calculator example
const calculator = ( 
function(){
    function add(a,b) {return a + b};
    const substratct = (a,b) => a-b;
    const divide = (a,b) => a/b;
    const multi = (a,b) => a*b;

    return {add, substratct, divide, multi};
})();

//calculator.add(7,3);

//advantages: organization, namespacing, encapsulation 