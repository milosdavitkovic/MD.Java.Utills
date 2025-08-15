package milos.davitkovic.utills.services.impl.string;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

/**
 * Extract Maximum
 * https://practice.geeksforgeeks.org/problems/extract-maximum/0/?ref=self
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class ExtractMaximum {

    private static final String REGEX_GET_ONLY_NUMBERS = "[^0-9]";
    private static final String REGEX_REMOVE_ALL_WHITESPACES_AND_NONVISIBLE_CHARACTERS = "\\s+";
    private static final String REGEX_GET_INTEGERS = "\\d+";

    public static void main (String[] args) throws IOException {
        // Using BufferedReader class to take input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // taking input of number of testcase
        Integer t = Integer.parseInt(br.readLine());

        while (t -- > 0) {
            final String input = br.readLine();
            maxNumberFromString(input);
        }
        br.close();
    }

    private static void maxNumberFromString(final String input) {
        if(input.isEmpty()) {
            return;
        }
        final List<String> inputList = new ArrayList<String>(Arrays.asList(input.split(REGEX_GET_ONLY_NUMBERS)));
        final OptionalInt sum = inputList.stream()
                .map(s -> s.replaceAll(REGEX_REMOVE_ALL_WHITESPACES_AND_NONVISIBLE_CHARACTERS, ""))
                .filter((s) -> s.matches(REGEX_GET_INTEGERS))
                .mapToInt(Integer::valueOf)
                .max();

        sum.ifPresent(s -> System.out.println(s));
    }
}
