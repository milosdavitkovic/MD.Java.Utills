package milos.davitkovic.utills.services.impl.palindrom;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class PalindromicNumbers
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class PalindromicNumbers {

    public void printPalindromicNumbers(final int N) {
        if(N < 1 || N > 15) {
            throw new IllegalArgumentException("Input number cannot be bigger then 15, and smaller then 1");
        }
        int min = getMinNumber(N);
        int max = getMaxNumber(N);
        final List<Integer> palindromicNumbers = getPalindromicNumbersInRange(min, max);
        palindromicNumbers.forEach(num -> System.out.println(num));
    }

    private int getMinNumber(final int n) {
        final StringBuilder min = new StringBuilder();
        min.append("1");
        for(int i =  0; i < n; i++) {
            min.append("0");
        }

        return Integer.valueOf(min.toString());
    }

    private int getMaxNumber(final int n) {
        final StringBuilder max = new StringBuilder();
        max.append("9");
        for(int i =  0; i < n; i++) {
            max.append("9");
        }

        return Integer.valueOf(max.toString());
    }

    /**
     * Get Palindromic numbers (5445, 323, 1234321..) between minValue and maxValue.
     * @param minValue is a minimum Value of the range
     * @param maxValue is a maximum Value of the range
     * @return List of Palindromic numbers
     */
    private List<Integer> getPalindromicNumbersInRange(final int minValue, final int maxValue) {
        final List<Integer> palindromicNumbers = new ArrayList<>();

        for (Integer i =  minValue; i < maxValue; i++) {

            final List<Integer> numberDigits = new ArrayList<String>(Arrays.asList(i.toString().split("")))
                    .stream()
                    .map(str -> Integer.parseInt(str))
                    .collect(Collectors.toList());

            if(isPalindromic(numberDigits)) {
                palindromicNumbers.add(i);
            }
        }
        return palindromicNumbers;
    }

    /**
     * Check if numberDigits array is Palindrom 5445, 323, 1234321...
     * Recursion can be faster than iteration on multi-core systems but use more memory.
     * User more Memory but less CPU power
     * @param numberDigits List of Integers which represents digits of number
     * @return true if the number is Palindrom
     */
    private boolean isPalindromic(final List<Integer> numberDigits) {
        if (numberDigits.size() == 1) {
            return true;
        }
        if (numberDigits.size() == 2 && numberDigits.get(0).equals(numberDigits.get(1))) {
            return true;
        }
        if (numberDigits.get(0).equals(numberDigits.get(numberDigits.size() - 1))) {
            numberDigits.remove(0);
            numberDigits.remove(numberDigits.size() - 1);
            return isPalindromic(numberDigits);
        }
        return false;
    }
}