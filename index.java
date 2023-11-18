// for file reading
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class index {
/**
 * This class is used for testing our implementation of Huffman Encoding. It
 * contains a handful of functions our implementation needs as well as a test()
 * and a main() function to run testcases.
 */

    // index instance variable
    static String fileString = "";

    public static PriorityQueue organizeInput(String filename, String content) throws FileNotFoundException {
    /**
     * This function reads in the text from the given file, counts the
     * frequency of each character, and creates a priority queue of said
     * characters and frequencies.
     * Args: filename, which is a string representing the name of a file
     * Returns: a PriorityQueue object which holds every character and their
     *          frequencies from the given file
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
            content += data;
            // loop through every character in the current line
            for (int i = 0; i < data.length(); i++){
                char letter = data.charAt(i);
                int found = pQueue.find(letter);
                // check if the character already exists in the queue
                if (found == -1)
                    pQueue.enqueue(new Node(letter, 1));
                else
                    pQueue.incrementPriority(found);
            }
        }

        // close the file
        myReader.close();
        fileString = content;
        return pQueue;
    }

    public static int inputSize(String fileString) {
    /**
     * This function determines the size of the input by counting the
     * characters. Each character occupies 8 bits, including whitespace.
     * Args: fileString, which is the contents of a text file as a string
     * Returns: an integer representing the size of the input
     */
        return fileString.length() * 8;
    }

    public static int outputSize(EncodingList eList) {
    /**
     * This function determines the size of the output by adding together the
     * number of characters, the frequency of each character, and the frequency
     * of each character times the length of that character's encoding
     * Args: eList, which is an EncodingList object
     * Returns: an integer representing the size of the output
     */
        int character = eList.pqSize * 8;
        int frequency = 0;
        int size = 0;
        for (int i=0; i<eList.pqSize; i++) {
            frequency += eList.list[i].frequency;
            size += (eList.list[i].frequency * eList.list[i].encoding.length());
        }
        return character + frequency + size;
    }

    public static void test(String filename) {
    /**
     * This method is used for testing our implementation. It runs through the
     * entire process and displays any relevant information.
     * Args: filename, which is the name of the file containing the input data
     * Returns: None
     */
        try {
            fileString = "";

            // read the input file and convert the data into a priority queue
            PriorityQueue pQueue = organizeInput(filename, fileString);
            System.out.println("Input file: " + fileString);
            System.out.println("Priority Queue: " + pQueue);

            // determine the size of the input
            int inputSize = inputSize(fileString);
            System.out.println("Input Size: " + inputSize);

            // create the huffman tree
            Huffman tree = new Huffman();
            tree.buildTree(pQueue);
            System.out.println("Huffman Tree: " + tree.printTree(tree.root));

            // create the encoding
            tree.createEncodingList();
            System.out.println("Encoding List: " + tree.encodingList.toString());
            String encoding = tree.encode(fileString);
            System.out.println("Encoded Sequence: " + encoding);

            // determine the size of the encoding
            int outputSize = outputSize(tree.encodingList);
            System.out.println("Output Size: " + outputSize);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void implementHuffman_GUI(String txtInput, Map<String, String> setOutputs) {
        /**
         * This method is called to test input that is given by the
         * user of the GUI. It creates a Huffman tree and "runs through the
         * entire process and displays any relevant information" in a very
         * similar manner to test() function. 
         * 
         * Parameters:   txtInput(String) - the string that is given by the user (FROM GUI)
         *               setOutputs(Map<String,String>) - the dictionary of outputs to track
         * Returns:      Nothing, only prints to the console.
         */
        try {
            fileString = "";

            // get the text input and create a priority queue
            PriorityQueue pQueue = organizeInputFromGUI(txtInput, fileString);
            // txtInput is txt.getText() (AKA the current value typed in the text field)
            System.out.println("Priority Queue: " + pQueue);

            // input size:
            int inputSize = fileString.length() * 8;
            setOutputs.put("input size", Integer.toString(inputSize));
            System.out.println("Input Size: " + inputSize);

            // create the huffman tree
            Huffman tree = new Huffman();
            tree.buildTree(pQueue);
            setOutputs.put("huff tree", tree.printTree(tree.root));
            System.out.println("Huffman Tree: " + tree.printTree(tree.root));

            // create the encoding
            tree.createEncodingList();
            System.out.println("Encoding List: " + tree.encodingList.toString());
            setOutputs.put("encoding list", tree.encodingList.toString());

            String encoding = tree.encode(fileString);
            setOutputs.put("encoding", encoding);
            System.out.println("Encoded Sequence: " + encoding);

            // determine the size of the encoding
            int outputSize = outputSize(tree.encodingList);
            System.out.println("Output Size: " + outputSize);
            setOutputs.put("out size", Integer.toString(outputSize));
        }
        catch (Exception e) {
            System.out.println("\n\nERROR.\n\n");
            e.printStackTrace();
        }
    }

    public static PriorityQueue organizeInputFromGUI(String txtInput, String content) throws FileNotFoundException {
        /**
         * This function is an imitation of organizeInput() but for the
         * input of the GUI, which is just a string of text rather than
         * a text file. 
         * 
         * Args: filename, which is a string representing the name of a file
         * Returns: a PriorityQueue object which holds every character and their
         *          frequencies from the given file
         */
            // priority queue to hold Nodes
            PriorityQueue pQueue = new PriorityQueue();
            content += txtInput;

            // loop through every character in the current line
            for (int i = 0; i < txtInput.length(); i++){
                char letter = txtInput.charAt(i);
                int found = pQueue.find(letter);
                // check if the character already exists in the queue
                if (found == -1)
                    pQueue.enqueue(new Node(letter, 1));
                else
                    pQueue.incrementPriority(found);
            }
            fileString = content;
            return pQueue;
    }
    
    public static void main(String[] args) {
        String[] testList = {"-2", "-1", "0"};
        for (int i=0; i<testList.length; i++) {
            String curTest = "test" + testList[i] + ".txt";
            test(curTest);
            System.out.println();
        }
        GUI_DataEntry.makeGUI();
    }
}
