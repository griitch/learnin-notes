package code_snippets;

import java.util.function.*;


public class FunctionalInterfacesClass {
	
	//ok so now we have this array, we wanna print the even numbers with the printeven method with 3 different methods,
	// the only difference between the three is the way we invoke the method 
	
	static int[] arr = {0,1,2,3,4,5,6,7,8,9,10};
	static int b = 8;
	static void printeven(Functional_Interface f) {
		for(int a : arr)
		{
			if(f.verify(a))
				System.out.print(a + " ");
		}
		System.out.println();
		f.printcrap();
	}
	//Predicate<int> gives this error: insert "Dimensions" to complete reference type, it means that
	//we tried to pass in a primitive object into a generic type declaration, generic types alwyas 
	//expect a wrapper class object.
	static void printeven2(Predicate<Integer> f) {
		for(int a : arr)
		{
			if(f.test(a))
				System.out.print(a + " ");
		}
		System.out.println();
	}
	//-----------------------------------------------------------------------------------------------------
	//other useful std functional interfaces
	//1- Consumer<T>, the abstract method is accept(T), takes a T as an argument and returns void, could be used to process some data
	static void dosomething(Predicate<Integer> p, Consumer<Integer> c) {
		for(int a: arr)
		{
			if(p.test(a))
				c.accept(a);
		}	
		System.out.println();
	}
	
	//2-  Function<T,R> , the method is  " R apply(T t) " Function<Integer,String> dosomething( a -> a.toString() );
	static void dosomething(Function<String,String> f, String s) {
		String ss = f.apply(s);
		System.out.println(ss);
	}
	
	
	//another way: aggregate operations that accept lambda expressions as parameters
	//List.stream().filter( p -> p%2 == 0); return to it when u study collections	
	
	
		
	public static void main(String[] args) {
		
//method 1: full implementation of class. the evencomparator class extends the functional interface and defines the verify method
		printeven(new evencomparator());		
//-------------------------------------------------------------------------------------------------------------------
		
		//method 2: anon class, same body as printeven1, but we do not define a whole separate class, we'll call it using anon classes
		 printeven( new Functional_Interface() {public Boolean verify(int a) { return a%2 == 0;} });
		 
//---------------------------------------------------------------------------------------------------------------------
		//method 3: best one i guess, lambda functions, 
		 //lambda function defines that one abstract method in the functional interface
		//printeven( (int a) -> { return a%2==0;} ); overkill
		//the following expression shows the power of lambda expresssions, we ommited return type, and the "return" and the result is
		 //a very short piece of code, and we can just use a lambda for each criteria of search in the array, without defining 
		 //separate methods or classes.
		printeven( a -> a%2==0); 
		 
//-----------------------------------------------------------------------------------------------------------------------
		//method 4 : use standard functional interfaces from java.util.functions, predicate in this case	
		printeven2( a -> a%2==0);
//-----------------------------------------------------------------------------------------------------------------------
		//using consumer and predicate func interfaces to print the even numbers * 2;
		dosomething( a -> a%2 == 0 , a-> System.out.print(a*2 + " "));
//-----------------------------------------------------------------------------------------------------------------------	
		//using function<String,String>
		dosomething( a -> a.toUpperCase() , "omaaaaaaaaaat");
		
		
	}

}
