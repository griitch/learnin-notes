package BinaryTrees;
import static BinaryTrees.RedBlackNode.*;

public class RedBlackBST<K extends Comparable<K>,T>{
    RedBlackNode<K,T> root;

    private RedBlackNode put(RedBlackNode h, K k, T t) {
        if(h == null) return new RedBlackNode<>(k,t,RED);

        int cmp = k.compareTo((K) h.key);
        // usual bst insertion

        if (cmp < 0) h.left = put(h.left,k,t);
        else if(cmp > 0) h.right = put(h.right,k,t);
        else h.value = t;

        if(isRED(h.right) && !isRED(h.left)) h = rotateLeft(h); // lean left
        if(isRED(h.left) && isRED(h.left.left)) h = rotateRight(h); // balance 4-node
        if(isRED(h.left) && isRED(h.right)) flipColors(h); // split 4 node

        return h;
    }

    public void put(K k, T t) {
        root = put(root,k,t);
    }

}
