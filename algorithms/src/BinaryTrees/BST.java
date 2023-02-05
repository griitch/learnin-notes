package BinaryTrees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BST<K extends Comparable<K>,T> {
    private Node<K,T> root;

    public BST(Node<K, T> root) {
        this.root = root;
    }

    public void put(K key, T value ) {
        // if the key is alrdy in the tree replace the value
        root = put(root,key,value);
    }

    private Node<K,T>put(Node<K,T>x, K key, T val) {
        if(x == null) {
            return new Node<>(key,val,0);
        }
        int cmp = key.compareTo((K) x.key);
        if (cmp < 0 ) {
            x.left = put(x.left,key,val);
        } else if ( cmp > 0 ) {
            x.right = put(x.right,key,val);
        } else {
            x.value = val;
        }
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public int size(Node<K,T>x) {
        return x == null ? 0 : x.count;
    }

    public T get(K key) {
        Node<K,T>x = root;
        while (x != null) {
            int cmp = key.compareTo((K) x.key);
            if(cmp < 0 ) x = x.left;
            else if (cmp > 0) x = x.right;
            else return (T) x.value;
        }
        return  null;
    }

    public K min() {
        return min(root);
    }

    public K max() {
        return max(root);
    }


     public K floor(K k) {
        // floor of k : largest key in the bst that is <= k
         return floor(root,k);
     }

     private K floor(Node<K,T>x, K k) {

        if(x == null) {
            return null;
        }

         if(x.key.compareTo(k) == 0) {
             return (K) x.key;
         }

        if(x.key.compareTo(k) > 0) {
            return floor(x.left,k);
        }

         K right = floor(x.right,k);
         if(right != null) {
             return right;
         }
         return (K) x.key;

     }

     public K ceil(K k) {
        return null;
     }

    private K min(Node<K,T>x) {
        // keep going left till the next left is null
        if(x.left == null)
            return (K) x.key;
       return min(x.left);
    }

    private Node<K,T>minNode(Node<K,T>x) {
        // keep going left till the next left is null
        if(x.left == null)
            return  x;
        return minNode(x.left);
    }

    private K max(Node<K,T>x) {
        if(x.right == null)
            return (K) x.key;
        return max(x.right);
    }

    public void delete(K key) {

    }

    public void delere(K key) {
        root = delete(root,key);
    }

    private Node<K,T>delete(Node<K,T>x, K key) {
        if(x == null)
            return null;

        int cmp = key.compareTo((K) x.key);
        if( cmp > 0 ) {
            x.right = delete(x.right,key);
        } else if (cmp < 0 ) {
            x.left = delete(x.left,key);
        } else {

            if( x.right == null ) return x.left;

            if( x.left == null ) return  x.right;

           Node<K,T>t = x;
           x = minNode(t.right);
           x.right = deleteMin(t.right);
           x.left = t.left;
        }

        x.count = size(x.left ) + size(x.right) + 1;

        return x;
    }
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node<K,T>deleteMin(Node<K,T> x) {
        if(x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }


    // number of elements with keys < than the key param
    private int rank(K key, Node<K,T> root) {

        if(root == null) {
            return 0;
        }

        if(root.key.compareTo(key) > 0) {
            return 1 + rank(key,root.left);
        } else {
            return 1 + rank(key,root.left) + rank(key,root.right);
        }
    }

    public int rank(K key) {
        return rank(key,root);
    }

    public int rangeCount(K lo, K hi) {
        if(get(hi) != null) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    public List<K> keysBetween(K lo, K hi) {
        List<K> l = new ArrayList<>();
        keysBetween(lo,hi,root,l);
        return l;
    }

    private void keysBetween(K lo, K hi, Node<K,T> root, List<K> list ) {
        if( root == null )
            return;

        if(root.key.compareTo(lo) > 0) {
            keysBetween(lo,hi,root.left,list);
        }

        if(root.key.compareTo(lo) > 0
        && root.key.compareTo(hi) < 0) {
            list.add(root.key);
        }

        if(root.key.compareTo(hi) < 0 ) {
            keysBetween(lo,hi,root.right,list);
        }

    }
}
