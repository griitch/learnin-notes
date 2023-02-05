package code_snippets;

public class ChainesDeChars {
	
	@SuppressWarnings("unused")
	public static void main(String args[])
	{
		//there are 13 string constructors but the most direct way is to use a string literal
		//String str = "string literal";
		
		//char array constructor
		char[] arr = {'o','m','a','r'};
		String str = new String(arr); //new String(char[] arr, int offset, int count) to take only a portion of the array
		
		str.length();// returns int (a method not a property like in arrays)
		str.getChars(0, str.length(), arr, 0); // getChars(srcBeginIndex, srcEndIndex, array to put results in, DestBeginIndex)
		str.charAt(2); 
		String n = "0123456789";
		n.getChars(4, 7, arr, 1); // ==> arr[0] stays the same, arr[1] = n[4], arr[2] = n[5], arr[3] = n[6]
		//  						 ==> arr = {o,4,5,6}
		//throws an out of bounds expection if destArray.length - destBegin < src.(endINDex - beginIndex)
		
		//concatenation with + or the concat(String str) instance method
		String multiLineString = "this string spans multiple" +
								 "lines using the + operator" +
								 "at the end of each line";
		
		//create formatted strings with the static method format
		String formatted = String.format("idk what to put in here so i'm %d years old ", str.length());
		
		//converting between numbers and strings
		// -the Number classes have all a static method valueOf(String) that returns the value inside that a string as a number object
		//  then, to access the actual primitive value use the {type}value instance method
		// xmpl:	
		String ff = "78.5";
		float a = (Float.valueOf(ff)).floatValue();
		
		// each one has a parseType(String ) that converts string DIRECTLY to primitive values, more direct than the previous method
		 a = Float.parseFloat(ff);
		 
		 //numbers to string
		 //easy way: concatenate with an empty string
		 String s1 = "" + a;
		 //or String.valueOf( primitive type)
		 s1 = String.valueOf(a);
		 //or use one the number subclasses static toString(i) methods
		 s1  = Float.toString(a);
		 
		 //methods for manipulating substrings
		 String s2 = "omar yahya grinat";
		 s1 = s2.substring(0,6); //substring(begin, end) or substring(begin) 
		 s2.split(" "); //returns a string array, the delimiter is a regex
		 //s2.split(regex, int num max) 
		 
		 //s2.subSequence(int begin, int end) returns a *charSequence* that can be cast to a string
		 //charsequence is an interface that string implements
		 //i don't see myself ever using this, if substring exists
		 
		 s2.trim(); //returns the string but without the leading and trailing whitespace		
		 s2.toUpperCase(); //dito s2.toLowerCase();
		 
		 //searching inna string
		 //int indexOf([String char]), lastIndexOf([string char]); or [index LastIndex]Of([String char], int fromIndex)
		 //boolean contains(CharSequence s) 
		 
		 //replacing inna string
		 /*
		  * 	String replace( [charSequence char] target, [charSequence char] replacement] 
		  * 		=> replaces EACH substring/char that match target w the replacmenet
		  * 
		  * 	String replaceAll(String Regex, String replacement) same as replace but can take regex
		  * 	String replaceFirst(String REgex, String replacement)  
		  */
		 //comparing strings with portions of strings
		 /*
		  * 	boolean ends/startsWith(String )	
		  * 	boolean startsWith(String s, int offset) //consider offset as the beginning of the string
		  * 	int compareTo(String s) (strcmp in c) 
		  * 	int compareToIgnoreCase(String s)
		  * 	Bool equals() & equalsIgnoreCase();
		  * 	boolean regionMatches(boolean ignorecase is optional, int srcBegin, String other, int otherOffset, int length)
		  		boolean matches(string regex);
		  */
		 //StringBuilder class (for better performance, because string concatenation requires rebuilding a new string and
		 //						is O(n), StringBuffer is exactly like StringBuilder but i's thread safe)
		 
		 StringBuilder sb = new StringBuilder(10); //int capacity
		 //sb = new StringBuilder(charsequence);
		 sb = new StringBuilder(); //default capacity 16
		 sb = new StringBuilder("omar"); //value is initialized w omar + 16 empty elemnts trailing the string
		 //Stringbuilder length methods
		 sb.setLength(20); //if the new length is less than current length chars ae truncated, else null chars are added at the end
		 sb.ensureCapacity(25); //sets the minimum capacity 
		 //content methods
		 sb.append("this can accept booleans, chars, char arrays, numeric types, objects and strings");
		 sb.delete(5, 8);  sb.deleteCharAt(6); //delete(begin,end) => from begin to end-1 inclusive
		 // Stringbuilder sb.insert(int offset, arg), inserts arg in the index before offset, the args could be all types that could be appended using append
		 // StringBuilder replace (int start, int end, string s) 
		 // StringBuilder setCharAt(int index, char c) 
		 // StringBuilder sb.reversr()
		 String strr = sb.toString();
		 //note: we can use any of String methods by using toString on the sb, then create a new sb with the StrinBuilder(String) constructor	
		 
		
	}
}

