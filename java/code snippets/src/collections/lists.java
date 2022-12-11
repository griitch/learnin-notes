package collections;
import java.util.*;

public class lists { 
	
	 public static void main(String[] args) {
		 @SuppressWarnings("unused")
		 
	        // 1. initialize
	        List<Integer> v0 = new ArrayList<>();
	        List<Integer> v1;  // v1 == null
	        
	        // 2. cast an array to a vector
	        Integer[] a = {0, 1, 2, 3, 4};  // does not work with int[]
	        v1 = new ArrayList<>(Arrays.asList(a));
	        
	        // 3. make a copy
	        List<Integer> v3 = new ArrayList<>(v1);     // make an actual copy of v1
	        
	        // 3. get length
	        System.out.println("The size of v1 is: " + v1.size());
	        
	        // 4. access element
	        System.out.println("The first element in v1 is: " + v1.get(0));
	        
	        // 5. modify element
	        System.out.println("The first element in v1 is: " + v1.get(0));
	        v3.set(0, -1); // v3.set(index, content)
	        System.out.println("The first element in v1 is: " + v1.get(0));
	        
	        // 7. sort
	        Collections.sort(v1);
	        
	        // 8. add new element at the end of the vector
	        v1.add(-1);
	        
	        // 9. delete the last element
	        v1.remove(v1.size() - 1);
	        
	        //10- reverse a list
	        Collections.reverse(v3);
	    }
}
