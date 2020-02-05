package milos.davitkovic.utills.services.impl.string;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Uncommon characters
 * https://practice.geeksforgeeks.org/problems/uncommon-characters/0/?ref=self
 *
 * @author milos.davitkovic@gmail.com
 *
 */
@Service
public class UncommonCharacters {

    public static void main (String[] args) throws IOException {
        // Using BufferedReader class to take input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // taking input of number of testcase
        Integer t = Integer.parseInt(br.readLine());

        while (t -- > 0) {
            final String input1 = br.readLine();
            final String input2 = br.readLine();
            findUncommonCharacters(input1, input2);
        }
        br.close();
    }

    private static void findUncommonCharacters(final String input1, final String input2) {
        if(input1.isEmpty() || input2.isEmpty()) {
            return;
        }
        final List<String> input1List = new ArrayList<String>(Arrays.asList(input1.split("")));
        final List<String> input2List = new ArrayList<String>(Arrays.asList(input2.split("")));

        Set<String> results = new TreeSet<String>();
        for(String s1 : input1List) {
            if(!input2List.contains(s1)) {
                results.add(s1);
            }
        }

        for(String s2 : input2List) {
            if(!input1List.contains(s2)) {
                results.add(s2);
            }
        }

        final String result = String.join("", results);
        System.out.println(result);
    }
}
