package milos.davitkovic.utills.services.impl.utils.tree;

import org.springframework.stereotype.Service;

/**
 * Check if subtree
 * https://practice.geeksforgeeks.org/problems/check-if-subtree/1/?ref=self
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class CheckIfSubtree {

    class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
            left = null;
            right = null;
        }
    }

    class Tree {

        public boolean isSubtree(Node T, Node S) {
            final Node sameTNode = getSNode(T, S);
            if(sameTNode != null) {
                return isTreesEquals(sameTNode, S);
            } else {
                return false;
            }
        }

        private Node getSNode(final Node T, final Node S) {
            if (T == null) {
                return null;
            }

            if (T.data == S.data) {
                return T;
            } else {
                getSNode(T.left, S);
                getSNode(T.right, S);
            }

            return null;
        }

        private boolean isTreesEquals(final Node T, final Node S) {
            if(T == null && S == null) {
                return true;
            }

            if((T != null && S == null) || (T == null && S != null)) {
                return false;
            }

            return isTreesEquals(T.left, S.left) && isTreesEquals(T.right, S.right);
        }
    }
}
