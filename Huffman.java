public class Huffman {
    
    public Node root = null;

    public void buildTree(PriorityQueue pQueue) throws Exception {
    /**
     * This method builds the Huffman tree using a PriorityQueue of Nodes of
     * characters and their frequencies.
     * Args: pQueue, which is a PriorityQueue instance
     * Returns: None
     */
        // loop through the whole priority queue
        while (pQueue.size() > 1) {
            // get the current two smallest frequencies
            Node least = pQueue.dequeue();
            Node secLeast = pQueue.dequeue();

            // creaty an "empty" node whose frequency is the sum of the smallest
            int newFrequency = least.frequency + secLeast.frequency;
            Node cur = new Node('\0', newFrequency);
            
            // make cur the parent of the two smallest
            cur.left = least;
            cur.right = secLeast;

            // add the new frequency back into the priority queue
            pQueue.enqueue(cur);
        }

        // set the root to the newly created tree
        root = pQueue.dequeue();
    }
    
    ///// function to create the encoding \\\\\

    
    public String toString(Node cur) {
    /**
     * This method recusively creates a String visualization of the Huffman Tree.
     * Args: None
     * Returns: a string representation of the tree
     */
        // base case
        if (cur == null) { return "null"; }

        String str = cur + " {";
        // recurse left
        str += toString(cur.left);
        str += "} {";
        // recurse right
        str += toString(cur.right);
        str += "}";

        return str;
    }
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

