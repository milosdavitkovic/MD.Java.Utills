package milos.davitkovic.utills.services.impl.utils.Array.list;

import milos.davitkovic.utills.annotations.UtilClass;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@UtilClass
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

    public <T> List<String> convertToStringList(final Collection<T> input) {
        final List<String> inputText = new ArrayList<>();
        for(T inputElement : input) {
            final String element = String.valueOf(inputElement);
            inputText.add(element);
        }
        return inputText;
    }


    /**
     * Find duplicate elements in the list.
     * For example I have list [1, 1, 2, 3, 3, 3] and as result want to have [1, 3]
     *
     * @param inputCollection
     * @param <T>
     * @return
     */
    public <T> Set<T> getDuplicates(final Collection<T> inputCollection) {
        final Set<T> uniques = new HashSet<>();
        return inputCollection.stream()
                .filter(e -> !uniques.add(e))
                .collect(Collectors.toSet());
    }

    /**
     *
     * If original list is filled with {1,1,2,3,6,3,8,7}
     * and frequency is 1
     * returns {2,6,8,7} - returns numbers which occur only once
     * @param frequency
     * @param <T>
     * @return
     */
    public <T> List<T> getElementsWithFrequency(final List<T> inputList, final int frequency) {
        return inputList.stream()
                .filter(e -> Collections.frequency(inputList, e) == frequency)
                .collect(Collectors.toList());
    }

    /**
     *
     * If original list is filled with {1,1,2,3,6,3,8,7}
     * and frequency is 1
     * returns {1,3} - returns only numbers which occur more times than 1
     * @param inputList
     * @param frequency
     * @param <T>
     * @return
     */
    public <T> List<T> getElementsWithHigherFrequencyThen(final List<T> inputList, final int frequency) {
        return inputList.stream()
                .filter(e -> Collections.frequency(inputList, e) > frequency)
                .collect(Collectors.toList());
    }

    /**
     *
     * If original list is filled with {1,1,2,3,6,3,8,7}
     * and frequency is 2
     * returns {1,3} - returns only numbers which occur less times than 1
     * @param inputList
     * @param frequency
     * @param <T>
     * @return
     */
    public <T> List<T> getElementsWithLowerFrequencyThen(final List<T> inputList, final int frequency) {
        return inputList.stream()
                .filter(e -> Collections.frequency(inputList, e) < frequency)
                .collect(Collectors.toList());
    }

    /**
     *
     * If original list is filled with {1,1,2,3,6,3,8,7}
     * returns {1,2,3,6,8,7} - returns the list without duplicates
     * @param inputList
     * @param <T>
     * @return
     */
    public <T> List<T> getListWithoutDuplicates(final List<T> inputList) {
        return inputList.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public <K, V> List<K> getKeyList(final Map<K, V> map) {
        return map.entrySet().stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public <K, V> List<V> getValueList(final Map<K, V> map) {
        return map.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
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
