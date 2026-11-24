/*
 * I certify that the Java code I am submitting is all my own work.
 * None of the code is copied from any source or any person.
 * I did not use AI to generate the code for my program.
 * Signed: Trae Bracy
 * Date: 11/19/2026
*/

/*
 * @author Trae Bracy
 * Date: 11/19/2026
 * Class: CSS405
 * Assignment: AVL-Based Word Frequency Analyzer
 * File Name: Main.java
 * Description: Add a complete description of the file. This should be a
 *  paragraph that gives a full description of the problem.
 *
 *
 * References: include links to your references. At a minimum, you should always
 *  reference the class textbook and the course notes.
*/

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        WordFrequencyAnalyzer analyzer =
                new WordFrequencyAnalyzer();

        System.out.println("=== AVL Word Frequency Analyzer ===");

        // Ask the user for a text filename.
        System.out.print("Enter the filename: ");
        String filename = "src/" + input.nextLine();

        try {

            // Read the file and insert words into the AVL tree.
            analyzer.processFile(filename);

            // Display words in alphabetical order.
            System.out.println();
            analyzer.displayInOrder();

            // Display the tree structure using a queue.
            System.out.println();
            analyzer.displayPreOrder();

        } catch (FileNotFoundException e) {

            System.out.println("Error: File not found.");
            System.out.println("Please check the filename.");

        }

        input.close();
    }
}

