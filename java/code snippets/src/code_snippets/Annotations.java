package code_snippets;
import java.lang.annotation.*;


public class Annotations {
//anotation are a form of metadata, they have no effect whatsoever on the code they annotate
/*
 * they are used to:
 *- give info to tje compiler: to detect errors and supress warnings
 *- Compile-time and deployment-time processing — Software tools can process annotation 
 * 		information to generate code, XML files, and so forth.
 *- Runtime processing — Some annotations are available to be examined at runtime. 
 *
 * annotations can have elements which can be named or unnamed
 *  @annotationName( element1 = value, element2 = value	)
 * 
 *  if there was just one element named value , the name can be omitted
    @annotationName( foo ) equivalent to @annotationName(value = foo)
 */ 
	//@SuppressWarnings("unused")
	void foo() {;}
    
	//if there were no values the ()'s can be omitted
	@Override
	public String toString() {return "aaaaaaaaa";}

	//its possible to have multiple annotations on the same delaration

//	//many annotations replace comments
//	 	xmpl 
//		//creator : omar
//		//current version: 6
//		//reviewers: ziyech, kante, mount
//		// those meta infos can be replace by a custom annotation the syntax is
//		
		@Documented
		@interface Classpreamble {
			String creator() default "omar";
			int version() default 0;
			String[] reviewers();
		}
		
//		// the documented annotation makes the infos about our custom annotation available in the Javadoc-generated documentation
//		//the annotation def is similar to the def of an interface but preceded with an @
//		//annotation type elemnts look like methods and can have optional values. to use the annotation 
//		// for a class
//		
		@Classpreamble (creator = "omar", version = 2, reviewers = {"kante, mount,ziya5"}) //version can be ommitted since it has a default val
		class goo { ; }
//	
//		/*some predefined annots in java.lang.annotation.*
//		 * @Deprecated : The compiler generates a warning whenever a program uses a method, class, or field with the @Deprecated annotation
//		 
//		 * @Override @Override annotation informs the compiler that the element is meant to override an element declared in a superclass. 
//		 *  not required but helps to prevent errors:if a method marked with @Override fails to correctly override a method in one of its superclasses,
//		 *  the compiler generates an error.
		
//		 *  @SuppressWarnings @SuppressWarnings annotation tells the compiler to suppress specific warnings that it would otherwise generate.
//		 *   xmpl: @SuppressWarnings("deprecation")
//		 *   		void usedeprecatedmethod() {....}
//		 * 
//		 * Every compiler warning belongs to a category. The Java Language Specification lists two categories: deprecation and unchecked. 
//		 * The unchecked warning can occur when interfacing with legacy code written before the advent of generics.
		
//		 * To suppress multiple categories of warnings, use the following syntax:
//				@SuppressWarnings({"unchecked", "deprecation"})
//			
//		  for the rest of the list https://docs.oracle.com/javase/tutorial/java/annotations/predefined.html
//				

// * Where Annotations Can Be Used
//		Annotations can be applied to declarations: declarations of classes, fields, methods, and other program elements.
//		When used on a declaration, each annotation often appears, by convention, on its own line.

//		 */


}


