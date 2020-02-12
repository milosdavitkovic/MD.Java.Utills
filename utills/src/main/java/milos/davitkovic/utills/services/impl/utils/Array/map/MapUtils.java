package milos.davitkovic.utills.services.impl.utils.Array.map;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Miloš on 11/03/2017.
 */

@Service
public class MapUtils {

    final private Logger log = Logger.getLogger(this.getClass().getName());
    /*

        //creating Hashtable for sorting
        Map<String, Integer> olympic2012 = new HashMap<String, Integer>();

        olympic2012.put("England", 3);
        olympic2012.put("USA", 1);
        olympic2012.put("China", 2);
        olympic2012.put("Russia", 4);
        //olympic2012.put("Australia", 4); //adding duplicate value

        //printing hashtable without sorting
        System.out.println("Unsorted Map in Java : " + olympic2012);


        //Sorting Map in Java by keys using TreeMap
        Map<String, Integer> sortedMapByKeys = new TreeMap<String,Integer>();
        sortedMapByKeys.putAll(olympic2012);
        System.out.println("Sorted Map in Java by key using TreeMap : " + sortedMapByKeys);


        //Sorting Map by keys in Java using Google Collections (Guava)
        //Main benefit is you can specify any ordering like natural or toString or arbitrary
        Map<String, Integer> sortingUsingGuava = Maps.newTreeMap(Ordering.natural());
        sortingUsingGuava.putAll(olympic2012);
        System.out.println("Example to sort Map in Java using Guava : " + sortingUsingGuava);



     */

    public <K extends Comparable,V extends Comparable> Map<K,V> sortByKeysWithHashMap(Map<K,V> map) {
        Map<K, V> sorted = sortByKeys(map);
        log.info("Sorted Map in Java by key: " + sorted);
        return sorted;
    }

    /*
     * Paramterized method to sort Map e.g. HashMap or Hashtable in Java
     * throw NullPointerException if Map contains null key
     *
     * Using:
     * //sorting Map e.g. HashMap, Hashtable by keys in Java
        Map<String, Integer> sorted = sortByKeys(olympic2012);
        System.out.println("Sorted Map in Java by key: " + sorted);


        //sorting Map like Hashtable and HashMap by values in Java
        sorted = sortByValues(olympic2012);
        System.out.println("Sorted Map in Java by values: " + sorted);

Read more: http://javarevisited.blogspot.com/2012/12/how-to-sort-hashmap-java-by-key-and-value.html#ixzz4b0Jew8qa
     */
    public <K extends Comparable,V extends Comparable> Map<K,V> sortByKeys(Map<K,V> map){
        List<K> keys = new LinkedList<K>(map.keySet());
        Collections.sort(keys);

        //LinkedHashMap will keep the keys in the order they are inserted
        //which is currently sorted on natural ordering
        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
        for(K key: keys){
            sortedMap.put(key, map.get(key));
        }

        return sortedMap;
    }

    /*
     * Java method to sort Map in Java by value e.g. HashMap or Hashtable
     * throw NullPointerException if Map contains null values
     * It also sort values even if they are duplicates
     */
    public <K extends Comparable,V extends Comparable> Map<K,V> sortByValues(Map<K,V> map){
        List<Map.Entry<K,V>> entries = new LinkedList<Map.Entry<K,V>>(map.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<K,V>>() {

            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        //LinkedHashMap will keep the keys in the order they are inserted
        //which is currently sorted on natural ordering
        Map<K,V> sortedMap = new LinkedHashMap<K,V>();

        for(Map.Entry<K,V> entry: entries){
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
