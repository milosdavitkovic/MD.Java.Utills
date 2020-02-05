package milos.davitkovic.utills.services.impl.palindrom;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Palindromic Array
 * https://practice.geeksforgeeks.org/problems/palindromic-array/0
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class PalindromicArray {

    public static void main(String[] args) throws IOException {
        // Using BufferedReader class to take input
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // taking input of number of testcase
        Integer t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            Integer N = Integer.valueOf(br.readLine());
            String array = br.readLine();
            operationsNumberToCreatPalindromicArray(array);
        }
        br.close();
    }

    private static void operationsNumberToCreatPalindromicArray(final String array) {
        List<Integer> arrayList = new ArrayList<String>(Arrays.asList(array.split(" ")))
                .stream()
                .map(str -> Integer.parseInt(str))
                .collect(Collectors.toList());

        int numberOfOperations = 0;
        while (!isPalindromicArrayLoop(arrayList)) {
            doOperation(arrayList);
            numberOfOperations++;
        }
        System.out.println(numberOfOperations);
    }

    /**
     *
     * iteration is faster than recursion, use more CPU power but less memory
     * @param arrayList
     * @return
     */
    private static boolean isPalindromicArrayLoop(final List<Integer> arrayList) {
        if (arrayList.size() == 1) {
            return true;
        }
        if (arrayList.size() == 2 && arrayList.get(0).equals(arrayList.get(1))) {
            return true;
        }
        for (int i = 0; i < arrayList.size() / 2; i++) {
            if (!arrayList.get(i).equals(arrayList.get(arrayList.size() - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if array is Palindrom 5445, 323, 1234321...
     * Recursion can be faster than iteration on multi-core systems but use more memory.
     * User more Memory but less CPU power
     * @param arrayList
     * @return
     */
    private static boolean isPalindromicArrayRecursive(final List<Integer> arrayList) {
        if (arrayList.size() == 1) {
            return true;
        }
        if (arrayList.size() == 2 && arrayList.get(0).equals(arrayList.get(1))) {
            return true;
        }
        if (arrayList.get(0).equals(arrayList.get(arrayList.size() - 1))) {
            arrayList.remove(0);
            arrayList.remove(arrayList.size() - 1);
            return isPalindromicArrayRecursive(arrayList);
        }
        return false;
    }

    /**
     * Sum adjacent elements
     * The only allowed operation is that you can merge two adjacent elements in the array and replace them with their sum.
     * Java is always pass-by-value, by value is the reference number which is point out on memory location of object.
     * We can use the the passed value and change the object on that memory location, because of the this function is void.
     * @param arrayList
     */
    private static void doOperation(final List<Integer> arrayList) {
        if (arrayList.size() == 1) {
            return;
        }

        if (arrayList.size() == 2 || arrayList.get(0) <= arrayList.get(arrayList.size() - 1)) {
            sumElements(arrayList, 0, 1);
        }

        if (arrayList.get(0) > arrayList.get(arrayList.size() - 1)) {
            sumElements(arrayList, arrayList.size() - 1, arrayList.size() - 2);
        }

        return;
    }

    /**
     * Do a Sum of specified elements of array.
     * Pass arrayList value of memory location, that we can change a object on that memory location
     * @param arrayList
     * @param i
     * @param j
     */
    private static void sumElements(final List<Integer> arrayList, int i, int j) {
        final Integer sum = arrayList.get(i) + arrayList.get(j);
        arrayList.add(i, sum);
        arrayList.remove(j);
    }
}
