package misc;

public class MergeSort {

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {

        if(hi <= lo) {
            return;
        }
        int mid = lo + (hi-lo)/2;

        sort(a,aux,lo,mid);
        sort(a,aux,mid+1,hi);

        merge(a, aux, lo, mid, hi);
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {


        // a[lo : mid ] and a [mid+1, hi] are 2 sorted arrays
        // merge them into a

        for (int i = lo; i <= hi ; i++) {
            aux[i] = a[i];
        }
        int i = lo;
        int j = mid+1;

        for (int k = lo; k <= hi ; k++) {

            if(i > mid) { a[k] = aux[j++]; }

            else if(j > hi)  { a[k] = aux[i++]; }

            else if ( aux[i].compareTo(aux[j]) < 0 ) { a[k] = aux[i++]; } // if aux[i] is less than aux[j]

            else { a[k] = aux[j++]; }
        }



    }


}
