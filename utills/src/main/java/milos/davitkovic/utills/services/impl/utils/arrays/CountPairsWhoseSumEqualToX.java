package arrays;

import java.util.LinkedList;

/**
 * Count Pairs whose sum is equal to X
 * https://practice.geeksforgeeks.org/problems/count-pairs-whose-sum-is-equal-to-x/1
 *
 * @author milos.davitkovic@gmail.com
 */
public class CountPairsWhoseSumEqualToX {

    public static int countPairs(LinkedList<Integer> head1, LinkedList<Integer> head2, int x) {

        if (head1.isEmpty() || head2.isEmpty()) {
            throw new IllegalArgumentException("LinkedLists cannot be empty!");
        }
        return getCountP(head1, head2, x);
        //        return getCountPairs(head1, head2, x);
    }

    private static int getCountPairs(LinkedList<Integer> head1, LinkedList<Integer> head2, int x) {
        int distinctElements = 0;
        for (int i = 0; i < head1.size(); i++) {
            for (int j = 0; j < head2.size(); j++) {
                final int elementsSum = head1.get(i) + head2.get(j);
                if (elementsSum == x) {
                    distinctElements++;
                }
            }
        }
        return distinctElements;
    }

    private static int getCountP(final LinkedList<Integer> head1, final LinkedList<Integer> head2, final int x) {
        int distinctElements = 0;
        for(Integer h1 : head1) {
            for(Integer h2 : head2) {
                final int elementsSum = h1 + h2;
                if (elementsSum == x) {
                    distinctElements++;
                }
            }
        }
        return distinctElements;
    }
}

// Itereting throw 2 LinkedList and check Ith element from 1st list with all elements from another, if there is a match increase sum
