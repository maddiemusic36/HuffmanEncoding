/**
 * This project ____
 * Authors: Madeline DeLeon, Billy Dolny, Savannah Rabasa, Julia Ryan
*/

/**
 * This is so slay if this works
 */

// for file reading
import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;


public class index<K, V> {

    public void organizeInput(String filename) throws FileNotFoundException {
    /**
     * This function reads in the text from the given file, counts the
     * frequency of each character, and creates a min priority queue of said
     * frequencies.
     * Args: filename, which is a string representing the name of a file
     * Returns: ______
     */
        File myFile = new File(filename);
        Scanner myReader;
        myReader = new Scanner(myFile);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            for (int i = 0; i < data.length(); i++){
                char letter = data.charAt(i);
                createNode(letter); // MUST CREATE A FUNCTION TO CHECK IF NODE IS ALREADY MADE
            }
        }
    }

    public int inputSize(String fileString) {
    /**
     * This function determines the size of the input by counting the
     * characters. Each character occupies 8 bits, including whitespace.
     * Args: fileString, which is the contents of a text file as a string
     * Returns: an integer representing the size of the input
     */
        return 0;
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
}
