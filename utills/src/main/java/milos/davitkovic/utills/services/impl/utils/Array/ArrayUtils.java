package milos.davitkovic.utills.services.impl.utils.Array;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Miloš Davitkovic
 * <p>
 * List<E>:
 * An ordered collection (also known as a sequence). The user of this interface has precise control over where
 * in the list each element is inserted. The user can access elements by their integer index
 * (position in the list), and search for elements in the list.
 * <p>
 * Set<E>:
 * A collection that contains no duplicate elements. More formally, sets contain no pair of elements e1 and e2
 * such that e1.equals(e2), and at most one null element. As implied by its name, this interface models the
 * mathematical set abstraction.
 * <p>
 * Map<K,V>:
 * Map tmpMap = new HashMap();
 * The Map interface maps UNIQUE keys to values. A key is an object that you use to retrieve a value at a later date.
 * A map cannot contain duplicate keys; each key can map to at most one value.
 * The order of a map is defined as the order in which the iterators on the map's collection views return their elements.
 * Some map implementations,
 * like the TreeMap class, make specific guarantees as to their order; others, like the HashMap class, do not.
 * <p>
 * TreeMap<K,V>:
 * TreeMap tmpTreeMap = new TreeMap();
 * ****************************************
 * The main difference between them is that HashMap is an unordered collection while TreeMap is sorted in the
 * ascending order of its keys. TreeMap is unsynchronized collection class which
 * means it is not suitable for thread-safe operations until unless synchronized explicitly.
 * <p>
 * A TreeMap provides an efficient means of storing key/value pairs in sorted order, and allows rapid retrieval.
 * You should note that, unlike a hash map, a tree map guarantees that its elements will be sorted in an ascending key order.
 * <p>
 * The map is sorted according to the natural ordering of its keys, or by a Comparator provided at map creation time,
 * depending on which constructor is used.
 * ordering maintained by a tree map, like any sorted map, and whether or not an explicit comparator is provided, must be
 * consistent with equals if this sorted map is to correctly implement the Map interface.
 * <p>
 * Doubles shouldn't be used in HashMaps because they are difficult to compare for equality.
 * The double values are generated a bunch of math, so the likelihood of a duplicate value is extremely low.
 */

@Service
public class ArrayUtils {
    /**
     * 1.
     * Object[] oa = new Object[100];
     * Collection<Object> co = new ArrayList<Object>();
     * <p>
     * // T inferred to be Object
     * fromArrayToCollection(oa, co);
     * <p>
     * 2.
     * String[] sa = new String[100];
     * Collection<String> cs = new ArrayList<String>();
     * <p>
     * // T inferred to be String
     * fromArrayToCollection(sa, cs);
     * <p>
     * 3.
     * String[] sa = new String[100];
     * Collection<Object> co = new ArrayList<Object>();
     * // T inferred to be Object
     * fromArrayToCollection(sa, co);
     * <p>
     * 4.
     * Integer[] ia = new Integer[100];
     * Collection<Number> cn = new ArrayList<Number>();
     * // T inferred to be Number
     * fromArrayToCollection(ia, cn);
     * <p>
     * 5.
     * Float[] fa = new Float[100];
     * Collection<Number> cn = new ArrayList<Number>();
     * // T inferred to be Number
     * fromArrayToCollection(fa, cn);
     * <p>
     * 6.
     * Number[] na = new Number[100];
     * Collection<Number> cn = new ArrayList<Number>();
     * // T inferred to be Number
     * fromArrayToCollection(na, cn);
     * <p>
     * 7.
     * Number[] na = new Number[100];
     * Collection<Object> co = new ArrayList<Object>();
     * // T inferred to be Object
     * fromArrayToCollection(na, co);
     * <p>
     * 8.
     * Number[] na = new Number[100];
     * Collection<String> cs = new ArrayList<String>();
     * // !!! compile-time error !!!
     * fromArrayToCollection(na, cs);
     *
     * @param a
     * @param c
     */
    public <T> void arrayToCollection(T[] a, Collection<T> c) {
        for (T o : a) {
            c.add(o);
        }
    }

    /**
     * Generic method printArray to print Array elements on Console
     * // Create arrays of Integer, Double and Character
     * Integer[] intArray = { 1, 2, 3, 4, 5 };
     * Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
     * Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };
     * <p>
     * System.out.println("Array integerArray contains:");
     * printArray(intArray);   // pass an Integer array
     * <p>
     * System.out.println("\nArray doubleArray contains:");
     * printArray(doubleArray);   // pass a Double array
     * <p>
     * System.out.println("\nArray characterArray contains:");
     * printArray(charArray);   // pass a Character array
     *
     * @param inputArray
     */
    public <E> void printArray(E[] inputArray) {
        // Display array elements
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }


    /**
     * Determines the largest of three Comparable objects
     * System.out.printf("Max of %d, %d and %d is %d\n\n",
     * 3, 4, 5, maximum( 3, 4, 5 ));
     * <p>
     * System.out.printf("Max of %.1f,%.1f and %.1f is %.1f\n\n",
     * 6.6, 8.8, 7.7, maximum( 6.6, 8.8, 7.7 ));
     * <p>
     * System.out.printf("Max of %s, %s and %s is %s\n","pear",
     * "apple", "orange", maximum("pear", "apple", "orange"));
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public <T extends Comparable<T>> T maximum(T x, T y, T z) {
        T max = x;   // assume x is initially the largest

        if (y.compareTo(max) > 0) {
            max = y;   // y is the largest so far
        }

        if (z.compareTo(max) > 0) {
            max = z;   // z is the largest now
        }
        return max;   // returns the largest object
    }

    /**
     * Java 8
     *
     * @param iterator
     * @return
     */
    public <T> ArrayList<T> IteratorToArrayList(final Iterator<T> iterator) {
        return StreamSupport
                .stream(
                        Spliterators
                                .spliteratorUnknownSize(iterator, Spliterator.ORDERED), false)
                .collect(
                        Collectors.toCollection(ArrayList::new)
                );
    }

    public <E> List<E> IterableToList(Iterable<E> iterable) {
        if (iterable instanceof List) {
            return (List<E>) iterable;
        }
        ArrayList<E> list = new ArrayList<E>();
        if (iterable != null) {
            for (E e : iterable) {
                list.add(e);
            }
        }
        return list;
    }


    /**
     * (Generic) Convert static Array onto List
     *
     * @param array
     * @param <T>
     * @return
     */
    public <T> List<T> arrayToList(T[] array) {
        return Arrays.stream(array).collect(Collectors.toList());
    }

    public <T> Set<T> arrayToSet(T[] array) {
        return Arrays.stream(array).collect(Collectors.toSet());
    }

    public <T> T[] joinArray(T[]... arrays) {
        int length = 0;
        for (T[] array : arrays) {
            length += array.length;
        }

        //T[] result = new T[length];
        final T[] result = (T[]) Array.newInstance(arrays[0].getClass().getComponentType(), length);

        int offset = 0;
        for (T[] array : arrays) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }

        return result;
    }

    public String mapFilterByValue(Map<Integer, String> map, String wantedString) {
        String result = "";
        //Map -> Stream -> Filter -> String
        result = map.entrySet().stream()
                .filter(tmpMap -> wantedString.equals(tmpMap.getValue()))
                .map(tmpMap -> tmpMap.getValue())
                .collect(Collectors.joining());

        System.out.println("With Java 8 : " + result);
        return result;
    }

    public Map<Integer, String> MapFilterByKey(Map<Integer, String> map, Integer key) {
        //Map -> Stream -> Filter -> String
        Map<Integer, String> collect = map.entrySet().stream()
                .filter(tmpMap -> tmpMap.getKey() == key)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

        System.out.println(collect);
        return collect;
    }

    /**
     * (Generic) Sort Map with key with type String
     *
     * @param unsortedMap
     * @param
     * @return
     */
    public <K extends Comparable<? super K>, V> Map<K, V> sortMapByKey(final Map<K, V> unsortedMap, final boolean reversed) {
        final Map<K, V> result = new LinkedHashMap<>();

        if(reversed) {
            unsortedMap.entrySet().stream()
                    .sorted(Map.Entry.<K, V>comparingByKey().reversed())
                    .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        } else {
            //sort by key, a,b,c..., or 1, 2, 3... and put it into the "result" map
            unsortedMap.entrySet().stream()
                    .sorted(Map.Entry.<K, V>comparingByKey())
                    .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        }

        return result;
    }

    public <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(final Map<K, V> unsortedMap, final boolean reversed) {
        final Map<K, V> result = new LinkedHashMap<>();

        if(reversed) {
            //sort by value, and reserve, 10,9,8,7,6...
            unsortedMap.entrySet().stream()
                    .sorted(Map.Entry.<K, V>comparingByValue().reversed())
                    .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        } else {
            //sort by value, 1, 2, 3, 4 ...
            unsortedMap.entrySet().stream()
                    .sorted(Map.Entry.<K, V>comparingByValue())
                    .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        }
        return result;
    }

    /**
     * Map<String, Integer> unsortMap = new HashMap<>();
     * unsortMap.put("z", 10);
     * unsortMap.put("b", 5);
     * ...
     * <p>
     * System.out.println("Sort By Key...");
     * Map<String, Integer> resultKey = compareByKey(unsortMap);
     * System.out.println(resultKey);
     *
     * @param map
     * @return
     */
    //Reference from java.util.Map source code, try dig inside, many generic examples.
    public <K, V extends Comparable<? super V>> Map<K, V> compareByValue(Map<K, V> map) {

        Map<K, V> result = new LinkedHashMap<>();

        Stream<Map.Entry<K, V>> mapInStream = map.entrySet().stream();

        mapInStream.sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));

        return result;

    }

    /**
     * Map<String, Integer> unsortMap = new HashMap<>();
     * unsortMap.put("z", 10);
     * unsortMap.put("b", 5);
     * ...
     * <p>
     * System.out.println("Sort By Value...");
     * Map<String, Integer> resultValue = compareByValue(unsortMap);
     * System.out.println(resultValue);
     *
     * @param map
     * @return
     */
    public <K extends Comparable<? super K>, V> Map<K, V> compareByKey(Map<K, V> map) {

        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> mapInStream = map.entrySet().stream();

        mapInStream.sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));

        return result;

    }

    /**
     * Some example, with these 2 lists:
     * <p>
     * List<Integer> a = Arrays.asList(1, 2, 3);
     * List<String> b = Arrays.asList("a", "b", "c", "d");
     * Loop within min size of a and b:
     * <p>
     * loop(a, b, i -> i < Math.min(a.size(), b.size()), (x, y) -> {
     * System.out.println(x +  " -> " + y);
     * });
     * Output:
     * <p>
     * 1 -> a
     * 2 -> b
     * 3 -> c
     * Loop within max size of a and b (elements in shorter list will be cycled):
     * <p>
     * loop(a, b, i -> i < Math.max(a.size(), b.size()), (x, y) -> {
     * System.out.println(x +  " -> " + y);
     * });
     * Output:
     * <p>
     * 1 -> a
     * 2 -> b
     * 3 -> c
     * 1 -> d
     * Loop n times ((elements will be cycled if n is bigger than sizes of lists)):
     * <p>
     * loop(a, b, i -> i < 5, (x, y) -> {
     * System.out.println(x +  " -> " + y);
     * });
     * Output:
     * <p>
     * 1 -> a
     * 2 -> b
     * 3 -> c
     * 1 -> d
     * 2 -> a
     * Loop forever:
     * <p>
     * loop(a, b, i -> true, (x, y) -> {
     * System.out.println(x +  " -> " + y);
     * });
     * Apply to your situation:
     * <p>
     * loop(list1, list2, i -> i < Math.min(a.size(), b.size()), (e1, e2) -> {
     * doStuff(e1);
     * doStuff(e2);
     * });
     *
     * @param a
     * @param b
     * @param intPredicate
     * @param biConsumer
     */
    //parallel loop
    public <A, B> void iterateParallelOfTwoLists(Collection<A> a, Collection<B> b, IntPredicate intPredicate, BiConsumer<A, B> biConsumer) {
        Iterator<A> ait = a.iterator();
        Iterator<B> bit = b.iterator();
        if (ait.hasNext() && bit.hasNext()) {
            for (int i = 0; intPredicate.test(i); i++) {
                if (!ait.hasNext()) {
                    ait = a.iterator();
                }
                if (!bit.hasNext()) {
                    bit = b.iterator();
                }
                biConsumer.accept(ait.next(), bit.next());
            }
        }
    }

    // ##########################################################################################################

    //nest loop
    public <A, B> void loopNest(Collection<A> a, Collection<B> b, BiConsumer<A, B> biConsumer) {
        for (A ai : a) {
            for (B bi : b) {
                biConsumer.accept(ai, bi);
            }
        }
    }

    // ##########################################################################################################

    /**
     * If all consignments have same status return that status, if not return null
     */
//	public String isSameStatus(final Set<String> consignments) {
//		final List<String> consignmentList = new ArrayList<>(consignments);
//		final Predicate<String> p1 = con -> con.getStatus().equals(consignmentList.get(0).getStatus());
//        if(consignmentList.stream().allMatch(p1))
//            return consignmentList.get(0).getStatus();
//        else
//            return null;
//	}

    // ##########################################################################################################

    /**
     * If all consignments have same status return true, if not return false
     */
//	public boolean isSameStatus(Set<ConsignmentModel> consignments) {
//		List<ConsignmentModel> consignmentList = new ArrayList<>(consignments);
//		final Predicate<ConsignmentModel> p1 = con -> con.getStatus().equals(consignmentList.get(0).getStatus());
//        return consignmentList.stream().allMatch(p1) ? true : false;
//    }


    // ##########################################################################################################

    public int arraySum(int[] array) {
        return Arrays.stream(array)
                .boxed()
                .mapToInt(Integer::valueOf)
                .sum();
    }

// ##########################################################################################################

    // Complete the findNumber function below.
    private String findNumber(List<Integer> arr, int k) {
        final String YES = "YES";
        final String NO = "NO";

        if(arr == null) {
            return NO;
        }

        return arr.contains(k) ? YES : NO;
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


}
