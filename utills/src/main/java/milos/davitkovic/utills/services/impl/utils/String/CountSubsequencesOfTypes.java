package milos.davitkovic.utills.services.impl.string;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Count subsequences of type a^i b^j c^k
 * https://practice.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck/0
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class CountSubsequencesOfTypes {

    private static List<String> SEQUENCE = new ArrayList(Arrays.asList("a", "b", "c"));

    public void main (String[] args) throws IOException {
        // Using BufferedReader class to take input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // taking input of number of testcase
        Integer t = Integer.parseInt(br.readLine());

        while (t -- > 0) {
            final String inputArray = br.readLine();
            final List<String> array = new ArrayList<String>(Arrays.asList(inputArray.split("")));
            countNumberOfSubsequences(array);
        }
        br.close();
    }

    private void countNumberOfSubsequences(final List<String> input) {
        int countOfSubsequences = 0;

        for(int i =  0; i < input.size() - 2; i++) {
            if(input.get(i).equals(SEQUENCE.get(0))) {

                for(int j = i + 1; j < input.size() - 1; j++) {
                    if(input.get(j).equals(SEQUENCE.get(1))) {

                        for(int k = j + 1; k < input.size(); k++) {
                            if(input.get(k).equals(SEQUENCE.get(2))) {
                                countOfSubsequences++;
                            }
                        }
                    }
                }
            }
        }

        System.out.print(countOfSubsequences + "\n");
    }
}
