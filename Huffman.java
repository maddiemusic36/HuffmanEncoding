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
    
    public String encode(String string) {
    /**
     * This method creates the encoding of the given string using the Huffman
     * tree. It does this by ____
     */
        return "";
    }
    
    public String decode(String string) {
    /**
     * This method decodes the Huffman tree using the given encoding. It
     * traverses left for every 0, and right for every 1. If a leaf node is
     * reached, it adds the key of that node to the final result.
     * Args: string, which is a string of 1s and 0s
     * Returns: the decoded string
     */
    	String result = "";
    	Node currNode = root;
    	
    	// iterate through input string
    	for (int i = 0; i < string.length(); i++) {
    		// if we reach a leaf, add char to result and go back to top of tree
    		if (currNode.left == null && currNode.right == null) {
    			result += currNode.key;
    			currNode = root;
    		}
    		// if string[i] = 0, go left
    		else if (string.charAt(i) == '0')
    			currNode = currNode.left;
    		// if string[i] = 1, go right
    		else 
    			currNode = currNode.right;
    	}
        
    	return result;
    }
    
    public String printTree(Node cur) {
    /**
     * This method recusively creates a String visualization of the Huffman Tree.
     * Args: None
     * Returns: a string representation of the tree
     */
        // base case
        if (cur == null) { return "null"; }

        String str = cur + " {";
        // recurse left
        str += printTree(cur.left);
        str += "} {";
        // recurse right
        str += printTree(cur.right);
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

