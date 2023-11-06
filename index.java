/**
 * This project ____
 * Authors: Madeline DeLeon, Billy Dolny, Savannah Rabasa, Julia Ryan
*/

// for file reading
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class index<K, V> {

    public PriorityQueue organizeInput(String filename) throws FileNotFoundException {
    /**
     * This function reads in the text from the given file, counts the
     * frequency of each character, and creates a priority queue of said
     * frequencies.
     * Args: filename, which is a string representing the name of a file
     * Returns: a PriorityQueue object which holds every character and their
     *              frequencies from the given file
     */
        // priority queue to hold Nodes
        PriorityQueue pQueue = new PriorityQueue();

        // read in the file
        File myFile = new File(filename);
        Scanner myReader;
        myReader = new Scanner(myFile);
        
        // loop through every line in the file
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            // loop through every character in the current line
            for (int i = 0; i < data.length(); i++){
                char letter = data.charAt(i);
                Node maybe = pQueue.find(letter);
                // check if the letter already exists in the queue
                if (maybe == null)
                    pQueue.enqueue(new Node(letter, 0));
                else { maybe.frequency++;}
            }
        }

        myReader.close();

        return pQueue;

    }

    public int inputSize(String fileString) {
    /**
     * This function determines the size of the input by counting the
     * characters. Each character occupies 8 bits, including whitespace.
     * Args: fileString, which is the contents of a text file as a string
     * Returns: an integer representing the size of the input
     */
        return fileString.length() * 8;
    }

    public void createTree() {
        //////////
    }

    public int outputSize(Huffman<K,V> tree, String code) {
    /**
     * This function determines the size of the output by finding the size of
     * the Huffman tree and the encoded sequence
     * Args:
     *      tree, which is a Huffman object
     *      code, which is a string representing the encoded input data
     */
        return 0;
    }

    public String decode(Huffman<K,V> tree, String code) {
    /**
     * This function decodes the Huffman tree using the given code. It creates
     * a string representing the final decoded value, which should match the
     * input data
     * Args: 
     *      tree, which is a Huffman object
     *      code, which is a string representing the encoded input data
     */
        return "";
    }

    public static void test() {
        // read the input file
        // determine the size of the input
        // convert the data of the input into a priority queue
        // create the huffman tree
        // create the encoding
        // determine the size of the encoding and the tree
    }
}
