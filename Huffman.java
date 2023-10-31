public class Huffman<K,V> {
    
    private int size;
    private Node<K,V> root;

    Huffman() {
        this.size = 0;
        this.root = null;
    }

    ///// function to add value to tree \\\\\
    
    ///// function to create the encoding \\\\\

    ///// function to print the tree \\\\\
}

class Node<K,V> {
    String key;
    int val;
    Node<K,V> left;
    Node<K,V> right;

    Node(String key, int value) {
        this.key = key;
        this.val = value;
        this.left = null;
        this.right = null;
    }
}


// :(