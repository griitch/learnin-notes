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

//le premier fichier a été encombré donc je prends des notes sur celui-ci:
/* la syntaxe d'une expressison labmda se constitue de
 * 1- une liste de paramètres formels séparés par des virgules et entre parenthèses.
 * 	  on peut ommettre le type des données dans une lambda expression, et les parenthèses s'il n y a
 * 	  qu'un seul param
 * 2- la flèche
 * 3- le corps de la fonction, peut être un bloc ou une seule expression
 * 	  si on n'a utilisé qu'une seule expression jre évalue l'expression et la retourne (pas besoin de taper return machintruc) 
 * ---------------------------------------------------------------------------------
 * accès aux variables locales et aux membres du scope englobant:
 * comme les classes locales et anonymes, les lambda peuvent capturer les variables locales des methodes
 * ca veut dire qu'elles ne peuvent accéder qu'aux variable locales délcarées finales
 * ou effectivement finales. elles ont également accès aux membres de la classes englobante
 * mais contrairement aux classes locales et anon, lambda
 * n'ont pas de problèmes avec le shadowing, il n'héritent pas de noms de la superclass
 *  the lambda expression does not introduce a new level of scoping
 *  suppose we have an attribute named x in the encolsing class, we can't define an attribute named x in the lambda 
 *  + this keyword refers to the enclosing class, super refers to its superclass
 *
 *  */
