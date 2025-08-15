package milos.davitkovic.utills.services.impl.utils.tree;

import org.springframework.stereotype.Service;

/**
 * Ancestors in Binary Tree
 * https://practice.geeksforgeeks.org/problems/ancestors-in-binary-tree/1/?ref=self
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class AncestorsInBinaryTree {

    class Node {
        int data;
        Node left, right;

        Node(int key) {
            data = key;
            left = right = null;
        }
    }

    public boolean printAncestors(Node node, int x) {
        if (node == null) {
            return false;
        }

        if (node.data == x) {
            return true;
        }

        if (printAncestors(node.left, x) || printAncestors(node.right, x)) {
            System.out.print(node.data + " ");
            return true;
        }
        return false;
    }
}
