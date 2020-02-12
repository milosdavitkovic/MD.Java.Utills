package milos.davitkovic.utills.services.impl.utils.Array;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Search in Rotated Sorted Array II
 *
 * author milos.davitkovic@gmail.com
 */
@Service
public class SearchRotatedSortedArrayII {

    public boolean search(int[] nums, int target) {
        List<Integer> numbers = new ArrayList<>();
        for(int n : nums) {
            numbers.add(n);
        }
        return numbers.stream()
                .anyMatch(num -> num.equals(target));
    }
}
