package milos.davitkovic.utills.services.impl.arrays;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Occurences of 2 as a digit
 * https://practice.geeksforgeeks.org/problems/occurences-of-2-as-a-digit/1
 *
 * @author milos.davitkovic@gmail.com
 *
 */
@Service
public class OccurencesOf2AsDigit {

    private static final String SPLITTER = "";
    private static final String TWO = "2";
    private static final long RANGE_START = 0;

    public static long count2s(long n) {
        long count2s = 0;
        for(long i =  RANGE_START; i <= n; i++) {
            count2s += count2sInNumber(i);
        }
        return count2s;
    }

    public static long count2sinRangeAtDigit(long n, long d) {
        long count2s = 0;
        for(long i =  d; i <= n; i++) {
            count2s += count2sInNumber(i);
        }
        return count2s;
    }

    private static long count2sInNumber(final long n) {
        final String number = String.valueOf(n);
        final List<String> numberList = new ArrayList(Arrays.asList(number.split(SPLITTER)));
        return numberList.stream()
                .filter(num -> num.equals(TWO))
                .count();
    }
}
