package milos.davitkovic.utills.services.impl.utils.tree;

import org.springframework.stereotype.Service;

/**
 * Full binary tree
 * https://practice.geeksforgeeks.org/problems/full-binary-tree/1
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class FullBinaryTree {

    class Node {
        int data;
        Node left, right;

        Node(int item)
        {
            data = item;
            left = right = null;
        }
    }

    boolean isFullTree(Node node) {
        if(node == null) {
            return true;
        }
        if((node.left == null && node.right != null) ||
                (node.left != null && node.right == null)){
            return false;
        }
        return isFullTree(node.left) && isFullTree(node.right);
    }
}
