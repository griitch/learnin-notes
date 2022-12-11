package misc;

import java.util.Iterator;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Sorting {
	
	private static void merge(int[] t, int[] aux, int lo,int mid, int hi) {
		// merge 2 sorted sub arrays : the first is a[lo:mid], the second a[mid+1,hi]
		
		for(int i = lo ; i < hi ; i++ ) {
			aux[i] = t[i]; // put items in an auxiliary array	
		}
		
		int i = lo, j = mid+1;
		
		for(int k = lo ; k <= hi ; k++) {
			if( i > mid )
				t[k] = aux[j++];
			else if(j > hi)
				t[k] = aux[i++];
			else if (t[i] < t[j])
				t[k] = aux[i++];
			else 
				t[k] = aux[j++];
		}
	}
	private static void swap(int[] a, int i, int j ) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void quickSort(int[] arr, int lo, int hi) {
		
		if(hi <= lo)
			return;
		
		
		// this is a 3 way partition, it handles duplicate keys
		// by putting all the elements equal to the pivot in their right place
		// in the middle of the lt and gt pointers
		
		int lt = lo;
		int gt = hi;
		int v = arr[lo];
				
		for(int i = lo ; i <= gt ; i++)
		{
			if(arr[i] > v)
				swap(arr, i--, gt--);
			
			if(arr[i] < v)
				swap(arr, i, lt++);			
		}
		quickSort(arr,lo,lt-1);
		quickSort(arr, gt+1, hi);
		
	}
	
	public static void mergeSort(int[] t, int[] res, int hi,int lo) {
		if(hi <= lo)	
			return;
		int mid = lo + (hi - lo)/2;
		mergeSort(t,res,lo,mid);
		mergeSort(t,res,mid+1,hi);
		merge(t,res,lo,mid,hi);
		
	}
	
	
	public static void main(String[] args) {
		int[] foo = {4,6,5,6,4,3,11,2,3,5,6,88,9,7,8,0,5,4,3,5,66};
	
		
		quickSort(foo,0,foo.length-1);
		
		for(Integer baz : foo) {
			System.out.print(baz);
			System.out.print(" ");
		}
	}
	
	
	
}
