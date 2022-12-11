// part 1: getters and setters confusion                //the similar code in java would be like this
class human {                                           //  class human {
                                                        //      private String foo; private int age;
    constructor(name,age) {                             //      human(int age, String name) {
    this.name = name; //il y'a appel du setter ici!!    //          this.age = age; this.foo = name;  
    this.age = age;                                     //      }                    
    }
    get name() {                                        // String getName(){   
        console.log("getter is called")                 // return foo; }
        return  this.foo;       
    }                                                   //void setName(String name) {this.foo = name; }

    set name(name) {
      this.foo = name;
      console.log("setter is called")
    }
//can't have a getter without a setter function,
//in this class, the actual property name is called foo, but in the provided api 
// it's reffered to with "name"     
} 

//class declaration: like previous xmpl ( class classname { ... })

//class expressions
    //named
let dawg = class {
    constructor(name,age)
    {
        this.name = name; this.age = age;
    }
}
    //unnamed
let dawg2 = class dawg3 {
    constructor(name,age)   
    {
        this.name = name; this.age = age;
    }
} //let a = new dawg2(foo,bar) not dawg3 !!!

//methods and attribute

class classname{
    attribute; //public attribute
    #arr; //private attribute
    //private fields are preceded w the #
    constructor( attribute ){
        this.attribute = attribute //instance prop xmpl
        this.#arr = [1,2,3,4,5,6,7] //used for generator xmpl 
    }
    //methods
    method(param) {  
        //body of the method
        return param + this.attribute;
    }

    #privateMethod() {/*...*/ }

    //*generator methods
    *arrElems() {
        for(let e of this.arr)
            yield e; 

    //let a = classname.arrElemes();
    // each call to a.next() returns a generator object which is like { value: foo, done: boolean }        
    }
    
    //static methods and props: can't be called on instances wow
    static staticProp = "im shared between instances "
    static staticMethod() {
        console.log("crap")
    }

}

//we can also extend classes that are defined with the constructor function

//can't extend from objects (can't extend objects from factories and modules), if we want to extend an obj
// use Object.setPrototypeOf(myObj, prototype)
class derived extends classname{
    secondAtt;
    constructor(attribute,scnd)
    {
        super(attribute)
        this.secondAtt = scnd;
        // just like in java, call the super constructor before doing anything else
        // can't inherit private stuff, can't use the super keyword outsde of the class body
        // that's it i guess
    }
}

//a mixin is a class containing methods that can be used by other classes without 
//inheriting from it

//a mixin provides methods that implement a certain behavior,
//but we do not use it alone, we use it to add the behavior to other classes.

//mixins are used to achieve multiple inheritance   
//several ways to implement mixins, one of them is with function that take a base class as input,
// and return a subclass extending that superclass as output

//or copy methods into the prototype with Object.assign(myClass.prototype, ...methhods  )

let aMixin =  ( baseclass ) => {
    return class extends baseclass {
        amethod(){ };
    }
}

let secondMixin = ( baseclass ) => {
    return class extends baseclass {
        secondMethod(){ };
    }
}

class foo {
  third_method() {
  }
}

class bar extends secondMixin(aMixin(foo)) { }

//class bar extends fourthMixing(thirdMixing(secondMixin(aMixin(foo)))) { } and on and on