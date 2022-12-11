package PrioQ;

import java.util.Arrays;

public class BinaryMaxHeap<T extends Comparable> {
    //inna max heap each item is bigger than its children

    private T[] items;
    private int n; // number of elems in heap

    public BinaryMaxHeap(int capacity) {
        this.n = 0;
        items = (T[]) new Comparable[capacity + 1]; // 1 indexed

        // left child of k : 2k
        // right child : 2k + 1
        // parent of k : k/2
    }

    public void insert(T t) {
        items[++n] = t;
        swim(n);
    }

    public T poll() {
        T t = items[1];
        swap(1,n--);
        sink(1);
        items[n+1] = null;
        return t;
    }

    public boolean isEmpty() {return n == 0; }

    public T[] heapSort() {
        T[] arr = (T[]) new Comparable[n];
        int i = n-1;
        while (!isEmpty()) {
            arr[i--] = poll();
        }

        return arr;
    }


    private void swim(int k) {

        // when an element is larger than its parent, make it swim to its place

        while (k > 1 && isLessThan(k/2,k) ) {
            swap(k,k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        // when an element is smaller than its children, make it sink to the bottom of the heap

        while ( 2*k <= n) {
            int j = 2*k;
            if( j < n && isLessThan(j,j+1) ) j++; // choose the biggest child
            if(isLessThan(j,k)) break;
            // break if the node is greater than its biggest child
            swap(k,j);
            k = j;
        }

    }

    private void swap(int a, int b) {
        T t = items[a];
        items[a] = items[b];
        items[b] = t;
    }

    private boolean isLessThan(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    private void print() {
        int k = 1;

        while (k <= n) {
            for(int i = k; i < k*2; i++) {
                if(i <= n)
                System.out.print(items[i] + " ");
            }
            k = 2*k;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(12);
        
        int[] f = {4,2,3,48,9,0,12,10,23,34,54,31};
        for (int i : f) {
            heap.insert(i);
        }
        heap.print();
        heap.poll();
        heap.poll();
        System.out.println();
        heap.print();

        Comparable[] arr = heap.heapSort();
        System.out.println();
        Arrays.stream(arr).forEach(integer -> System.out.print(integer + " "));
    }


}
