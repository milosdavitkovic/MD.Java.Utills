package milos.davitkovic.utills.services.impl.utils.Array.list.linkedlist;

import org.springframework.stereotype.Service;

/**
 * Merge Sort for Linked List
 * https://practice.geeksforgeeks.org/problems/sort-a-linked-list/1
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class MergeSortForLinkedList {

    class Node {
        int data;
        Node next;

        Node(int key) {
            this.data = key;
            next = null;
        }
    }

    static Node mergeSort(final Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node mid = partition(head);
        return merge(mergeSort(head), mergeSort(mid));
    }

    static Node partition(final Node head) {
        Node fast = head.next;
        Node slow = head;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
        }
        Node second = slow.next;
        slow.next = null;
        return second;
    }

    static Node merge(Node head, Node second) {
        if (head == null) {
            return second;
        }
        if (second == null) {
            return head;
        }
        Node c = null;
        if (head.data <= second.data) {
            c = head;
        }
        else {
            c = second;
            second = head;
            head = c;
        }
        while (head.next != null) {
            if (head.next.data > second.data) {
                Node temp = head.next;
                head.next = second;
                second = temp;
            }
            head = head.next;
        }
        head.next = second;
        return c;
    }
}
