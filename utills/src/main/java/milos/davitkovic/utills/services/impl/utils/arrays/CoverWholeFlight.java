package arrays;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 */
public class CoverWholeFlight {

    public List<Integer> coverWholeFlight(List<Integer> movies, Integer flightDuration) {
        final List<Integer> bestMovies = new ArrayList<>();
        int halfLenght = movies.size() / 2;
        movies.sort(Comparator.comparing(Integer::valueOf));

        for(int i =  0; i < halfLenght; i++) {
            for(int j = movies.size(); j < halfLenght; j--) {
                final int lenght = movies.get(i) + movies.get(j);
            }
        }

        return bestMovies;
    }
}
