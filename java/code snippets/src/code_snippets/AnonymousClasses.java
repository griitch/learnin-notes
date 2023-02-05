package code_snippets;

public class AnonymousClasses {
	int EnclosingClassMember;
	static int staticEnclosingCLassMember;
	/*
	 * they are like local classes but have no name and are only used once
	 * anon class are "expressions", local classes are declaration
	 * they enable us to declare and to instantiate a class @ the same time
	 */
	/* syntax: new + name of interface to implement or class to extend + parenthesises that
	 * contain arguments of the constructor + the body of the class inside curly braces {}
	 * and because anon classes are expressios add a semicolon; after the class body 
	 */
	
	//example, added the second method just so the class won't be a functional interface, as it's better to use lambda expression 
	//with functional interfaces
	interface helloworld{
		abstract public void greet();
		abstract public void curse();
	}
	
	class frenchGreeting implements helloworld {
		public void greet() {
			System.out.println("bonjour tout le monde");
		}
		public void curse()
		{
			System.out.println("mange tes morts" );
		}
		
	}
	
	//we can instead do this
	helloworld frencHello = new helloworld() {
		public void greet() {
			System.out.println("bonjour tout le monde");
		}
		public void curse()
		{
			System.out.println("mange tes morts");
		}
		
		@SuppressWarnings("unused")
		public void method() {
			System.out.println(EnclosingClassMember + staticEnclosingCLassMember);
		}	
	};
	
	//its way more flexible because we can now use an anon class for each <language> instead of creating a whole new class
	//in case we intended to use them once.
	
	/*
	 * like local classes, anon classes can capture local variables ( only access effectively final local vars if they were local)
	 * and they have access to enclosing class members.
	 * can't declare static init blocks or member interfces inside anon classes unless they are constant variables
	 * 
	 * anon class can have fields, methods, instance initalizers and local classes but cant have a constructor
	 * 
	 */

}
