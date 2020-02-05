package milos.davitkovic.utills.services.impl.utils.tree;

import org.springframework.stereotype.Service;

/**
 * Sum Tree
 * https://practice.geeksforgeeks.org/problems/sum-tree/1/?ref=self
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class SumTree {

    class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    boolean isSumTree(Node node) {
        if(node == null) {
            return true;
        }

        if(node.left == null && node.right == null) {
            return true;
        }

        final int sum = getSumOfSubTree(node.left) + getSumOfSubTree(node.right);

        if(node.data == sum && isSumTree(node.left) && isSumTree(node.right)) {
            return true;
        } else {
            return false;
        }
    }

    private int getSumOfSubTree(final Node node) {
        if(node == null) {
            return 0;
        }

        if(node.left == null && node.right == null) {
            return node.data;
        }

        return node.data + getSumOfSubTree(node.left) + getSumOfSubTree(node.right);
    }
}
