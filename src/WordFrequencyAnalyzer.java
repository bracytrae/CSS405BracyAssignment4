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
 * File Name: WordFrequencyAnalyzer.java
 * Description: Add a complete description of the file. This should be a
 *  paragraph that gives a full description of the problem.
 *
 *
 * References: include links to your references. At a minimum, you should always
 *  reference the class textbook and the course notes.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordFrequencyAnalyzer {

    private AVLTree tree;

    public WordFrequencyAnalyzer() {
        tree = new AVLTree();
    }

    // Reads a text file and inserts words into the AVL tree.
    public void processFile(String filename)
            throws FileNotFoundException {

        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {

            String word = scanner.next();

            // Convert the word to lowercase.
            word = word.toLowerCase();

            // Remove punctuation and other non-letter characters.
            word = word.replaceAll("[^a-z]", "");

            // Insert the word if it is not empty.
            if (!word.isEmpty()) {
                tree.insert(word);
            }
        }

        scanner.close();
    }

    public void displayInOrder() {
        System.out.println("=== In-Order Traversal ===");
        tree.inOrder();
    }

    public void displayPreOrder() {
        System.out.println("=== Pre-Order Traversal ===");
        tree.preOrder();
    }


}
