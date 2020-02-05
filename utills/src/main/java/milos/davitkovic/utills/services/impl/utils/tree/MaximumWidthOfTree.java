package milos.davitkovic.utills.services.impl.utils.tree;

import org.springframework.stereotype.Service;

/**
 * Maximum Width of Tree
 * https://practice.geeksforgeeks.org/problems/maximum-width-of-tree/1/?ref=self
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class MaximumWidthOfTree {

    class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    private int treeHeight = 0;
    private int width = 0;

    // /* Function to get the maximum width of a binary tree*/
    int getMaxWidth(Node root) {
        if (root == null) {
            return 0;
        }
        int height = height(root);
        int maxWidth = 0;
        for (int level = 1; level <= height; level++) {
            int count = 0;
            int d = getNodeNumber(root, level, count);
            maxWidth = Math.max(d, maxWidth);
        }
        return maxWidth;
    }

    int height(Node root) {
        if (root == null) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);

        if (left > right) {
            return left + 1;
        } else {
            return right + 1;
        }
    }

    int getNodeNumber(Node root, int level, int count) {
        if (root == null) {
            return count;
        }
        if (level == 1) {
            count = count + 1;
            return count;
        } else {
            int countLeft = getNodeNumber(root.left, level - 1, count);
            int countRight = getNodeNumber(root.right, level - 1, countLeft);
            return countRight;
        }
    }
}
