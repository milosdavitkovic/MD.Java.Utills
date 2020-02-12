package milos.davitkovic.utills.services.impl.utils.Array.set;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SetUtils {

    public Set<String> getDifference(final Set<String> set1, final Set<String> set2) {
        return set1.stream()
                .filter(code -> !set2.contains(code))
                .collect(Collectors.toSet());
    }

    public Set<String> getIntersection(final Set<String> set1, final Set<String> set2) {
        return set1.stream()
                .filter(set2::contains)
                .collect(Collectors.toSet());
    }
}
