public class Huffman {
    
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
    
    ///// function to create the encoding list \\\\\
    public void createEncodingList() {
    	String traversal = "";
    	Node currNode = root;
    	encodingList = new EncodingList(pqSize);
    	
    	createEncodingListHelper(traversal, currNode);
    	//encodingList.printList();
    }
    
    private void createEncodingListHelper(String traversal, Node currNode) {
    	if (currNode.right == null && currNode.left == null) {
    		EncodeNode addToArray = new EncodeNode(currNode.key, traversal);
    		encodingList.add(addToArray);
    		return;
    	}
    		
    	traversal += "0";
    	createEncodingListHelper(traversal, currNode.left);
    	traversal = traversal.substring(0, traversal.length() - 1);
    	
    	traversal += "1";
    	createEncodingListHelper(traversal, currNode.right);
    	traversal = traversal.substring(0, traversal.length() - 1);
    }


    ///// function to print the tree \\\\\
    public void printTree() {
    	
    }
    
    // function to decode
    public String decode(String string) {
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

class EncodingList {
	EncodeNode[] list;
	int currIndex;
	int pqSize;
	
	EncodingList(int pqSize) {
		list = new EncodeNode[pqSize];
		currIndex = 0;
		this.pqSize = pqSize;
	}
	
	public void add(EncodeNode node) {
		list[currIndex] = node;
		currIndex++;
	}
	
	public void printList() {
		for (int i = 0; i < pqSize; i++) {
			System.out.println(list[i].key +" "+ list[i].encoding);
		}
	}
	
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < pqSize; i++) {
			result += (list[i].key + ": " + list[i].encoding + " // ");
		}
		return result;
	}
}

class EncodeNode {
	char key;
	String encoding;
	
	EncodeNode(char key, String encoding) {
		this.key = key;
		this.encoding = encoding;
	}
	
	public String toString() {
        return "(" + key + ", " + encoding + ")";
    }
}

