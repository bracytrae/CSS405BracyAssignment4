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
 * File Name: Node.java
 * Description: Add a complete description of the file. This should be a
 *  paragraph that gives a full description of the problem.
 *
 *
 * References: include links to your references. At a minimum, you should always
 *  reference the class textbook and the course notes.
*/

public class Node {

    // stores the word
    String key;

    // stores the occurrences of the word
    int count;

    // stores the height of the subtree for balancing
    int height;


    boolean deleted;

    // reference to the left child
    Node left;

    // reference to the right child
    Node right;


    // the Node constructor that initializes the Node's values.
    public Node(String key) {


        this.key = key;

        this.count = 1;

        this.height = 1;

        this.deleted = false;

        this.left = null;

        this.right = null;

    }
}


