package milos.davitkovic.utills.services.impl.utils.String;

import info.debatty.java.stringsimilarity.Damerau;
import info.debatty.java.stringsimilarity.JaroWinkler;
import info.debatty.java.stringsimilarity.Levenshtein;
import info.debatty.java.stringsimilarity.NGram;

import info.debatty.java.stringsimilarity.QGram;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 
 * @author Milos Davitkovic
 *
 */
@Service
public class StringFn {

	/**
	 * 
	 * @param inputString
	 * @param delimiter
	 * @return
	 */
	public String[] splitString(String inputString, String delimiter) {
		return inputString.split(Pattern.quote(delimiter));		// String[] output = phone.split("-");
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public String sortCharInStringAlphabetically(String string) {
		String newString = string
				.chars()
				.distinct()
				.mapToObj(c -> String.valueOf((char)c))
				.sorted()
				.collect(Collectors.joining());
		return newString;
	}

	/**
	 * // Test this string.
	final String test = "DEFINE:A=TWO";

	// Call between, before and after methods.
	System.out.println(between(test, "DEFINE:", "=")); 
	Output: A
	System.out.println(between(test, ":", "="));
	Output: A
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
	final String test = "DEFINE:A=TWO";
	 * System.out.println(before(test, ":"));
	 * Output: DEFINE
	System.out.println(before(test, "="));
	Output: DEFINE:A
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
	final String test = "DEFINE:A=TWO";
	System.out.println(after(test, ":"));
	Output: A=TWO
	System.out.println(after(test, "DEFINE:"));
	Output: A=TWO
	System.out.println(after(test, "="));
	Output: TWO
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
	String value = "website";
	String result = right(value, 4);
	System.out.println(result);
	Output: site

	value = "Java Virtual Machine";
	result = right(value, 7);
	System.out.println(result);
	Output: Machine
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
	System.out.println(value);
	Output: One two three four five.

	// Test firstWords on the first 3 and 4 words.
	String words3 = firstWords(value, 3);
	System.out.println(words3);
	Output: One two three
	String words4 = firstWords(value, 4);
	System.out.println(words4);
	Output: One two three four

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
	// ... Truncate to 3 characters.
	String result1 = truncate(test, 3);
	System.out.println(result1);

	// ... Truncate to larger sizes: no exception occurs.
	String result2 = truncate(test, 10);
	System.out.println(result2);

	String result3 = truncate(test, 5);
	System.out.println(result3);

	Output:
	app
	apple
	apple
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
	// Test our methods.
	String result = removeAllWhitespace(value);
	System.out.println(result);
	Output: Hi,howareyou?
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
	// Test our methods.
	 * result = collapseWhitespace(value);
	System.out.println(result);
	output: Hi, how are you?
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
		for(Integer n: list) {

			if(predicate.test(n)) {
				output += n + " ";
			}
		}
		return output;
	}
	
	// ##########################################################################################################

	public String evenNumbers(List<Integer> list) {
		return eval(list, n-> n%2 == 0 );
	}
	
	// ##########################################################################################################

	public String oddNumbers(List<Integer> list) {
		return eval(list, n-> n%2 != 0 );
	}
	
	// ##########################################################################################################

	public String greaterThenNumbers(List<Integer> list, Integer number) {
		return eval(list, n-> n > number );
	}
	
	// ##########################################################################################################

	/**
	 * Java 8
	 * @param lines
	 * @param filter
	 * @return List of String without specified filter item
	 */
	public List<String> getListWithout(List<String> lines, String filter) {
		List<String> result = lines.stream() 			//convert list to stream
				.filter(line -> !filter. equals (line))	//filters the line, equals to "mkyong"
				.collect(Collectors.toList());			//collect the output and convert streams to a List
		return result;
	}

	// ##########################################################################################################
	
	
	/**
	 * 	String a = "This is the first string.";
		String b = "this is not 1st string!";
		float similarity = simpleSimilarity(a,b);

		// These two have the same value
new String("test").equals("test") // --> true 

// ... but they are not the same object
new String("test") == "test" // --> false 

// ... neither are these
new String("test") == new String("test") // --> false 

// ... but these are because literals are interned by 
// the compiler and thus refer to the same object
"test" == "test" // --> true 

// ... but you should really just call Objects.equals()
Objects.equals("test", new String("test")) // --> true
Objects.equals(null, "test") // --> false

You almost always want to useObjects.equals(). In the rare situation where you know you're dealing with interned strings, you can use ==.
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
	 * 
	 * Testing:
	 *  printSimilarity("", "");
	    printSimilarity("1234567890", "1");
	    printSimilarity("1234567890", "123");
	    printSimilarity("1234567890", "1234567");
	    printSimilarity("1234567890", "1234567890");
	    printSimilarity("1234567890", "1234567980");
	    printSimilarity("47/2010", "472010");
	    printSimilarity("47/2010", "472011");
	    printSimilarity("47/2010", "AB.CDEF");
	    printSimilarity("47/2010", "4B.CDEFG");
	    printSimilarity("47/2010", "AB.CDEFG");
	    printSimilarity("The quick fox jumped", "The fox jumped");
	    printSimilarity("The quick fox jumped", "The fox");
	    printSimilarity("kitten", "sitting");

    Output:
    1.000 is the similarity between "" and ""
	0.100 is the similarity between "1234567890" and "1"
	0.300 is the similarity between "1234567890" and "123"
	0.700 is the similarity between "1234567890" and "1234567"
	1.000 is the similarity between "1234567890" and "1234567890"
	0.800 is the similarity between "1234567890" and "1234567980"
	0.857 is the similarity between "47/2010" and "472010"
	0.714 is the similarity between "47/2010" and "472011"
	0.000 is the similarity between "47/2010" and "AB.CDEF"
	0.125 is the similarity between "47/2010" and "4B.CDEFG"
	0.000 is the similarity between "47/2010" and "AB.CDEFG"
	0.700 is the similarity between "The quick fox jumped" and "The fox jumped"
	0.350 is the similarity between "The quick fox jumped" and "The fox"
	0.571 is the similarity between "kitten" and "sitting"
	 */
	public static double similarityLevenshtein(String s1, String s2) {
		String longer = s1, shorter = s2;
		if (s1.length() < s2.length()) { // longer should always have greater length
			longer = s2; shorter = s1;
		}
		int longerLength = longer.length();
		if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
		/* // If you have StringUtils, you can use it to calculate the edit distance:
	    return (longerLength - StringUtils.getLevenshteinDistance(longer, shorter)) /
	                               (double) longerLength; */
		return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

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

//	//Usage of Apache Commons Lang 3
//	// import org.apache.commons.lang3.StringUtils;
//	/**
//	 *
//	 * @param stringA
//	 * @param stringB
//	 * @return
//	 */
//	public double compareStringsApache(String stringA, String stringB) {
//	    return StringUtils.getJaroWinklerDistance(stringA, stringB);
//	}
	
	// ##########################################################################################################
	/**
	 * The Levenshtein distance between two words is the minimum number of single-character 
	 * edits (insertions, deletions or substitutions) required to change one word into the other.

		It is a metric string distance. This implementation uses dynamic programming (Wagner–Fischer algorithm), 
		with only 2 rows of data. The space requirement is thus O(m) and the algorithm runs in O(m.n).
		
								Normalized?	  Metric?	Cost
		Levenshtein	distance		No			Yes		O(m*n) 1

	 * @param string1
	 * @param string2
	 * @return
	 */
//	public double similarityOfStringsLevenshtein(String string1, String string2) {
//		Levenshtein l = new Levenshtein();
//		return l.distance(string1, string2);
//	}
	
	// ##########################################################################################################
	
	/**
	 * This distance is computed as levenshtein distance divided by the length of the longest string. 
	 * The resulting value is always in the interval [0.0 1.0] but it is not a metric anymore!

		The similarity is computed as 1 - normalized distance.
	 * @param string1
	 * @param string2
	 * @return
	 */
//	public double similarityOfStringsNormalizedLevenshtein(String string1, String string2) {
//		NormalizedLevenshtein  l = new NormalizedLevenshtein();
//		return l.distance(string1, string2);
//	}
	
	// ##########################################################################################################
	
	/**
	 * Similar to Levenshtein, Damerau-Levenshtein distance with transposition (also sometimes calls unrestricted Damerau-Levenshtein distance) is the minimum number of operations needed to transform one string into the other, where an operation is defined as an insertion, deletion, or substitution of a single character, or a transposition of two adjacent characters.

		It does respect triangle inequality, and is thus a metric distance.

		This is not to be confused with the optimal string alignment distance, which is an extension where no substring can be edited more than once.
	
	 * Testing:
	 * // 1 substitution
        System.out.println(d.distance("ABCDEF", "ABDCEF"));

        // 2 substitutions
        System.out.println(d.distance("ABCDEF", "BACDFE"));

        // 1 deletion
        System.out.println(d.distance("ABCDEF", "ABCDE"));
        System.out.println(d.distance("ABCDEF", "BCDEF"));
        System.out.println(d.distance("ABCDEF", "ABCGDEF"));

        // All different
        System.out.println(d.distance("ABCDEF", "POIU"));
        
        Output:
        1.0
		2.0
		1.0
		1.0
		1.0
		6.0
        
	 * 
	 * @param string1
	 * @param string2
	 * @return
	 */
//	public double similarityOfStringsDamerauLevenshtein(String string1, String string2) {
//		Damerau d = new Damerau();
//		return d.distance(string1, string2);
//	}
	
	// ##########################################################################################################
	
	/**
	 * The Optimal String Alignment variant of Damerau–Levenshtein (sometimes called the restricted edit distance) 
	 * computes the number of edit operations needed to make the strings equal under the condition that no substring 
	 * is edited more than once, whereas the true Damerau–Levenshtein presents no such restriction. The difference 
	 * from the algorithm for Levenshtein distance is the addition of one recurrence for the transposition operations.

	Note that for the optimal string alignment distance, the triangle inequality does not hold and so it is not a 
	true metric.
	
	Testing:
	System.out.println(osa.distance("CA", "ABC"));
	
	Output:
	3.0
	 * @param string1
	 * @param string2
	 * @return
	 */
//	public double similarityOfStringsOptimalStringAlignment(String string1, String string2) {
//		OptimalStringAlignment osa = new OptimalStringAlignment();
//		return osa.distance(string1, string2);
//	}
	
	// ##########################################################################################################
	
	/**
	 * Jaro-Winkler is a string edit distance that was developed in the area of record linkage (duplicate detection) 
	 * (Winkler, 1990). The Jaro–Winkler distance metric is designed and best suited for short strings such as person 
	 * names, and to detect typos.

	Jaro-Winkler computes the similarity between 2 strings, and the returned value lies in the interval [0.0, 1.0]. 
	It is (roughly) a variation of Damerau-Levenshtein, where the substitution of 2 close characters is considered 
	less important then the substitution of 2 characters that a far from each other.

	The distance is computed as 1 - Jaro-Winkler similarity.

		Testing:
		// substitution of s and t
        System.out.println(jw.similarity("My string", "My tsring"));

        // substitution of s and n
        System.out.println(jw.similarity("My string", "My ntrisg"));
        
        Output:
        0.9740740656852722
		0.8962963223457336
        
	 * @param string1
	 * @param string2
	 * @return
	 */
	public double similarityOfStringsJaroWinkler(String string1, String string2) {
		final JaroWinkler jw = new JaroWinkler();
		return jw.similarity(string1, string2);
	}
	
	// ##########################################################################################################
	
	/**
	 * Distance metric based on Longest Common Subsequence, from the notes "An LCS-based string metric" by Daniel Bakkelund. http://heim.ifi.uio.no/~danielry/StringMetric.pdf

The distance is computed as 1 - |LCS(s1, s2)| / max(|s1|, |s2|)

		Testing:
		String s1 = "ABCDEFG";   
        String s2 = "ABCDEFHJKL";
        // LCS: ABCDEF => length = 6
        // longest = s2 => length = 10
        // => 1 - 6/10 = 0.4
        System.out.println(lcs.distance(s1, s2));

        // LCS: ABDF => length = 4
        // longest = ABDEF => length = 5
        // => 1 - 4 / 5 = 0.2
        System.out.println(lcs.distance("ABDEF", "ABDIF"));
		
	 * @param string1
	 * @param string2
	 * @return
	 */
//	public double similarityOfStringsMetricLongestCommonSubsequence(String string1, String string2) {
//		info.debatty.java.stringsimilarity.MetricLCS lcs =
//                new info.debatty.java.stringsimilarity.MetricLCS();
//		return lcs.distance(string1, string2);
//	}
	
	// ##########################################################################################################
	
	/**
	 * Normalized N-Gram distance as defined by Kondrak, "N-Gram Similarity and Distance", String Processing and Information Retrieval, Lecture Notes in Computer Science Volume 3772, 2005, pp 115-126.

http://webdocs.cs.ualberta.ca/~kondrak/papers/spire05.pdf

The algorithm uses affixing with special character '\n' to increase the weight of first characters. The normalization is achieved by dividing the total similarity score the original length of the longest word.

In the paper, Kondrak also defines a similarity measure, which is not implemented (yet).
	 * 
	 * 
	 * 
	 * 
	 * Testing:
	 *  // produces 0.416666
        NGram twogram = new NGram(2);
        System.out.println(twogram.distance("ABCD", "ABTUIO"));

        // produces 0.97222
        String s1 = "Adobe CreativeSuite 5 Master Collection from cheap 4zp";
        String s2 = "Adobe CreativeSuite 5 Master Collection from cheap d1x";
        NGram ngram = new NGram(4);
        System.out.println(ngram.distance(s1, s2));
	 * 
	 * @param string1
	 * @param string2
	 * @return
	 */
//	public double similarityOfStringsNGram(String string1, String string2) {
//		NGram twogram = new NGram(2);
//		return twogram.distance(string1, string2);
//	}
	
	// ##########################################################################################################
	
	/**
	 * A few algorithms work by converting strings into sets of n-grams (sequences of n characters, also sometimes called k-shingles). The similarity or distance between the strings is then the similarity or distance between the sets.

The cost for computing these similarities and distances is mainly domnitated by k-shingling (converting the strings into sequences of k characters). Therefore there are typically two use cases for these algorithms:

Directly compute the distance between strings:

		Testing:
		QGram dig = new QGram(2);

        // AB BC CD CE
        // 1  1  1  0
        // 1  1  0  1
        // Total: 2

        System.out.println(dig.distance("ABCD", "ABCE"));
		
	 * @param string1
	 * @param string2
	 * @return
	 */
	public double similarityOfStringsQGram(String string1, String string2) {
		QGram dig = new QGram(2);
		return dig.distance(string1, string2);
	}
	
	// ##########################################################################################################
	
	/**
	 * 
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

Collections.sort(countries);
System.out.println(countries); // outputs [Slovensko, Turecko, Švédsko]

Collections.sort(countries, Collator.getInstance(new Locale("sk")));
System.out.println(countries); // outputs [Slovensko, Švédsko, Turecko]

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
	
	/**
	 * This is function recommended for short strings becouse of using JeroWinker algorithm for 
	 * string similarity which is good for short strings.
	 * @param inputList
	 * @param inputString
	 * @return
	 */
	public List<String> recommendSimilarFromList(List<String> inputList, String inputString) {
		TreeMap<Integer, String> resultMap = new TreeMap<Integer, String>(Collections.reverseOrder());
		List<String> resultList = new ArrayList<String>();
		List<String> shorterInputList = new ArrayList<String>();
		String firstLetter  = "";
		Double similarity;
		if (inputString.length() < 4)
			firstLetter = inputString.substring(0, 1);
		if (inputString.length() < 6)
			firstLetter = inputString.substring(0, 2);
		if (inputString.length() < 8)
			firstLetter = inputString.substring(0, 3);
		if (inputString.length() >= 8)
			firstLetter = inputString.substring(0, 4);
		shorterInputList = startsWith(inputList, firstLetter);
//		shorterInputList.forEach(item -> System.out.println(item));
		for (String str : shorterInputList) {
			similarity = similarityOfStringsJaroWinkler(str, inputString);
//			System.out.println(str + " - " + similarity);
			if (similarity > 0.6) {
				Double tmpSimilarity = similarity * 100;
				resultMap.put(Integer.valueOf(tmpSimilarity.intValue()), str);
//				resultList.add(str);
//				System.out.println(str + " - " + similarity);
			}
		}
//		resultList.forEach(item -> System.out.println(item));
		
		
		/* Display content using Iterator*/
	      Set set = resultMap.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	         System.out.println(mentry.getValue());
	         resultList.add(mentry.getValue().toString());
	      }
	      
		if (resultList.size() <= 3)
			return resultList;
		if (resultList.size() <= 5)
			return resultList.subList(0, 3);
		if (resultList.size() <= 10)
			return resultList.subList(0, 4);
		if (resultList.size() > 10)
			return resultList.subList(0, 5);
		return resultList;
	}
	
	// ##########################################################################################################
	
	/**
	 * 
	 * @param inputString
	 * @return
	 */
	public String capitalizeFirstLetter(String inputString) {
		return inputString.substring(0, 1).toUpperCase() + inputString.substring(1);
	}
	
	// ##########################################################################################################
	
	public String capitalizeEveryWordFirstLetter(String word){
        String[] words = word.split(" ");
        StringBuilder sb = new StringBuilder();
        if (words[0].length() > 0) {
            sb.append(Character.toUpperCase(words[0].charAt(0)) + words[0].subSequence(1, words[0].length()).toString().toLowerCase());
            for (int i = 1; i < words.length; i++) {
                sb.append(" ");
                sb.append(Character.toUpperCase(words[i].charAt(0)) + words[i].subSequence(1, words[i].length()).toString().toLowerCase());
            }
        }
        return  sb.toString();
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
