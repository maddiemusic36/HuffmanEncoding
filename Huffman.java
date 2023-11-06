public class Huffman<K,V> {
    
    private int size;
    private Node root;

    Huffman() {
        this.size = 0;
        this.root = null;
    }

    public void insertNode(Node newNode, Node cur) {
    /**
     * This method recursively inserts a node into the Huffman tree.
     * Args: newNode, which is the Node object to be inserted
     * Returns: None
     */
        // check if the tree is empty
        if (cur == null) { cur = newNode; }

        // otherwise, check the frequencies to determine the location
        else {
            // if the new frequency is less than the current frequency
            if (newNode.frequency <= cur.frequency) {
                // check if the current node has a left child
                if (cur.left == null) {
                    cur.left = newNode;
                }
                else {
                    // recurse on the left child
                    insertNode(newNode, cur.left);
                }
            }
            // if the new frequency is greater than the current frequency
            if (newNode.frequency > cur.frequency) {
                // check if the current node has a right child
                if (cur.right == null) {
                    cur.right = newNode;
                }
                else {
                    // recurse on the right child
                    insertNode(newNode, cur.right);
                }
            }
        }
    }

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
            Node cur = new Node(null, least.frequency + secLeast.frequency);
            
            // make cur the parents of the two smallest
            cur.left = least;
            cur.right = secLeast;

            // add the new frequency to the tree
            insertNode(cur, root);

            // add the new frequency back into the priority queue
            pQueue.enqueue(cur);
        }
    }
    
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