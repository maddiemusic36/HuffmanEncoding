import java.util.ArrayList;

public class PriorityQueue<K, V> {
/**
 * This class represents a Priority Queue. Just like a regular queue, this
 * class follows the First in First out procedure. However, items can move up
 * or down in the queue depending on their frequency number. Higher numbers
 * represent higher frequency and vice versa. This class has several methods to
 * manipulate and interact with the Priority Queue.
 */

    // priority queue class variables
    MaxHeap pQueue;
    int size;

    private class MaxHeap {
    /**
     * This class represents a Binary Max-Heap. It utilizes an array list to
     * hold Node objects. As per the definition of Max-Heaps, Nodes with a
     * higher frequency number belong higher on the heap. This class has
     * several methods to manipulate and interact with the Heap.
     */

        // declare the Heap class variables
        ArrayList<Node> heap;
        int size;

        MaxHeap() {
        /**
         * This constructor creates an array list of size 26 which holds Node
         * objects. It also sets the size variable to 0, reflecting the
         * emptiness of the Heap.
         */
            heap = new ArrayList<>(26);
            size = 0; 
        }

        private int parent(int i) {
        /**
         * This method calculates an integer representing the parent of the
         * Node at the given index i.
         * @param  i  an integer representing the index of a Node in the heap
         * @return    the index of the parent
        */
            return i/2;
        }

        private int left(int i) {
        /**
         * This method calculates an integer representing the left child of the 
         * Node at the given index i.
         * @param  i  an integer representing the index of a Node in the heap
         * @return    the index of the left child
         */
            return 2*i;
        }

        private int right(int i) {
        /**
         * This method calculates an integer representing the right child of the 
         * Node at the given index i.
         * @param  i  an integer representing the index of a Node in the heap
         * @return    the index of the right child
         */
            return (2*i + 1);
        }

        private void bubbleUp(int i) {
        /**
         * This method traverses upwards through the heap starting at the given
         * index and ending at the top of the heap, swapping elements when
         * necessary to maintain the order of priorities. It bubbles all the way
         * to the top using recursion.
         * @param  i  an integer representing an index of a Node in the heap
         * @return    void
         */
            // check that the index is not the top of the heap
            if (i > 1) {
                Node bubbleNode = heap.get(i);
                Node currParent = heap.get(parent(i));
                // check if a swap is needed by comparing frequencies
                if (bubbleNode.frequency > currParent.frequency) {
                    // swap the index and its lower frequency parent
                    heap.set(parent(i), bubbleNode);
                    heap.set(i, currParent);
                    // recurse
                    bubbleUp(parent(i));
                }
            }
        }

        private void bubbleDown(int i) {
        /**
         * This method traverses downwards through the heap starting at the
         * given index and ending at the bottom of the heap, swapping elements
         * when necessary to maintain the order of frequencies. It bubbles all
         * the way down using recursion.
         * @param  i  an integer representing an index of a Node in the heap
         * @return    void
         */
            Node bubbleNode = heap.get(i);
            // check that the index has a left child
            if (left(i) <= size) {

                // find the index of the higher frequency child
                int higherFrequencyChildIdx = left(i);
                if (right(i) <= size && heap.get(right(i)).frequency > heap.get(left(i)).frequency) {
                    higherFrequencyChildIdx = right(i);
                }

                // get the higher frequency child
                Node child = heap.get(higherFrequencyChildIdx);

                // check if a swap is needed by comparing frequencies
                if (bubbleNode.frequency < child.frequency) {
                    // swap the index with its child of higher frequency
                    heap.set(higherFrequencyChildIdx, bubbleNode);;
                    heap.set(i, child);
                    // recurse
                    bubbleDown(higherFrequencyChildIdx);
                }
            }
        }

        public void add(Node n) {
        /**
         * This method adds a new Node object to the heap. If necessary, the
         * array is resized to twice its size before adding. The new Node is
         * added to the bottom of the heap and then bubbled to its proper place.
         * @param  n  An instance of the Node class
         * @return    void
         */
            // resize the array to twice its size if necessary
            if ( (size + 1) >= heap.size()) { resize(2 * heap.size()); }
            size++;
            // add the new element to the end of the array and bubble it up
            heap.set(size, n);
            bubbleUp(size);
        }

        public void resize(int capacity) {
        /**
         * This method resizes the array by creating a new array and
         * transferring all of the original values over. Then, it assigns the
         * array pointer to this new array.
         * @param  capacity  An integer specifying the length of the new array
         * @return           void
         */
            ArrayList<Node> temp = new ArrayList<>(capacity);
            // assign size to the lesser value between capacity and size
            size = capacity < size ? capacity : size;
            for (int i = 0; i < size; i++) {
                // add each value in the original array to the new array
                temp.set(i, heap.get(i));
            }
            heap = temp;
        }

        public Node remove() {
        /**
         * This method removes the Node at the top of the heap. Next, the Node
         * at the very bottom at the heap is placed at the top and then bubbled
         * down to maintain the order of the heap. If the heap is empty, this
         * method returns null.
         * @param   none
         * @return  the Node object that was removed from the top of the heap
         */
            // check if the heap is empty
            if (isEmpty()) { return null; }

            // get the Node that will be removed from the top of the heap
            Node removed = heap.get(1);
            // move the largest index (lowest priority) to the top
            heap.set(1, heap.get(size));
            heap.set(size, null);
            size--;
            // bubble down the heap to rearrange the values where necessary
            bubbleDown(1);
            return removed;
        }

        public boolean isEmpty() {
        /**
         * This method determines if the heap is empty by checking if its size
         * equals 0. It returns a boolean value representing the emptiness.
         * @param   none
         * @return  a boolean value representing if the heap is empty or not
         */
            return size == 0;
        }

        public String toString() {
        /**
         * This method creates a String representation of the heap. This string
         * is surrounded by curly brackets, and each element in the heap is
         * separated by a comma.
         * @param   none
         * @return  a String representation of the heap
         */
            String s = "{";
            // iterate through the heap
            for (int i = 1; i <= size; i++) {
                // add a comma before the next value unless it's the first element
                s += (i == 1 ? "" : ", ") + heap.get(i);
            }
            s += "}";
            return s;
        }
    }


    PriorityQueue() {
    /**
     * This constructor initializes the pQueue variable to an empty heap. It
     * also sets the size variable to 0, reflecting the emptiness of the queue.
     */
        pQueue = new MaxHeap();
        size = 0;
    }

    void enqueue(Node newNode) {
    /**
     * This method adds a new Node to the queue and increments the size.
     * @param  newNode   a Node object
     * @return           void
     */
        pQueue.add(newNode);
        size++;
    }

    Node dequeue() throws Exception {
    /**
     * This method removes a Node from the front of the queue, returns
     * their name, and decrements size. If the queue is empty, this method
     * throws a Null Pointer Exception because you can't dequeue an empty queue.
     * @param   none
     * @return  the dequeued Node object
     */
        // check if the queue is empty
        if ( !(pQueue.isEmpty())) {
            Node dqNode = pQueue.remove();
            size--;
            return dqNode;
        }
        else { throw new NullPointerException(); }
    }

    Node peek() throws Exception {
    /**
     * This method returns the Node at the front of the queue without
     * dequeueing it. If the queue is empty, this method throws a Null Pointer
     * Exception because you can't peek into an empty queue.
     * @param   none
     * @return  the Node at the front of the queue
     */
        // check if the queue is empty
        if ( !(pQueue.isEmpty()) ) {
            Node frontNode = (pQueue.heap).get(1);
            return frontNode;
        }
        else { throw new NullPointerException(); }
    }

    int peekPriority() throws Exception {
        /**
         * This method returns the priority of the Node at the front of the queue
         * without dequeueing them. If the queue is empty, this method throws a 
         * Null Pointer Exception becuase you can't peek into an empty queue.
         * @param   none
         * @return  the priority of the Node at the front of the queue
         */
        // check if the queue is empty
        if ( !(pQueue.isEmpty())) {
            Node frontNode = (pQueue.heap).get(1);
            return frontNode.frequency;
        }
        else { throw new NullPointerException(); }
    }

    void changePriority(String name, int newPriority) {
    /**
     * This method changes the priority of the Node in the queue with the given
     * name. If this change increases the priority, the Node is bubbled up. If
     * it decreases the priority, the Node is bubbled down. Otherwise, nothing
     * happens. If there are multiple paitents with the same name, this method
     * only affects the priority of the first person found. If there is no Node
     * in the queue with that name, this method does nothing.
     * @param  name         the key of a Node in the queue
     * @param  newPriority  the new priority number
     * @return              void
     */
        // iterate through the heap and find the given name (if it exists)
        Node changeNode = null;
        int changeNodeIdx = 0;
        ArrayList<Node> heap = pQueue.heap;
        for (int i = 1; i < heap.size(); i++) {
            // if the name is found in the queue
            if (heap.get(i).key == name) {
                // get the Node and its index
                changeNode = heap.get(i);
                changeNodeIdx = i;
                break;
            }
        }

        // if a patient with that name is found, change their priority
        if (changeNode != null) {
            // if the new priority is higher than the old priority
            if (newPriority > changeNode.frequency) {
                // change the priority number and bubble the Patient up
                changeNode.frequency = newPriority;
                pQueue.bubbleUp(changeNodeIdx);
            }

            // if the new priority is lower than the old priority
            else if (newPriority < changeNode.frequency) {
                // change the priority number and bubble the Patient down
                changeNode.frequency = newPriority;
                pQueue.bubbleDown(changeNodeIdx);
            }
        }
    }

    boolean isEmpty() {
    /**
     * This method determines if the queue is empty by checking if its size
     * equals 0. It returns a boolean value representing the emptiness.
     * @param   none
     * @return  a boolean value representing if the heap is empty or not
     */
        return size == 0;
    }

    int size() {
    /**
     * This method returns the size of the queue by returning the size variable.
     * @param   none
     * @return  the size of the queue
     */
        return size;
    }

    void clear() {
    /**
     * This method completely clears the queue by creating a new (empty) heap
     * and assigning the pQueue variable to it. It also resets size to 0.
     * @param   none
     * @return  void
     */
        pQueue = new MaxHeap();
        size = 0;
    }

    @Override
    public String toString() {
    /**
     * This method converts the queue into a string representation by calling
     * the toString() method on pQueue, which is a heap object.
     * @param   none
     * @return  a String representation of the queue
     */
        return pQueue.toString();
    }

}
