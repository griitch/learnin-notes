package code_snippets;

public class LocalClasses {
	/*local classes are non static nested classes defined inna block ( { .......}, typically in the body of a method
		but can also be in a "for" loop or in a "if" clause
		
		in the example i'll use a local class in a method
	 */
	
	
	int nonStaticMember; 
	static int StaticMember;
	
	void method( int argument)
	{
		
		int nonFinalLocalVariable = 9;
		final int finalLocalVariable = 777;
		int g = 7; 
		// g++; //if we uncomment this we'd have an error at line 33, non effecitvely final local vars are inaccessible 
		//to local classes
		@SuppressWarnings("unused")
		class localClass {
			//static int b= 0; error, can't have static members 
			//local classes defined inside static methods can only direcly access STATIC members of the enclosing class
			// can't declare interfaces inside a block because interfaces are inhertly static
				
			void method()
			{
				//nonFinalLocalVariable++; error, if we incremented the variable will no longer be effectively final and we won't
				//have access to it
				
				System.out.println(nonFinalLocalVariable + argument + finalLocalVariable + nonStaticMember + StaticMember + g);
				//we have access to members of the outerclass, and to arguments and local variables 
				//of the enclosing block as long as they are effectively or explicitely final
				
				
			}
		}
	}
	
	
	static void staticmethod(int argument)
	{
		//we don't have acccess to nonStaticMember of the encolsing class
		//we have access to StaticMember
		//we have access to effectively final local variables of the method, and the argument
		int effectivelyfinal = 8;
		final int finalfinal = 7;
		System.out.println(argument + StaticMember + effectivelyfinal + finalfinal );
		//System.out.println(nonStaticMember); error
		
		
		
	}
	
	
}
