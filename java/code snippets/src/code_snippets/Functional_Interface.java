package code_snippets;

//public abstract class Functional_Interface { this does not work, it NEEDS to be an INTERFACE, not an abstract class
public interface Functional_Interface { 
	
	abstract Boolean verify(int a); 
	
	public default void printcrap() {
		System.out.println("hi im here to prove that a functional interface"
				+ " can also have non abstract methods." );
	}
	
	public static void printshit()
	{
		System.out.println("interfaces can have static and default methods");
	}
	
	
}


class evencomparator implements Functional_Interface {
	public Boolean verify(int a) {
		return a%2==0;
	}
}

//le premier fichier a �t� encombr� donc je prends des notes sur celui-ci:
/* la syntaxe d'une expressison labmda se constitue de
 * 1- une liste de param�tres formels s�par�s par des virgules et entre parenth�ses.
 * 	  on peut ommettre le type des donn�es dans une lambda expression, et les parenth�ses s'il n y a
 * 	  qu'un seul param
 * 2- la fl�che
 * 3- le corps de la fonction, peut �tre un bloc ou une seule expression
 * 	  si on n'a utilis� qu'une seule expression jre �value l'expression et la retourne (pas besoin de taper return machintruc) 
 * ---------------------------------------------------------------------------------
 * acc�s aux variables locales et aux membres du scope englobant:
 * comme les classes locales et anonymes, les lambda peuvent capturer les variables locales des methodes
 * ca veut dire qu'elles ne peuvent acc�der qu'aux variable locales d�lcar�es finales
 * ou effectivement finales. elles ont �galement acc�s aux membres de la classes englobante
 * mais contrairement aux classes locales et anon, lambda
 * n'ont pas de probl�mes avec le shadowing, il n'h�ritent pas de noms de la superclass
 *  the lambda expression does not introduce a new level of scoping
 *  suppose we have an attribute named x in the encolsing class, we can't define an attribute named x in the lambda 
 *  + this keyword refers to the enclosing class, super refers to its superclass
 *
 *  */
