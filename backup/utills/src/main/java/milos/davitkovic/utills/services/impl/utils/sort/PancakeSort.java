package milos.davitkovic.utills.services.impl.utils.sort;

import org.springframework.stereotype.Service;

import java.util.*;
import java.lang.*;
import java.io.*;


/**
 * Class PancakeSort
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class PancakeSort {

    public static void main(String[] args) {

        int[] arr = {10, 9, 8, 7, 6, 15, 14, 13, 12, 11, 5, 4, 3, 2, 1};

        System.out.println("Unsorted: " + Arrays.toString(arr));
        pancakeSort(arr);
        System.out.println("Sorted: " + Arrays.toString(arr));
    }

    public static void pancakeSort(int[] A) {
        int max = 0;
        int j, index = 0;

        for (int i = 0; i < A.length; i++) {
            max = A[0];
            index = 0;
            for (j = 0; j < A.length - i; j++) {
                if (A[j] > max) {
                    max = A[j];
                    index = j;
                }
            }
            flip(A, index, A.length - 1 - i);
        }
    }

    public static void flip(int[] A, int l, int r) {
        while (l <= r) {
            int temp = A[l];
            A[l] = A[r];
            A[r] = temp;
            l++;
            r--;
        }
    }
}