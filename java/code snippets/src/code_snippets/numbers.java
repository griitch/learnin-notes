package code_snippets;
import java.text.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*; //static, we don't have to write Math.method, direclty the method

@SuppressWarnings("unused")


//there is a Number class that all of the wrapper classes of primitive numbers inherit from
//we use wrapper classes instead of primitives for some use cases like:
//	-arguments of methods that expect an object
//	-use constants defined by the class such as MAX_VALUE	
//	-USE class methods for converting value from and to pimitive types, to and from strings, or between
//		number systems (decimal octal hexadecimal binary.....)

public class numbers {
	
	// {Primitive type}Value(), compareTo({correspoding type}), equals(Object )
	// are all instance methods implemented by all wrapper classes
	
	static Integer i = 7;
	static double b = i.doubleValue(); //floatValue(), byteValue()..... for all other types
	static int result = i.compareTo(6); // 1 if less , -1 if it's greater, 0 for equality
	
	public static void main(String args[]) {
		// methods for string conversion for integer class, other number class methods are similar
		// if they were instance methods i will be used, else they are static 
		
		System.out.println(Integer.decode("0xFFFF") + " " + Integer.decode("0121"));
		//decode = string to int, accepts hexa (0x...) and ocral (0...) and decimal values
		
		//Integer.parseInt(int ): same as decode but accepts decimal only
		
		//Integer.parseInt(int num, int base): accepts binary octal decimal hexa (specify base = {2,8,10,16})
		
		//toString() instance method to return a string of the given int
		//String.toString(int i) same but a string class method
		
		Integer seven = Integer.valueOf(7); //valueof returns a new integer object with the value 7
			//there is a overloaded valueOf(String num) that does the same
			// and a valueOf(String num, int base) that accepts other number systems
		
		//decimal format class is used to control the display of leading and trailing zeroes, pre and suffixes
		//grouping separators (for 1000's) 
		customFormat("###,###.###", 123456.789); // #represents a digit, the comma: separator, the dot: placeholder for the decimal separator
		customFormat("###.##", 123456.789); //value has 3 digits past the decimal point, the patternhas 2, so it rounds it up
 		customFormat("000000.000", 123.78); //leading and trailing 0's
		customFormat("$###,###.###", 12345.67);// the 1st char is a $
		
		//some math methods
		//numType abs(num),double ceil, double floor, long /int round, numsType min/max(num1, num2)
		//double exp(double d), log(double d) => natural logarithm !
		// double pow(double base, double exponent), double sqrt(double d)
		//trigonometric methods: sin, cos, tan, acos, atan, asin, toDegrees, toRadians al take and return double
		// random num: random(): returns a double between  and 1
		
		//autoboxing and unboxing is the automatic conversion that java does between primitives and wrapper classes
		//converting a primitive to a wrapper object is called autoboxing
		//its done when a primitive values is passed as a parameter to a method that expects an object of the wrapper class
		List<Integer> li = new ArrayList<>(); 
		li.add(i); ///even tho i is an INT not an INTEGER OBJECT 
		// or when when it's assigned to a variable of that class
		Integer i = 7;//
		
		//converting from an object to a primitive is called unboxing, it's done automatically when we assign an object to the 
		//corresponding primitive type, or when we pass it as an argument where the primitive type is expected
	}
	 static void customFormat(String pattern, double value ) {
	      DecimalFormat myFormatter = new DecimalFormat(pattern);
	      String output = myFormatter.format(value);
	      System.out.println(value + "  " + pattern + "  " + output);
	   };		
	
	
}
