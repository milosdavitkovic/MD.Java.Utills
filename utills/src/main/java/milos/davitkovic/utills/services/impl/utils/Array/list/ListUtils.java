package milos.davitkovic.utills.services.impl.utils.Array;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ListUtils {

    /**
     * Remove duplicate elements from ArrayList implementation of List interface.
     * @param inputList
     * @return ArrayList implementation of List interface without duplicated elements.
     */
    public <T> List<T> removeDuplicates(final List<T> inputList) {
        final Set<T> set = new HashSet<>(inputList);
        inputList.clear();
        final List<T> noDuplicatesList = new ArrayList<>(set);
        return noDuplicatesList.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public <K, V> List<K> getKeyList(final Map<K, V> map) {
        List<K> result = map.entrySet().stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return result;
    }

    public <K, V> List<V> getValueList(final Map<K, V> map) {
        List<V> result = map.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        return result;
    }

    // ##########################################################################################################
    // https://www.hackerrank.com/test/5qqdbqh3j95/questions/3pramr7a684
    // Complete the oddNumbers function below.
    public List<Integer> getOddNumbersInRange(int l, int r) {
        final List<Integer> result = new ArrayList<>();
        if(l > r) {
            return result;
        }

        for(int i = l; i <= r; i++) {
            if(isOddNumber(i)) {
                result.add(i);
            }
        }

        return result;
    }

    private boolean isOddNumber(int number) {
        return number % 2 != 0 ? true : false;
    }

    /*
     * Complete the 'findMatch' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING_ARRAY possibleMatches
     *  2. STRING crossword
     */

    public static String findMatch(List<String> possibleMatches, String crossword) {
        final Map<Integer, String> knownLetters = getKnownLetters(crossword);
        final int crosswordLength = Arrays.asList(crossword.split(" ")).size();
        for(String possibleMatch : possibleMatches) {
            if(isPossibleMatches(possibleMatch,crosswordLength,knownLetters)) {
                return possibleMatch;
            }
        }
        return StringUtils.EMPTY;
    }

    private static boolean isPossibleMatches(final String possibleMatch, final int crosswordLength, final Map<Integer, String> knownLetters) {
        final List<String> possibleMatchLetters = Arrays.asList(possibleMatch.split(" "));
        final int possibleMatchLength = possibleMatchLetters.size();
        if (possibleMatchLength != crosswordLength) {
            return false;
        }

        for (Map.Entry<Integer, String> knownLetter : knownLetters.entrySet()) {
            final Integer letterPosition = knownLetter.getKey();
            final String knownLetterValue = knownLetter.getValue();

            final String possibleMatchLetter = possibleMatchLetters.get(letterPosition);
            if (!possibleMatchLetter.equals(knownLetterValue)) {
                return false;
            }
        }

        return true;
    }

    private static Map<Integer, String> getKnownLetters(final String crossword) {
        final List<String> crosswordLetters = Arrays.asList(crossword.split(" "));
        final int crosswordLength = crosswordLetters.size();
        final Map<Integer, String> knownLetter = new HashMap<>();
        for (int i = 0; i < crosswordLength; i++) {
            final String letter = crosswordLetters.get(i);
            if (!letter.equals(".")) {
                knownLetter.put(i, letter);
            }
        }
        return knownLetter;
    }

    public <T> List<T> removeNullElements(final List<T> array) {
        return array.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
