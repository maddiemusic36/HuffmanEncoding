public class Huffman {
/**
 * This class represents a Huffman tree, or a tree whose nodes are organized
 * by frequency to help with the compression of data.
 */
    
    // huffman instance variables
    public Node root = null;
    public int pqSize;
    public EncodingList encodingList;

    public void buildTree(PriorityQueue pQueue) throws Exception {
    /**
     * This method builds the Huffman tree using a PriorityQueue of Nodes of
     * characters and their frequencies.
     * Args: pQueue, which is a PriorityQueue instance
     * Returns: None
     */
    	pqSize = pQueue.size();
        // loop through the whole priority queue
        while (pQueue.size() > 1) {
            // get the current two smallest frequencies
            Node least = pQueue.dequeue();
            Node secLeast = pQueue.dequeue();

            // create an "empty" node whose frequency is the sum of the smallest
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
    
    public void createEncodingList() {
    /**
     * This method creates a list of all of the encoded sequences for each
     * unique character in the input file. 
     * Args: None
     * Returns: None
     */
    	String traversal = "";
    	Node currNode = root;
        // list whose size is the number of unique characters in the input
    	encodingList = new EncodingList(pqSize);
    	
    	createEncodingListHelper(traversal, currNode);
    }
    
    private void createEncodingListHelper(String traversal, Node currNode) {
    /**
     * This method creates a list of all encoded sequences for each unique
     * character in the input file. It does this using recursive backtracking
     * to find each leaf node. When a leaf node is found, it adds the current
     * sequence to the list of encoded sequences.
     * Args: traversal, which is the current encoded sequence
     *       currNode, which is the current node in the tree
     * Returns: None
     */
        // if a leaf node is found
    	if (currNode.right == null && currNode.left == null) {
            // add the current node to the encoding list
            currNode.encoding = traversal;
    		encodingList.add(currNode);
    		return;
    	}
    	
        // traverse left
    	traversal += "0";
    	createEncodingListHelper(traversal, currNode.left);
    	traversal = traversal.substring(0, traversal.length() - 1);
    	
        // traverse right
    	traversal += "1";
    	createEncodingListHelper(traversal, currNode.right);
    	traversal = traversal.substring(0, traversal.length() - 1);
    }

    public String encode(String string) {
    /**
     * This method creates the encoding of the given string using the Huffman
     * tree. It does this by looping through the given string, finding the 
     * associated encoding sequence, and adding that to the final string.
     * Args: string, which is the input data to be encoded
     * Returns: a string of 1s and 0s representing the encoded input
     */
       String result = "";
       for (int i = 0; i < string.length(); i++) {
    	   result += encodingList.getEncoding(string.charAt(i));
       }
       return result;
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
        if (cur == null) { return ""; }

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


///////////
class Node {
/**
 * This class represents a node, which contains a key, a frequency, a left
 * pointer, and a right pointer.
 */

    // node instance variables
    char key;
    int frequency;
    String encoding;
    Node left;
    Node right;

    Node(Character key, Integer value) {
    /**
     * This constructor initializes an instance of Node. It assigns the given
     * key/value pair to the key and frequency, and sets the pointers to null.
     * Args: key, which is a character
     *       value, which is an integer
     */
        this.key = key;
        this.frequency = value;
        this.left = null;
        this.right = null;
    }

    public String toString() {
    /**
     * This method creates a string representation of the node
     * Args: None
     * Returns: a string displaying the key and frequency of the node
     */
        return "(" + key + ", " + frequency + ")";
    }
}


///////////
class EncodingList {
/**
 * This class represents an encoding list, which is a primitive list of
 * EncodeNode objects
 */

    // encoding list instance variables
	Node[] list;
	int currIndex;
	int pqSize;
	
	EncodingList(int pqSize) {
    /**
     * This constructor initalizes the list to a primitive list of the given size.
     * Args: pqSize, which is an integer indicating the size of the encoding list
     */
		list = new Node[pqSize];
		currIndex = 0;
		this.pqSize = pqSize;
	}
	
	public void add(Node node) {
    /**
     * This method adds a new value to the encoding list.
     * Args: node, which is an EncodeNode object to be added
     * Returns: None
     */
		list[currIndex] = node;
		currIndex++;
	}
	
	public String getEncoding(char givenChar) {
	/**
	 * This method returns the encoding for the given character.
	 * Args: givenChar, which is a char from the string to be encoded.
	 * Returns: the encoded string for the character
	 */
		String encoding = "NOT FOUND";
		for (int i = 0; i < pqSize; i++) {
			if (list[i].key == givenChar) {
				encoding = list[i].encoding;
			}
		}
		return encoding;
	}
	
	@Override
	public String toString() {
    /**
     * This method creates a string representation of the encoding list
     * Args: None
     * Returns: a string displaying the key and encoding of each EncodeNode
     *          in the list
     */
		String result = "";
		for (int i = 0; i < pqSize; i++) {
			result += (list[i].key + ": " + list[i].encoding + " // ");
		}
		return result;
	}
}

