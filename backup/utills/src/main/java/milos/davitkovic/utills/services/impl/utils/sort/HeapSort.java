package milos.davitkovic.utills.services.impl.utils.sort;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Class HeapSort
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class HeapSort {
    private static int[] a;
    private static int n;
    private static int left;
    private static int right;
    private static int largest;

    public static void buildheap(int[] a) {
        n = a.length - 1;
        for (int i = n / 2; i >= 0; i--) {
            maxheap(a, i);
        }
    }

    public static void maxheap(int[] a, int i) {
        left = 2 * i;
        right = 2 * i + 1;

        if (left <= n && a[left] > a[i]) {
            largest = left;
        } else {
            largest = i;
        }

        if (right <= n && a[right] > a[largest]) {
            largest = right;
        }
        if (largest != i) {
            exchange(i, largest);
            maxheap(a, largest);
        }
    }

    public static void exchange(int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void sort(int[] myarray) {
        a = myarray;
        buildheap(a);
        for (int i = n; i > 0; i--) {
            exchange(0, i);
            n = n - 1;
            maxheap(a, 0);
        }
    }

    public static void main(String[] args) {
        int[] numbers = {55, 2, 93, 1, 23, 10, 66, 12, 7, 54, 3};
        System.out.println(Arrays.toString(numbers));
        sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }
}