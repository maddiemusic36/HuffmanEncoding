/*
 * NOTE: this version is only ONE Swing page. So you
 *       enter data, hit go, and then it will
 *       PRINT THE RESULTS TO THE CONSOLE. 
 **/

import java.awt.*;
import java.util.Map;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class GUI_DataEntry{
    /*
    *  This class is to create a GUI that will create a data
    *  entry page for the Huffman Encoding code. 
    *  It allows a user in input data and press a button that
    *  will cause the text given to be encoded using the
    *  Huffman Encoding algorithm. 
    */
    
    public static void makeGUI(){
        /**
         * This function creates a JFrame using Java Swing. 
         * On a button press, it reads in the text from the text 
         * input field of the GUI and then does the same as other
         * tests. 
         * 
         * Arguments:  None, accesses using txt.getText()
         * Returns:    Nothing; displays GUI instead
         */

        // declaring the frame...
        JFrame framey = new JFrame("Huffman Encoding!");
        framey.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framey.setSize(700, 492);
        
        // create theme color & set background color...
        int r = 2;
        int g = 0;
        int b = 143;
        Color themeColor = new Color(r,g,b);
        framey.getContentPane().setBackground(themeColor);

        // general setup for the overall frame...
        framey.setLayout(new FlowLayout());
        framey.setTitle("Huffman Encoding!");
        JPanel allThree = new JPanel();
        allThree.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        // declaring sections of the frame...
        JPanel uppy = new JPanel();
        JPanel texty = new JPanel();
        JPanel textyINNER = new JPanel();
        JPanel downy = new JPanel();

        // setting layouts of the frame divisions...
        allThree.setLayout(new GridLayout(3, 1));
        texty.setLayout(new GridLayout(2, 1));
        textyINNER.setLayout(new BorderLayout());  // to make text box fill whole width
        downy.setLayout(new GridLayout(3,1));

        // ** TITLE SECTION **  (uppy)
        JLabel titley = new JLabel("HUFFMAN ENCODING");
        titley.setFont(new Font("Elephant",Font.PLAIN,45));
        titley.setForeground(themeColor);
        uppy.setBorder(new EmptyBorder(10, 10, 50, 10));
        uppy.add(titley);

        // ** INPUT SECTION **  (texty)
        texty.add(new JLabel("  Please enter some text..."));
        JTextField txt = new JTextField(32);   // text input field

        textyINNER.add(txt);      // text field consumed more width
        texty.add(textyINNER);    // add the text to the general container
        texty.setBorder(new EmptyBorder(10, 10, 10, 10)); 

        // ** BUTTON SECTION ** (downy)
        JButton goButton = new JButton("Click to Encode!");
        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("\n\nAbout to convent Huffman Encode the following text input from user: " + txt.getText() + "\n");

                Map<String, String> huffResults = new HashMap<String, String>();
                index.implementHuffman_GUI(txt.getText().strip(), huffResults);
            }
        });
        downy.setBorder(new EmptyBorder(10, 10, 10, 10)); 
        downy.add(goButton);

        // add all sections to the frame's container...
        allThree.add(uppy);
        texty.add(textyINNER);
        allThree.add(texty);
        allThree.add(downy);

        framey.add(allThree);     // then add the container

        framey.setVisible(true);  // set to visible
    }
}