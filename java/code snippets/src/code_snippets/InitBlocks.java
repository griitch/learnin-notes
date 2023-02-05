package code_snippets;

public class InitBlocks {
	
//2 types of init blocks, static and instance init blocks
//a static init block code is executed when the class is initialized ( loaded in the jvm) (executed only once!!)
	
//an instance init block is executed every time an object is created before the constructor, actually according to
//stackoverflow, the init block is "copied" into every constructor of the class

/*
 *	-static init block can be used to init static attributes of a class
 *  -the only use case i can think of for instance init block is sharing code between many constructors
 *   but what if that piece of shared code needed parameters ? idk
 *	
 *  -if the class inherited from another class, the order will be like :
 *    static init block of the super class -> static init of the class -> instance init of the super class
 *    -> superclass constructor ->  instance init of the class -> constructor of the class
 *
 */

	@SuppressWarnings("unused")
	private int nonStaticMember;
	private static int staticMember;
	
	static {
		System.out.println("static initialization");
		staticMember = 10;
		//nonStaticMember = 7; // genereate an error
	}
	
	{
		System.out.println("instance initialization");
		nonStaticMember = staticMember;
		staticMember--;
	}
	
	public InitBlocks()
	{
		System.out.println("constructor");
	}
	
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		InitBlocks a = new InitBlocks();
		a = new InitBlocks();
		
	}

}

