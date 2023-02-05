package BinaryTrees;

public class RedBlackNode<K extends Comparable<K>,T>{

     static final boolean RED = true;
     static final boolean BLACK = false;

    T value;
    K key;
   // int count;
    RedBlackNode<K,T> left = null;
    RedBlackNode<K,T> right = null;
    boolean color; // color of parent node

    public RedBlackNode(K key,T value, boolean color) {
        this.color = color;
        this.value = value;
        this.key = key;
    }


     static RedBlackNode rotateLeft(RedBlackNode h) {
        RedBlackNode x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = BLACK;
        h.color = RED;
        return x; // the new root of the subtree who was previously the right
    }

     static void flipColors(RedBlackNode h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

     static RedBlackNode rotateRight(RedBlackNode h) {
        RedBlackNode x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    public static boolean isRED(RedBlackNode x) {
        if(x == null)
            return false; // null links are black
        return x.color == RED;
    }
}
