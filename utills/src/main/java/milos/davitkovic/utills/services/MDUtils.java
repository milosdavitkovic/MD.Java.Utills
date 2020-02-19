package milos.davitkovic.utills.services;

import milos.davitkovic.utills.annotations.Interface;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Milos Davitkovic's Java Spring Utils
 *
 * @author milos.davitkovic@gmail.com
 */
@Interface
public interface MDUtils {
    /**
     * (Generic) Convert Static Array into Collection
     *
     * @param a
     * @param c
     * @param <T>
     */
    <T> void arrayToCollection(T[] a, Collection<T> c);

    /**
     * (Generic) Convert Static Array into List
     *
     * @param array
     * @param <T>
     * @return
     */
    <T> List<T> arrayToList(T[] array);

    /**
     * (Generic) Convert Static Array into Set
     *
     * @param array
     * @param <T>
     * @return
     */
    <T> Set<T> arrayToSet(T[] array);

    /**
     * (Generic) Sort Map by Key
     *
     * @param unsortedMap
     * @param reversed
     * @return
     */
    <K extends Comparable<? super K>, V> Map<K, V> sortMapByKey(Map<K, V> unsortedMap, boolean reversed);

    /**
     * (Generic) Sort map by Value
     *
     * @param unsortedMap
     * @param reversed
     * @return
     */
    <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(Map<K, V> unsortedMap, boolean reversed);

    /**
     * Sum array with lambda
     *
     * @param array
     * @return
     */
    int arraySum(int[] array);

    /**
     * (Generic) Remove duplicate elements from ArrayList implementation of List interface.
     * @param inputList
     * @return ArrayList implementation of List interface without duplicated elements.
     */
    <T> List<T> removeDuplicates(List<T> inputList);

    /**
     * (Generic) Remove NULL elements from the List
     *
     * @param array
     * @param <T>
     * @return
     */
    <T> List<T> removeNullElements(List<T> array);

    /**
     * Get Odd number from rangeStart up to rangeEnd
     *
     * @param rangeStart
     * @param rangeEnd
     * @return
     */
    List<Integer> getOddNumbersInRange(int rangeStart, int rangeEnd);

    /**
     * (Generic) Get List of Keys from provided Map
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    <K, V> List<K> getKeyList(Map<K, V> map);

    /**
     * (Generic) Get List of Values from provided Map
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    <K, V> List<V> getValueList(Map<K, V> map);

    /**
     * Get difference elements set which exists in Set 1 but not exists in S2.
     *
     * @param set1
     * @param set2
     * @return
     */
    Set<String> getDifference(Set<String> set1, Set<String> set2);

    /**
     * Get intersection elements set which exists in both Sets.
     *
     * @param set1
     * @param set2
     * @return
     */
    Set<String> getIntersection(Set<String> set1, Set<String> set2);

    /**
     * Null Safe, Double to Integer conversion
     *
     * @param inputValue
     * @return
     */
    Integer toInteger(Double inputValue);

    /**
     * (Generic) Find duplicate elements in the list.
     *
     * For example I have list [1, 1, 2, 3, 3, 3] and as result want to have [1, 3]
     *
     * @param inputCollection
     * @param <T>
     * @return
     */
    <T> Set<T> getDuplicates(final Collection<T> inputCollection);

    /**
     * Null Safe, Double to Long conversion
     *
     * @param inputValue
     * @return
     */
    Long toLong(Double inputValue);

    /**
     * Null Safe, get a rounded Integer value on less number
     *
     * @param inputValue
     * @return
     */
    Integer roundOnLess(Double inputValue);

    /**
     * Get a String representation of Current DateTime, based of provided format
     *
     * @param dataTimeFormat Format options: "yyyy/MM/dd HH:mm:ss" hh - 12h format, HH - 24h format; yyyy/MM/dd HH:mm:ss; yyyy/MM/dd
     * @return String representation of Current DateTime, based of required format
     */
    String getCurrentDateTime(String dataTimeFormat);

    /**
     * List all files from the project folder as a root folder
     *
     * @return
     */
    String listFiles();

    /**
     * List of Files from the specified Folder
     *
     * @param folderName
     * @return
     */
    String listFiles(String folderName);

    /**
     * Read from the file under 'resource' (classpath) folder
     *
     * @param fileName
     * @param folderName
     * @return lines from file
     */
    List<String> readResourceFile(String fileName, String folderName) throws IOException;

    /**
     * Read from the file in the specified folder
     *
     * @param fileName
     * @param folderName
     * @return lines from file
     */
    List<String> readFile(String fileName, String folderName) throws IOException;

    Path findFile(String fileName, String folderName) throws IOException;

    /**
     * Search for File in the whole system
     *
     * @param fileName
     * @return Path of the File if exists
     */
    List<Path> findFiles(String fileName);

    /**
     * Write in the Resource file under 'resource' (classpath) folder.
     * File is moved during the build in the target/classes/...
     * You can find this file under target/classes/
     *
     * @param fileName
     * @param folderName
     * @param input
     */
    <T> void writeInResourceFile(final String fileName, final String folderName, final Collection<T> input) throws IOException;

    /**
     * Write in the specified File
     *
     * @param input content for writing in the file
     */
    <T> void writeInFile(String fileName, String folderName, Collection<T> input);

    /**
     * Write in the specified File
     *
     * @param filePath  path of the File
     * @param inputText content for writing in the file
     */
    void writeInFile(Path filePath, List<String> inputText);
}
