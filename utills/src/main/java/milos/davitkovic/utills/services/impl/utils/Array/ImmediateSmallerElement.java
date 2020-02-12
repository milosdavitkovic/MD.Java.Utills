package milos.davitkovic.utills.services.impl.utils.Array;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Immediate Smaller rElement
 * https://practice.geeksforgeeks.org/problems/immediate-smaller-element/0/?track=sp-arrays-and-searching&batchId=152
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class ImmediateSmallerElement {

    public static void main(String[] args) throws IOException {
        // Using BufferedReader class to take input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // integer T denoting the number of test cases
        Integer t = Integer.parseInt(br.readLine());

        while (t --> 0) {
            // integer N, where N is the size of array.
            Integer N = Integer.parseInt(br.readLine());
            // N integers(elements of the array) separated with spaces
            String inputArray = br.readLine();
            // business logic
            printImmediateSmallerElement(inputArray);
        }
        br.close();
    }

    private static void printImmediateSmallerElement(final String inputArray) {
        List<Integer> array = new ArrayList<String>(Arrays.asList(inputArray.split(" ")))
                .stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        // Solution No1
        transformArray(array);
        // Solution No2
        //printArray(array);
    }

    private static void printArray(final List<Integer> array) {
        Integer value = null;
        Integer immediateNum = null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.size(); i++) {
            value = array.get(i);
            Integer inputValue = null;
            if (i == array.size() - 1) {
                inputValue = -1;
            } else {
                immediateNum = array.get(i + 1);
                if (immediateNum < value) {
                    inputValue = immediateNum;
                } else {
                    inputValue = -1;
                }
            }
            sb.append(inputValue + " ");
        }
        System.out.print(sb + "\n");
    }

    private static void transformArray(final List<Integer> array) {
        for(int i =  0; i < array.size() - 1; i++) {
            final Integer currentValue = array.get(i);
            final Integer nextValue = array.get(i + 1);
            if(currentValue > nextValue) {
                array.set(i, nextValue);
            } else {
                array.set(i, -1);
            }
        }
        array.set(array.size() - 1, -1);
        final List<String> stringList = array.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
        System.out.print(String.join(" ", stringList) + "\n");
    }
}
