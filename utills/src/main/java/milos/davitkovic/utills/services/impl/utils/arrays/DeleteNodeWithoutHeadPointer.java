package arrays;

/**
 * Delete without head pointer
 * https://practice.geeksforgeeks.org/problems/delete-without-head-pointer/1
 *
 * @author milos.davitkovic@gmail.com
 */
public class DeleteNodeWithoutHeadPointer {

    class Node
    {
        int data ;
        Node next;
        Node(int d)
        {
            data = d;
            next = null;
        }
    }

    void deleteNode(Node node)
    {
        Node nextNode = node.next;
        node.data = nextNode.data;
        node.next= nextNode.next;
    }

}
