package milos.davitkovic.utills.services.impl.utils.tree;

import org.springframework.stereotype.Service;

/**
 * K distance from root
 * https://practice.geeksforgeeks.org/problems/k-distance-from-root/1/?ref=self
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class KDistanceFromRoot {
    class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    // Recursive function to print right view of a binary tree.
    private void printKdistance(Node root, int k) {
        if(root == null) {
            return;
        }

        if(k == 0) {
            System.out.print(root.data + " ");
            return;
        }

        printKdistance(root.left, k-1);
        printKdistance(root.right, k-1);
    }
}
