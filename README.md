# HuffmanEncoding
345 Group Project
Authors: Madeline DeLeon, Billy Dolny, Savannah Rabasa, Julia Ryan

Our code for this implementation consists of four main files. 
    PriorityQueue.java contains our priority queue ADT. This class uses a min
heap to store the values and keep the lowest frequency nodes at the front of
the queue. It also has methods to enqueue and dequeue items to/from the queue,
as well as basic get/set methods to retrieve/change information about the queue.
    Huffman.java includes code for our Huffman Tree. It contains a Huffman
class, a Node class, and an Encoding List class. The Node and Encoding classes
are used to hold information that the tree needs. There are methods in the
Huffman class to build a tree from a priority queue, create an encoding list,
encode a string using the encoding list, and decode a string using the tree and
a the encoding.
    index.java contains methods that we felt didn't need to be in any specific
class. There are methods to read a file into a priority queue, and determine the
input and output size of the Huffman compression. This file is also where we
run testcases for our implementation. The test() method calls functions from all
of the previous files to print out important information regarding our program
to the console.
    Finally, GUI_DataEntry.java generates our Graphical User Interface. The GUI
consists of a title, a textbox, and a button. When text is entered into the box
and the button is pressed, the text is encoded using our algorithm, and relevant
information is printed to the console. You can enter text and encode it as many
times as you want before closing the window.

Aside from these files, there are two folders holding testcase files that
we used for testing the efficiency of our algorithm. The Repetition Testing
folder contains 95 .txt files. Each file is the same exact length, but the
contents of the file become increasingly repetitive as the testcase number
increases. The Size Testing folder contains 11 .txt files. Each file has the
same amount of repetition, but the length of each file increases as the number
increases. These series of tests helped us determine when our algorithm is most
useful versus when it's actually detrimental to use.

For testing purposes, all you need to do is run index.java. Its main method
is configured to run three basic testcases (test-2.txt, test-1.txt, and
test0.txt) before generating the GUI. Once the GUI pops up, you can enter any
text in the textbox, then click the "Click to Encode!" button. The program will
then run using your input as if it were a testcase by printing out any relevant
information to the console.