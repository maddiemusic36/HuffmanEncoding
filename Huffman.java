public class Huffman<K,V> {
    
    private int size;
    private Node root;

    Huffman() {
        this.size = 0;
        this.root = null;
    }

    ///// function to add value to tree \\\\\
    
    ///// function to create the encoding \\\\\

    ///// function to print the tree \\\\\
}

class Node {
    char key;
    int frequency;
    Node left;
    Node right;

    Node(Character key, Integer value) {
        this.key = key;
        this.frequency = value;
        this.left = null;
        this.right = null;
    }

    public String toString() {
        return "(" + key + ", " + frequency + ")";
    }
}


// once more