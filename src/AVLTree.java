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
 * File Name: AVLTree.java
 * Description: Add a complete description of the file. This should be a
 *  paragraph that gives a full description of the problem.
 *
 *
 * References: include links to your references. At a minimum, you should always
 *  reference the class textbook and the course notes.
*/

import java.util.LinkedList;
import java.util.Stack;

public class AVLTree {

    // references the root node of the AVL tree.
    private Node root;

    /*
     * Method Name: AVLTree()
     * Description: Initializes an empty AVL tree by setting the root node to null.
     */
    // sets the root node to null.
    public AVLTree() {
        root = null;
    }

    /*
     * Method Name: height(Node node)
     * Description: Retrieves the height of a given node and returns zero if the node is null.
     *
     * @param The node that's height is being checked.
     * @return The height of the node.
     *
     */
    // part of the AVL tree
    // returns the stored height of a node that exists.
    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    // Determines the balance factor.
    // An AVL node is balanced when its balance factor is -1, 0, or +1.

    /*
     * Method Name: getBalance(Node node)
     * Description: Calculates the difference between the heights of both the left and right subtrees.
     *
     * @param The node being checked for balance.
     * @return The balance factor of the node.
     */
    // part of the AVL tree
    // uses the height method to determine the balance factor
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    /*
     * Method Name: rightRotate(Node node)
     * Description: Performs a right rotation to rebalance the left-heavy subtree and updates heights.
     *
     * @param The root of the subtree that's being rotated.
     * @return The new root after the rotation.
     */
    // part of the AVL tree
    private Node rightRotate(Node node) {

        // reference that points to the nodes left child
        Node leftChild = node.left;

        // reference that makes the node's left child's sub-right child the middle node
        Node middle = leftChild.right;

        // make's the node the right child of the left child
        leftChild.right = node;

        // make's the middle node the child of node
        node.left = middle;

        node.height = 1 + Math.max(
                height(node.left), height(node.right)
        );

        leftChild.height = 1 + Math.max(
                height(leftChild.left), height(leftChild.right)
        );

        return leftChild;

    }

    /*
     * Method Name: LeftRotate(Node node)
     * Description: Performs a left rotation to rebalance the right-heavy subtree and updates heights.
     *
     * @param The root of the subtree that's being rotated.
     * @return The new root after the rotation.
     */
    // part of the AVL tree
    private Node leftRotate(Node node) {

        // reference that points to the nodes right child
        Node rightChild = node.right;

        // reference that makes the node's right child's sub-left child the middle node
        Node middle = rightChild.left;

        // make's the node the left child of the right child
        rightChild.left = node;

        // make's the middle node the child of node
        node.right = middle;

        node.height = 1 + Math.max(
                height(node.left), height(node.right)
        );

        rightChild.height = 1 + Math.max(
                height(rightChild.left), height(rightChild.right)
        );

        return rightChild;
    }


    /*
     * Method Name: Insert(String key)
     * Description: Calls the recursive insertion method
     *
     * @param describe the parameters for the method.
     * @return describe what is returned from the method
     */
    // part of the AVL tree
    public void insert(String key) {

        root = insert(root, key);

    }

    // part of the AVL tree
    private Node insert(Node node, String key) {

        if (node == null) {
            return new Node(key);
        }

        int comparison = key.compareTo(node.key);

        if (comparison < 0) {
            node.left = insert(node.left, key);
        }

        else if (comparison > 0) {
            node.right = insert(node.right, key);
        }

        else {

            // If the node was deleted, reactivate it.
            if (node.deleted) {
                node.deleted = false;
                node.count = 1;
            } else {
                node.count++;
            }

            return node;
        }

        node.height = 1 + Math.max(
                height(node.left), height(node.right)
        );

        int balance = getBalance(node);

        // Left-Left
        if (balance > 1 && key.compareTo(node.left.key) < 0) {
            return rightRotate(node);
        }

        // Left-Right
        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right-Right
        if (balance < -1 && key.compareTo(node.right.key) > 0) {
            return leftRotate(node);
        }

        // Right-Left
        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /*
     * Method Name: search(String key)
     * Description: Searches the tree for a word.
     *
     * @param describe The word being searched for.
     * @return The word's frequency or zero if not found.
     */
    public int search(String key) {

        Node current = root;

        while (current != null) {
            int comparison = key.compareTo(current.key);

            if (comparison == 0) {

                // Deleted nodes are treated as absent.
                if (current.deleted) {
                    return 0;
                }

                return current.count;

            } else if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return 0;
    }

    /*
     * Method Name: delete(String key)
     * Description: Searches for a word and marks its node as deleted without removing it from the AVL tree.
     *
     * @param The word that's being deleted.
     *
     */
    public void delete(String key) {

        Node current = root;

        while (current != null) {
            int comparison = key.compareTo(current.key);

            if (comparison == 0) {
                current.deleted = true;
                return;
            } else if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
    }

    /*
     * Method Name: inOrder()
     * Description: Starts an in-order traversal from the root to display words in alphabetical order.
     *
     * @param describe the parameters for the method.
     * @return describe what is returned from the method
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {

        if (node == null) {
            return;
        }

        inOrder(node.left);

        // Only display nodes that have not been deleted.
        if (!node.deleted) {
            System.out.println(node.key + ": " + node.count);
        }

        inOrder(node.right);
    }


    public void preOrder() {

        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {

            Node current = stack.pop();

            if (!current.deleted) {
                System.out.println(current.key + ": " + current.count);
            }

            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

}
