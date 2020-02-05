package milos.davitkovic.utills.services.impl.utils.tree;

import org.springframework.stereotype.Service;

/**
 * Level of a Node in Binary Tree
 * https://practice.geeksforgeeks.org/problems/level-of-a-node-in-binary-tree/1/?ref=self
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class LevelOfNodeInBinaryTree {

    class Node {
        int data;
        Node left, right;

        Node(int key) {
            data = key;
            left = right = null;
        }
    }

    public int getLevel(Node root, int key) {
        if (root == null) {
            return 0;
        }

        if (root.data == key) {
            return 1;
        }
        int left = getLevel(root.left, key);
        if (left > 0) {
            left++;
        }
        int right = getLevel(root.right, key);
        if (right > 0) {
            right++;
        }

        return left + right;
    }
}
