package collections;

import java.util.*;

public class Sets {
	
	// 3 set exist : HashSet, LinkedHashSet, TreeSet
	// LinkedHashSet : A hash table with a linkd list runnin thru its elements to maintain order of insertion
	// TreeSet : a red black tree, to keep items sorted
	// HashSet : a hashtable, no particular ordering of items 
	
	public static void main(String[] args) {
		
		Set<Integer> sett = new HashSet<>();
		Set<Integer> sett2 = new HashSet<>(new ArrayList<>());
		
		// ops : 
		// add and remove particular elems 
		sett.add(5);
		sett.remove(5);
		
		if(!sett2.containsAll(sett)) { // is subset
			 
			sett2.retainAll(sett); // intersection
			sett2.removeAll(sett2); // difference
			sett2.addAll(sett); // union
			
		}
		
		
	}

}
