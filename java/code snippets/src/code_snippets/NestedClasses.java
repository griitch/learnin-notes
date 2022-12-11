package code_snippets;

public class NestedClasses {
/*
 * 2 main types of nested classes: static and non static
 * static nested classes are called static nested classes (wow what a genius)
 * non-static nested classes are called inner classes. there are two special types of inner classes : local and anonymous classes
 * 
 * a nested class is a member of its enclosing class. inner classes have access to members of the enclosing class even if 
 * they are private. static classes have only access to static members of the enclosing class
 * nested classes can have access modifiers (private, public, protected, package private (default) )
 * 
 */
	
		int a ; 
		static int b;
		
		static {			
		 b = 0;
		 //a = 8; error on this line cause a is no static
		}
		
		class InnerClass {
			
			int memberA;
			void methodddd() { ; } 
			//static int staticmember; // error
			{ //this is an initialization block, init blocks are executed before the class constructor and in the order
				//they appear in the source code, static init blocks begin with the keyword static, they could be used to 
				// initialize static members like b in the outer class
				b++;
				a++;
			}
			/*
			 * an inner class is associated with an instance of its enclosing class and has direct access to its members, and it cannot
			 * define any static members itself.
			*/		
		}
		
		//to instanciate an inner class, instanciate an object of the outer class first
		NestedClasses outerclass = new NestedClasses();
		NestedClasses.InnerClass innerclass =  outerclass.new InnerClass();
			//counter-intuitive syntax but okay
		
		
		static class StaticInnerClass {
			/* static nested classes are associated with the outer class and not With a particular instance of the class an like static methods
			 * static nested classes cannot refer to instance variables unless thru an object reference, see accessMembers method
			 * 
			 * 
			 */
			void accessMembers(NestedClasses nestedClassInstance)
			{
				System.out.println(nestedClassInstance.a);
			}
			
			
			{
				//a++; this lines generates an error, static nested classes are like nested methods, they can only access 
				//		non-static members of their enclosing class
				b++; 
			}	
		}
		//to instantiate a nested inner class, do so just like any normal top level class
		
		StaticInnerClass staticInnerClassInstance = new StaticInnerClass();
		//StaticInnerClass staticInnerClassInstance = new NestedClasses.StaticInnerClass(); works for some reason too.
	
	
	
}
