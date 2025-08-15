package milos.davitkovic.utills.services.impl.utils.String;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Remove character
 * https://practice.geeksforgeeks.org/problems/remove-character/0
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class RemoveCharacters {

    public static void main(String[] args) throws IOException {
        // Using BufferedReader class to take input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // taking input of number of testcase
        Integer t = Integer.parseInt(br.readLine());

        while (t --> 0) {
            // take string 1 from input
            String string1 = br.readLine();
            // take string 2 from input
            String string2 = br.readLine();
            // business logic
            printMaskedString(string1, string2);
        }
        br.close();
    }

    private static void printMaskedString(final String string1, final String string2) {
        List<String> string1List = new ArrayList<String>(Arrays.asList(string1.split("")));
        List<String> string2List = new ArrayList<String>(Arrays.asList(string2.split("")));

        string2List.forEach(s2 -> {
            // check if element from string2List exist in string1List
            if(string1List.contains(s2)) {
                // remove all occurrences of this element in list
                string1List.removeAll(Arrays.asList(s2));
            }
        });
        // print String List like one String
        System.out.println(String.join("", string1List));
    }

    public String removeNewLineCharachters(final String inputString) {
        return StringUtils.trim(inputString);
    }
}
