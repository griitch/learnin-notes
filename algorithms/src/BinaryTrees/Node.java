package BinaryTrees;

public class Node<K extends Comparable<K>,T> {

    T value;
    final K key;
    int count;
    Node<K,T> left = null;
    Node<K,T> right = null;

    public Node(K key,T data, int count) {
        this.key = key;
        this.value = data;
        this.count = count;
    }

    public Node(T value, K key, Node<K, T> left, Node<K, T> right, int count) {
        this.value = value;
        this.key = key;
        this.left = left;
        this.right = right;
        this.count = count;
    }


}
