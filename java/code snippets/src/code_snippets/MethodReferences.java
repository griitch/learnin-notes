package code_snippets;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class MethodReferences {

//method references are like function pointers in c++, with them you can pass functions as params
/* We use lambda expressions to create anonymous methods, but sometimes, those lmbda expressions
 * do nothing but call an existing named method, so it makes sense to pass in that method in the 
 * params
 * Method references enable this, they're compact easy-to-read lambda exps for methods dat already have a name
 * ------------------------------------------------------------------------
 * 4 types of reference methods: 
 * reference to static methods: ContainingClass::staticMethodName
 * Reference to an instance method of a particular object: containingObject::instanceMethodName
 * Reference to an instance method of an arbitrary object of a particular type : ContainingType::methodName 
 * Reference to a constructor : ClassName::new
 * 
 */
	 public static <T> T mergeThings(T a, T b, BiFunction<T, T, T> merger) {
	        return merger.apply(a, b);
	    }
	
	 public static String appendStrings(String a, String b) {
	        return a + b;
	    }
	    
    public String appendStrings2(String a, String b) {
	        return a + b;
	    }
	

	public static void main(String args[])
	{
		List<Integer> arr = Arrays.asList(0,1,2,3,4,5,6);
		
		arr.forEach(System.out::print);
		System.out.println();
		
		//calling merge things with a lambda
		System.out.println(mergeThings("hello", "world", (a,b) -> a + b ));
		
		//calling it with a static method reference
		System.out.println(mergeThings("hello", "world", MethodReferences::appendStrings ));
		
		//calling it with an instance method of a particular object
		MethodReferences obj = new MethodReferences();
		System.out.println(mergeThings("hello", "world",  obj::appendStrings2));
		
		//calling it with a reference to an instance method of an arbitrary object of a particular type
		System.out.println(mergeThings("hello", "world",  String::concat ));
		
		//re-assigning new references to each member of a list of objects with a reference to a constructor
		//couldn't find a well explained example
		
	}
	
	
}
