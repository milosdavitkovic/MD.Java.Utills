package milos.davitkovic.javautil.utills.services.impl.utils.String;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.javautil.utills.annotations.UtilClass;
import org.apache.commons.lang3.StringUtils;

import java.text.Collator;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Milos Davitkovic
 */
@Slf4j
@UtilClass
@NoArgsConstructor
public class StringFn {

    /**
     * Split provided string value with delimiter
     *
     * @param inputString
     * @param delimiter
     * @return
     */
    public String[] splitString(String inputString, String delimiter) {
        return inputString.split(Pattern.quote(delimiter));        // String[] output = phone.split("-");
    }

    /**
     * Sort all characters in the string alphabetically
     *
     * @param string
     * @return
     */
    public String sortCharInStringAlphabetically(final String string) {
        String newString = string
                .chars()
                .distinct()
                .mapToObj(c -> String.valueOf((char) c))
                .sorted()
                .collect(Collectors.joining());
        return newString;
    }

    /**
     * // Test this string.
     * final String test = "DEFINE:A=TWO";
     * <p>
     * // Call between, before and after methods.
     * System.out.println(between(test, "DEFINE:", "="));
     * Output: A
     * System.out.println(between(test, ":", "="));
     * Output: A
     *
     * @param value
     * @param a
     * @param b
     * @return Substring between the two strings.
     */
    public String between(String value, String a, String b) {
        // Return a substring between the two strings.
        int posA = value.indexOf(a);
        if (posA == -1) {
            return "";
        }
        int posB = value.lastIndexOf(b);
        if (posB == -1) {
            return "";
        }
        int adjustedPosA = posA + a.length();
        if (adjustedPosA >= posB) {
            return "";
        }
        return value.substring(adjustedPosA, posB);
    }

    /**
     * // Test this string.
     * final String test = "DEFINE:A=TWO";
     * System.out.println(before(test, ":"));
     * Output: DEFINE
     * System.out.println(before(test, "="));
     * Output: DEFINE:A
     *
     * @param value
     * @param a
     * @return Substring containing all characters before a string.
     */
    public String before(String value, String a) {
        // Return substring containing all characters before a string.
        int posA = value.indexOf(a);
        if (posA == -1) {
            return "";
        }
        return value.substring(0, posA);
    }

    /**
     * // Test this string.
     * final String test = "DEFINE:A=TWO";
     * System.out.println(after(test, ":"));
     * Output: A=TWO
     * System.out.println(after(test, "DEFINE:"));
     * Output: A=TWO
     * System.out.println(after(test, "="));
     * Output: TWO
     *
     * @param value
     * @param a
     * @return Substring containing all characters after a string.
     */
    public String after(String value, String a) {
        // Returns a substring containing all characters after a string.
        int posA = value.lastIndexOf(a);
        if (posA == -1) {
            return "";
        }
        int adjustedPosA = posA + a.length();
        if (adjustedPosA >= value.length()) {
            return "";
        }
        return value.substring(adjustedPosA);
    }

    /**
     * Get right characters from a string, change the begin index.
     * // Test the right method.
     * String value = "website";
     * String result = right(value, 4);
     * System.out.println(result);
     * Output: site
     * <p>
     * value = "Java Virtual Machine";
     * result = right(value, 7);
     * System.out.println(result);
     * Output: Machine
     *
     * @param value
     * @param length
     * @return String with right characters from a string
     */
    public static String right(String value, int length) {
        // To get right characters from a string, change the begin index.
        return value.substring(value.length() - length);
    }

    /**
     * String value = "One two three four five.";
     * System.out.println(value);
     * Output: One two three four five.
     * <p>
     * // Test firstWords on the first 3 and 4 words.
     * String words3 = firstWords(value, 3);
     * System.out.println(words3);
     * Output: One two three
     * String words4 = firstWords(value, 4);
     * System.out.println(words4);
     * Output: One two three four
     *
     * @param input
     * @param words
     * @return String sentence with specified number of words, from input sentence
     */
    public String firstWords(String input, int words) {
        for (int i = 0; i < input.length(); i++) {
            // When a space is encountered, reduce words remaining by 1.
            if (input.charAt(i) == ' ') {
                words--;
            }
            // If no more words remaining, return a substring.
            if (words == 0) {
                return input.substring(0, i);
            }
        }
        // Error case.
        return "";
    }

    /**
     * String test = "apple";
     * // ... Truncate to 3 characters.
     * String result1 = truncate(test, 3);
     * System.out.println(result1);
     * <p>
     * // ... Truncate to larger sizes: no exception occurs.
     * String result2 = truncate(test, 10);
     * System.out.println(result2);
     * <p>
     * String result3 = truncate(test, 5);
     * System.out.println(result3);
     * <p>
     * Output:
     * app
     * apple
     * apple
     *
     * @param value
     * @param length
     * @return
     */
    public String truncate(String value, int length) {
        // Ensure String length is longer than requested size.
        if (value.length() > length) {
            return value.substring(0, length);
        } else {
            return value;
        }
    }

    /**
     * Function remove all whitespaces from input String
     * String value = " Hi,\r\n\t\thow are  you?";
     * // Test our methods.
     * String result = removeAllWhitespace(value);
     * System.out.println(result);
     * Output: Hi,howareyou?
     *
     * @param value
     * @return String witout whitespaces
     */
    public String removeAllWhitespace(String value) {
        // Remove all whitespace characters.
        return value.replaceAll("\\s", "");
    }

    // ##########################################################################################################

    /**
     * String value = " Hi,\r\n\t\thow are  you?";
     * // Test our methods.
     * result = collapseWhitespace(value);
     * System.out.println(result);
     * output: Hi, how are you?
     *
     * @param value
     * @return
     */
    public String collapseWhitespace(String value) {
        // Replace all whitespace blocks with single spaces.
        return value.replaceAll("\\s+", " ");
    }

    // ##########################################################################################################

    public String eval(List<Integer> list, Predicate<Integer> predicate) {
        String output = "";
        for (Integer n : list) {

            if (predicate.test(n)) {
                output += n + " ";
            }
        }
        return output;
    }

    // ##########################################################################################################

    public String evenNumbers(List<Integer> list) {
        return eval(list, n -> n % 2 == 0);
    }

    // ##########################################################################################################

    public String oddNumbers(List<Integer> list) {
        return eval(list, n -> n % 2 != 0);
    }

    // ##########################################################################################################

    public String greaterThenNumbers(List<Integer> list, Integer number) {
        return eval(list, n -> n > number);
    }

    // ##########################################################################################################

    /**
     * Java 8
     *
     * @param lines
     * @param filter
     * @return List of String without specified filter item
     */
    public List<String> getListWithout(List<String> lines, String filter) {
        List<String> result = lines.stream()            //convert list to stream
                .filter(line -> !filter.equals(line))    //filters the line, equals to "mkyong"
                .collect(Collectors.toList());            //collect the output and convert streams to a List
        return result;
    }

    // ##########################################################################################################


    /**
     * String a = "This is the first string.";
     * String b = "this is not 1st string!";
     * float similarity = simpleSimilarity(a,b);
     * <p>
     * // These two have the same value
     * new String("test").equals("test") // --> true
     * <p>
     * // ... but they are not the same object
     * new String("test") == "test" // --> false
     * <p>
     * // ... neither are these
     * new String("test") == new String("test") // --> false
     * <p>
     * // ... but these are because literals are interned by
     * // the compiler and thus refer to the same object
     * "test" == "test" // --> true
     * <p>
     * // ... but you should really just call Objects.equals()
     * Objects.equals("test", new String("test")) // --> true
     * Objects.equals(null, "test") // --> false
     * <p>
     * You almost always want to useObjects.equals(). In the rare situation where you know you're dealing with interned strings, you can use ==.
     *
     * @param u
     * @param v
     * @return
     */
    public float simpleStringSimilarity(String u, String v) {
        String[] a = u.split(" ");
        String[] b = v.split(" ");

        long correct = 0;
        int minLen = Math.min(a.length, b.length);

        for (int i = 0; i < minLen; i++) {
            String aa = a[i];
            String bb = b[i];
            int minWordLength = Math.min(aa.length(), bb.length());

            for (int j = 0; j < minWordLength; j++) {
                if (aa.charAt(j) == bb.charAt(j)) {
                    correct++;
                }
            }
        }

        return (float) (((double) correct) / Math.max(u.length(), v.length()));
    }

    // ##########################################################################################################

    /**
     * Calculates the similarity (a number within 0 and 1) between two strings.
     * <p>
     * Testing:
     * printSimilarity("", "");
     * printSimilarity("1234567890", "1");
     * printSimilarity("1234567890", "123");
     * printSimilarity("1234567890", "1234567");
     * printSimilarity("1234567890", "1234567890");
     * printSimilarity("1234567890", "1234567980");
     * printSimilarity("47/2010", "472010");
     * printSimilarity("47/2010", "472011");
     * printSimilarity("47/2010", "AB.CDEF");
     * printSimilarity("47/2010", "4B.CDEFG");
     * printSimilarity("47/2010", "AB.CDEFG");
     * printSimilarity("The quick fox jumped", "The fox jumped");
     * printSimilarity("The quick fox jumped", "The fox");
     * printSimilarity("kitten", "sitting");
     * <p>
     * Output:
     * 1.000 is the similarity between "" and ""
     * 0.100 is the similarity between "1234567890" and "1"
     * 0.300 is the similarity between "1234567890" and "123"
     * 0.700 is the similarity between "1234567890" and "1234567"
     * 1.000 is the similarity between "1234567890" and "1234567890"
     * 0.800 is the similarity between "1234567890" and "1234567980"
     * 0.857 is the similarity between "47/2010" and "472010"
     * 0.714 is the similarity between "47/2010" and "472011"
     * 0.000 is the similarity between "47/2010" and "AB.CDEF"
     * 0.125 is the similarity between "47/2010" and "4B.CDEFG"
     * 0.000 is the similarity between "47/2010" and "AB.CDEFG"
     * 0.700 is the similarity between "The quick fox jumped" and "The fox jumped"
     * 0.350 is the similarity between "The quick fox jumped" and "The fox"
     * 0.571 is the similarity between "kitten" and "sitting"
     */
    public static double similarityLevenshtein(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0; /* both strings are zero length */
        }
		/* // If you have StringUtils, you can use it to calculate the edit distance:
	    return (longerLength - StringUtils.getLevenshteinDistance(longer, shorter)) /
	                               (double) longerLength; */
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }

    /**
     * is provided value integer value
     *
     * @param value
     * @return
     */
    private boolean isInteger(final String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * is provided value boolean value
     * <p>
     * System.out.println(isBoolean(null)); //false
     * System.out.println(isBoolean("")); //false
     * System.out.println(isBoolean("true")); //true
     * System.out.println(isBoolean("fALsE")); //true
     * System.out.println(isBoolean("asdf")); //false
     * System.out.println(isBoolean("01truefalse")); //false
     *
     * @param value
     * @return
     */
    private boolean isBoolean(final String value) {
        return value != null && Arrays.stream(new String[]{"true", "false", "1", "0"})
                .anyMatch(b -> b.equalsIgnoreCase(value));
    }

    /**
     * is provided value string value
     *
     * @param value
     * @return
     */
    private boolean isString(final String value) {
        final String stringValue = String.valueOf(value);
        return stringValue != null;
    }

    private boolean isNotContains(final String inputValue, final String containsString) {
        return !StringUtils.contains(inputValue, containsString);
    }

    // Example implementation of the Levenshtein Edit Distance
    // See http://rosettacode.org/wiki/Levenshtein_distance#Java
    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }

    // ##########################################################################################################


    // ##########################################################################################################

    /**
     * @param inputList
     * @param startString
     * @return
     */
    public List<String> startsWith(List<String> inputList, String startString) {
        List<String> resultList = new ArrayList<String>();
        for (String str : inputList) {
            if (str.startsWith(startString))
                resultList.add(str);
        }
        return resultList;
    }


    // ##########################################################################################################

    /**
     * Testing:
     * List<String> countries = Arrays.asList("Slovensko", "Švédsko", "Turecko");
     * <p>
     * Collections.sort(countries);
     * System.out.println(countries); // outputs [Slovensko, Turecko, Švédsko]
     * <p>
     * Collections.sort(countries, Collator.getInstance(new Locale("sk")));
     * System.out.println(countries); // outputs [Slovensko, Švédsko, Turecko]
     *
     * @param inputList
     * @param languageCode: "sk", "en" (English), "ja" (Japanese), "kok" (Konkani), "Latn" (Latin), "Cyrl" (Cyrillic), "US" (United States), "FR" (France), "029" (Caribbean)
     * @return
     */
    public List<String> sortListByLocale(List<String> inputList, String languageCode) {
        List<String> resultList = new ArrayList<String>();
        resultList = inputList;
        Collections.sort(resultList, Collator.getInstance(new Locale(languageCode)));
        return resultList;
    }


    // ##########################################################################################################


    // ##########################################################################################################

    /**
     * @param inputString
     * @return
     */
    public String capitalizeFirstLetter(String inputString) {
        return inputString.substring(0, 1).toUpperCase() + inputString.substring(1);
    }

    // ##########################################################################################################

    public String capitalizeEveryWordFirstLetter(String word) {
        String[] words = word.split(" ");
        StringBuilder sb = new StringBuilder();
        if (words[0].length() > 0) {
            sb.append(Character.toUpperCase(words[0].charAt(0)) + words[0].subSequence(1, words[0].length()).toString().toLowerCase());
            for (int i = 1; i < words.length; i++) {
                sb.append(" ");
                sb.append(Character.toUpperCase(words[i].charAt(0)) + words[i].subSequence(1, words[i].length()).toString().toLowerCase());
            }
        }
        return sb.toString();
    }

    // ##########################################################################################################

    public String[] wordsOfString(String input) {
        try {
            if (input == null || input.isEmpty()) {
                String[] array = new String[0];
                return array;
            }
            String[] words = input.split("\\s+");
            return words;
        } catch (Exception e) {
            e.printStackTrace();
            String[] array = new String[0];
            return array;
        }
    }


    // ##########################################################################################################

    public Integer countWordsOfString(String input) {
        try {
            if (input == null || input.isEmpty()) {
                return 0;
            }
            String[] words = input.split("\\s+");
            return words.length;

//			StringTokenizer tokens = new StringTokenizer(input);
//			return tokens.countTokens();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ##########################################################################################################

    /**
     * SAP ERP system expect some speficit number and format. If SAP expect the number with 10 digits you need
     * to convert Integer with this function, otherwise you will send for example: 10 instead 0000000010;
     */
    private String convertNumberToSpecificLenghtString(final String code) {
        if (code.matches("\\d+")) {
            return String.format("%010d", Integer.valueOf(code));
        }
        return code;
    }


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################


    // ##########################################################################################################
}
