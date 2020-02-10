package milos.davitkovic.utills.services.impl.utils.Array;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ListUtils {

    /**
     * Remove duplicate elements from ArrayList implementation of List interface.
     * @param inputList
     * @return ArrayList implementation of List interface without duplicated elements.
     */
    public List<?> removeDuplicates(final List<?> inputList) {
        final Set<?> set = new HashSet<>(inputList);
        inputList.clear();
        final List<?> noDuplicatesList = new ArrayList<>(set);
        return noDuplicatesList.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> mapToList(final Map<Integer, String> map) {
        List<Integer> result = map.entrySet().stream()
                .map(x -> x.getKey())
                .collect(Collectors.toList());

        result.forEach(System.out::println);

        System.out.println("\n2. Export Map Value to List...");

        List<String> result2 = map.entrySet().stream()
                .map(x -> x.getValue())
                .collect(Collectors.toList());

        result2.forEach(System.out::println);

        return result2;
    }
}
