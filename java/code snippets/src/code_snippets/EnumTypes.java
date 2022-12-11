package code_snippets;

//-just like cpp, except the syntax is a lil bit different

// -enums are used to enable a variable value to be an element from  
//   a set of predefined constants that are known at compile time

//example: days of the week, compass directions
//because they are constants the names of enum type's fields are conventionally in uppercase

enum Day {
SUNDAY	, MONDAY, TUESDAY,WEDNESDAY,
THURSDAY, FRIDAY, SATURDAY
}
public class EnumTypes {
	public static void main(String args[])
	{
	
		System.out.println(Day.MONDAY);
		
 /*
  * the compiler adds some special methods to enums body when created
  * for exmpl the static values() method returns an array containing all the values of the enum
  * in the order they're declared
  */
		for(Day d: Day.values())
			System.out.print(d + " ");
		System.out.println();
		
/*
 * enums automatically extend the java.lang.Enum class , therefore enums can't extend anything else
 * 
 * enums can have constructors, but in java, the constants must be defined first, prior to any method or field.
 * 		when there are fields and methods, the list of enum constants must end with a semicolon.
 * 
 * The constructor for an enum type must be package-private or private access.
 *   	It automatically creates the constants that are defined at the beginning of the enum body.
 *   	You cannot invoke an enum constructor yourself.
 * 
 * enum Day {
		SUNDAY, MONDAY, TUESDAY,WEDNESDAY,THURSDAY, FRIDAY, SATURDAY;
		
		//methods and other bs here
}
 */
	
		people omar = people.OMAR;
		System.out.println(omar.getAge());
		System.out.println(people.OMAR.getLastname());
	}
	
}

//enum with a constructor and methods example
enum people{
	OMAR("gritch",20), CRISTIANO("ronaldo",36), JOHN("doe",17);
	//vlaues between () are passed to the constructor 
	
	private String lastname;	
	private int age;
	private people(String n,int age) //should be either private or package private
	{
		lastname = n;
		this.age = age;
	}
	public String getLastname() {
		return lastname;
	}
	public int getAge() {
		return age;
	}	
	
	
		
	
}
