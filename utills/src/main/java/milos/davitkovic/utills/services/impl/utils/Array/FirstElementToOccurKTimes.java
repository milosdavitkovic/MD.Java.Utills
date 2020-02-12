package milos.davitkovic.utills.services.impl.utils.Array;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * First element to occur k times
 * https://practice.geeksforgeeks.org/problems/first-element-to-occur-k-times/0/?ref=self
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class FirstElementToOccurKTimes {

    public static void main (String[] args) throws IOException {
        // Using BufferedReader class to take input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // taking input of number of testcase
        Integer t = Integer.parseInt(br.readLine());

        while (t -- > 0) {
            final String n_k = br.readLine();
            final List<String> n_k_list = Arrays.asList(n_k.split(" "));
            final Integer N = Integer.valueOf(n_k_list.get(0));
            final Integer K = Integer.valueOf(n_k_list.get(1));
            final String input2 = br.readLine();
            final List<Integer> array = Arrays.asList(input2.split(" ")).stream()
                    .map(str -> Integer.parseInt(str))
                    .collect(Collectors.toList());
            findFirstElementToOccurKTimes(N, K, array);
        }
        br.close();
    }

    private static void findFirstElementToOccurKTimes(final Integer N, final Integer K, final List<Integer> array) {

//        final Map<Integer, Long> intMap = array.stream()
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//
//        final Optional<Map.Entry<Integer, Long>> result = intMap.entrySet().stream()
//                .filter(map -> map.getValue().equals(K))
//                .findFirst();
//
//        result.ifPresent(r -> System.out.println(r));

        for(int i =  0; i < N - 1; i++) {
            final Integer element = array.get(i);
            int count = 1;
            for(int j = i + 1; j < N; j++) {
                if(element.equals(array.get(j))) {
                    if(++count == K) {
                        System.out.println(element);
                        return;
                    }
                }
            }
        }

        System.out.println("-1");
    }
}
