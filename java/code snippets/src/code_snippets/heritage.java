package code_snippets;

//u already know what interfaces and abstract classes and how inheritance works, this file is for little details

//static methods in interfaces are not inherited

//can't lower the visibilty of an overriden method, only keep it as it is or increase it	

//rules of name conflicts between superclasses (we can implement multiple interfaces and extend only one class)


// a class can not implement all of an interface methods as long as it's marked abstract


//rule 1: 
//	instance methods are preferred over default methods:
interface foo {
	 default public void method()
	 {
		 System.out.println("im called from the interface");
	 }
}


class goo {
	public void method() //this will be called in an instance of a class that implement foo and extends goo
	{
		System.out.println("im called from the superclass");
	}
}

//rule 2
//methods that are already overriden by other candidates are ignored. this can happen when supertypes share a common ancestor

interface too extends foo{
	@Override
	default public void method() // a class that implements too and foo will use this !!!
	{
		System.out.println("i have overwritten the method in foo");
	}
}

//rule 3
// if 2 or more independant default methods conflict, or a default method conflicts with an abstract method 
// we'll get a compilation error, we must explicitly override the supertype methods

interface foo2 {  default public void method() { System.out.println("crap"); }}
interface goo2 {  default public void method() { System.out.println("shite"); }}

//a class that implements foo2 and goo2 MUST override method()
//we can still call the original methods like this: foo2.super.method() - goo2.super.method()

public class heritage extends goo implements too,foo  {

	public static void main(String[] args)
	{
		Object obj = new heritage();
		((heritage) obj).method();
		//obj.method(); this generates an error
		}





}
