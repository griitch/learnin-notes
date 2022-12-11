package code_snippets;

//import java.util.ArrayList;
import java.util.List;

//the same syntax as templates in c++, but generics in java can only have non primitive type variables
// a generic type is a generic class or interface that is parameterized over types
/*Type params naming conventions :
 *  E: element, used extensively in collections
 *  K: key,   N: number, 	T: type, 	V: value
 */

public class Generiks<T> { // can have multiple types params xmpl: Generiks<T,V> { ... }
	
	@SuppressWarnings("unused")
	private T param;
	
	public Generiks(T t) { this.param = t; }
	
	//generic methods: methods that introduce their own type params (scoped to that method)
	//very counter-intuitive syntax, put the params list before the return type
	public <V,N> void printcrap(N n, V v) {
		System.out.println(n + " " + v);
	}
	
	//restricting type params (setting an upper bound ):
	// list the param time followed by extends + upper bound
	// this extends can mean either extends for classes or implements for interfaces
	// xmpl a generic method that take only numbers
	
	public static <N extends Number> void dosomething(N n)
	{
		System.out.println(n.getClass().getName());
	} //dosomething(7) works, dosomething("alal") compilation error
	
	//multiple bounds with the & operator <T extends A & B & C > (multiple interfaces and at most one class) 
	// if one of the bounds is a class it must be specified first
	
	//heritage :
	// myClass<superType> 
	// -myClass<A> and myClass<B> are not subObjects of myClass<Object> or whatever supertype they share
	//			they are descendants of myClass<?> !!!! 
	// there is no relationship between myclass<a> and myclass<b> regardless of the relationship btween a & b
	/* 
	 * to create generic classes subtypes, implement or extend one, for example list<string> extends collection<string>
	 * and arraylist<string> implements list<string>
	 *  
	 */
	class j extends Generiks<String> {	public j(String t) { super(t);} }
	
	//wild cards, the ? operator represent an unkonwn type
	public static void process(List< ? extends Number > list) { /* ... */ } //prcess a list of either float, integer..etc
	
	public static void processAnyList(List<?> list) { /* ... */ } //process a list of any type, used when the we're using methods 
															//that don't depend on the type param such as list.clear and list.size
	
	public static void lowerbounded(List<? super Integer> li) {/*...*/} //process lists of integers, numbers and objects
	
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public static void main(String[] args) {
		//invoking and instantiating a genric type
		Generiks<Integer> k = new Generiks<>(45); //before java 7 it was like new Generiks<Integers>(45)
	
		//raw types : generic classes & intercaces without type arguments
		Generiks raw = new Generiks(null);
		//raw types show up in legacy code bc alot of classes were not generic prior to jdk5
		raw = k; //assigning a parameterized type to a raw type is allowed for backward compatibilty
		// 		  but the opposite is not true can't assign a raw to a parameterized type	 
		
		//raw types are to be avoided because they	 bypass generic type checks
		
		//List<Integer> l = new ArrayList<>();
		//System.out.println(l instanceof List<?>); trueeee
		
		
		
		
	}
	
}
