package milos.davitkovic.utills.services.impl.utils;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * author milos.davitkovic@gmail.com
 */
@Service
public class JavaMDUtils {

    public List<List<Integer>> get2DimensionalIntegerList(int[][] grid) {
        final List<List<Integer>> gridList = new ArrayList<>();
        for (int[] array : grid) {
            List<Integer> elements = new ArrayList<>();
            for (int a : array) {
                elements.add(a);
            }
            gridList.add(elements);
        }
        return gridList;
    }

    public List<List<Integer>> get2DimensionalIntegerList(char[][] grid) {
        final List<List<Integer>> gridList = new ArrayList<>();
        for (char[] array : grid) {
            List<Integer> elements = new ArrayList<>();
            for (char c : array) {
                elements.add(Integer.parseInt(String.valueOf(c)));
            }
            gridList.add(elements);
        }
        System.out.println("gridList: " + gridList);
        return gridList;
    }

    public void main(String[] args) throws IOException {
        // Using BufferedReader class to take input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // taking input of number of testcase
        Integer t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            Integer N = Integer.parseInt(br.readLine());
            final String inputArray = br.readLine();
            final List<String> array = new ArrayList(Arrays.asList(inputArray.split(" ")));
            // business logic
        }
        br.close();
    }

    public List<String> splitString(final String s, final String splitter) {
        // splitter = " ";
        // splitter = "";
        return new ArrayList(Arrays.asList(s.split(splitter)));
    }

    public long countDigitInNumber(final int digit, final long number) {
        final String numberStr = String.valueOf(number);
        final List<String> numberList = new ArrayList(Arrays.asList(numberStr.split("")));
        return numberList.stream()
                .filter(num -> num.equals(digit))
                .count();
    }

    public Integer sumCharacters(final List<Character> input) {
        return input.stream()
                .map(character -> Integer.parseInt(String.valueOf(character)))
                .reduce(0, Integer::sum);
    }

    public Integer sumIntegers(final List<Integer> input) {
        return input.stream()
                .collect(Collectors.summingInt(Integer::intValue));
    }

    public Integer sumIntegers(final Map<Object, Integer> input) {
        return input.values()
                .stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }

    public Integer numbersSumFromString(final String input) {
        if (input.isEmpty()) {
            return 0;
        }

        final List<String> inputList = new ArrayList(Arrays.asList(input.split(JavaRegexMD.REGEX_GET_ONLY_NUMBERS)));
        return inputList.stream()
                .map(s -> s.replaceAll(JavaRegexMD.REGEX_REMOVE_ALL_WHITESPACES_AND_NONVISIBLE_CHARACTERS, ""))
                .filter((s) -> s.matches(JavaRegexMD.REGEX_GET_INTEGERS))
                .mapToInt(Integer::valueOf)
                .sum();
    }

    public Integer maxNumberFromString(final String input) {
        if (input.isEmpty()) {
            return null;
        }

        final List<String> inputList = new ArrayList<String>(Arrays.asList(input.split(JavaRegexMD.REGEX_GET_ONLY_NUMBERS)));
        final OptionalInt sum = inputList.stream()
                .map(s -> s.replaceAll(JavaRegexMD.REGEX_REMOVE_ALL_WHITESPACES_AND_NONVISIBLE_CHARACTERS, ""))
                .filter((s) -> s.matches(JavaRegexMD.REGEX_GET_INTEGERS))
                .mapToInt(Integer::valueOf)
                .max();

        if (sum.isPresent()) {
            return sum.getAsInt();
        } else {
            return null;
        }
    }

    private String findUncommonCharacters(final String input1, final String input2) {
        if (input1.isEmpty() || input2.isEmpty()) {
            return "";
        }
        final List<String> input1List = new ArrayList<String>(Arrays.asList(input1.split("")));
        final List<String> input2List = new ArrayList<String>(Arrays.asList(input2.split("")));

        final Set<String> results = new TreeSet<String>();
        for (String s1 : input1List) {
            if (!input2List.contains(s1)) {
                results.add(s1);
            }
        }

        for (String s2 : input2List) {
            if (!input1List.contains(s2)) {
                results.add(s2);
            }
        }

        return String.join("", results);
    }

    public static long power(int x, int y) {
        long result = x;

        for (int i = 1; i < y; i++) {
            result = result * x;
        }

        return result;
    }
}
