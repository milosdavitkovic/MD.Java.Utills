package milos.davitkovic.utills.services.impl;

import milos.davitkovic.utills.annotations.UtilClass;
import milos.davitkovic.utills.services.MDUtils;
import milos.davitkovic.utills.services.impl.utils.Array.ArrayUtils;
import milos.davitkovic.utills.services.impl.utils.Array.list.ListUtils;
import milos.davitkovic.utills.services.impl.utils.Array.set.SetUtils;
import milos.davitkovic.utills.services.impl.utils.File.FileIOUtils;
import milos.davitkovic.utills.services.impl.utils.Number.Integer.IntegerUtils;
import milos.davitkovic.utills.services.impl.utils.Number.NumberUtils;
import milos.davitkovic.utills.services.impl.utils.Time.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Milos Davitkovic's Java Spring Utils
 *
 * @author milos.davitkovic@gmail.com
 */
@UtilClass
public class DefaultMDUtils implements MDUtils {

    final static Logger logger = LoggerFactory.getLogger(DefaultMDUtils.class);

    @Resource(name = "fileIOUtils")
    private FileIOUtils fileIOUtils;
    @Resource(name = "arrayUtils")
    private ArrayUtils arrayUtils;
    @Resource(name = "timeUtils")
    private TimeUtils timeUtils;
    @Resource(name = "listUtils")
    private ListUtils listUtils;
    @Resource(name = "setUtils")
    private SetUtils setUtils;
    @Resource(name = "numberUtils")
    private NumberUtils numberUtils;
    @Resource(name = "integerUtils")
    private IntegerUtils integerUtils;

    /**
     * ***************************************************************************************
     * Array Utils
     * ***************************************************************************************
     */

    /**
     * (Generic) Convert Static Array into Collection
     *
     * @param a
     * @param c
     * @param <T>
     */
    @Override
    public <T> void arrayToCollection(T[] a, Collection<T> c) {
        arrayUtils.arrayToCollection(a, c);
    }

    /**
     * (Generic) Convert Static Array into List
     *
     * @param array
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> arrayToList(T[] array) {
        return arrayUtils.arrayToList(array);
    }

    /**
     * (Generic) Convert Static Array into Set
     *
     * @param array
     * @param <T>
     * @return
     */
    @Override
    public <T> Set<T> arrayToSet(T[] array) {
        return arrayUtils.arrayToSet(array);
    }

    /**
     * (Generic) Sort Map by Key
     *
     * @param unsortedMap
     * @param reversed
     * @return
     */
    @Override
    public <K extends Comparable<? super K>, V> Map<K, V> sortMapByKey(Map<K, V> unsortedMap, boolean reversed) {
        return arrayUtils.sortMapByKey(unsortedMap, reversed);
    }

    /**
     * (Generic) Sort map by Value
     *
     * @param unsortedMap
     * @param reversed
     * @return
     */
    @Override
    public <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(final Map<K, V> unsortedMap, boolean reversed) {
        return arrayUtils.sortMapByValue(unsortedMap, reversed);
    }

    /**
     * Sum array with lambda
     *
     * @param array
     * @return
     */
    @Override
    public int arraySum(int[] array) {
        return arrayUtils.arraySum(array);
    }

    /**
     * *****************************
     * END of Array Utils
     * *****************************
     */

    /**
     * ***************************************************************************************
     * List Utils
     * ***************************************************************************************
     */

    /**
     * (Generic) Remove duplicate elements from ArrayList implementation of List interface.
     *
     * @param inputList
     * @return ArrayList implementation of List interface without duplicated elements.
     */
    @Override
    public <T> List<T> removeDuplicates(final List<T> inputList) {
        return listUtils.removeDuplicates(inputList);
    }

    /**
     *  (Generic) Get a List without duplicate elements
     *
     * If original list is filled with {1,1,2,3,6,3,8,7}
     * returns {1,2,3,6,8,7} - returns the list without duplicates
     * @param inputList
     * @param <T>
     * @return a List without duplicates
     */
    public <T> List<T> getListWithoutDuplicates(final List<T> inputList) {
        return getListWithoutDuplicates(inputList);
    }

    /**
     * (Generic) Find duplicate elements in the list.
     *
     * For example I have list [1, 1, 2, 3, 3, 3] and as result want to have [1, 3]
     *
     * @param inputCollection
     * @param <T>
     * @return
     */
    public  <T> Set<T> getDuplicates(final Collection<T> inputCollection) {
        return listUtils.getDuplicates(inputCollection);
    }

    /**
     * (Generic) Get elements repeated specific number of times in the input list
     *
     * If original list is filled with {1,1,2,3,6,3,8,7}
     * and frequency is 1
     * returns {2,6,8,7} - returns numbers which occur only once
     * @param frequency how many times element is repeated
     * @param <T>
     * @return elements repeated frequency number of times in the input list
     */
    public <T> List<T> getElementsWithFrequency(final List<T> inputList, final int frequency) {
        return listUtils.getElementsWithFrequency(inputList, frequency);
    }

    /**
     * (Generic) Get elements repeated many number of times then provided number in the input list
     *
     * If original list is filled with {1,1,2,3,6,3,8,7}
     * and frequency is 1
     * returns {1,3} - returns only numbers which occur more times than 1
     * @param inputList
     * @param frequency
     * @param <T>
     * @return elements repeated many number of times then frequency in the input list
     */
    public <T> List<T> getElementsWithHigherFrequencyThen(final List<T> inputList, final int frequency) {
        return listUtils.getElementsWithFrequency(inputList, frequency);
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
        return listUtils.getElementsWithLowerFrequencyThen(inputList, frequency);
    }

    /**
     * (Generic) Remove NULL elements from the List
     *
     * @param array
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> removeNullElements(final List<T> array) {
        return listUtils.removeNullElements(array);
    }

    /**
     * Get Odd number from rangeStart up to rangeEnd
     *
     * @param rangeStart
     * @param rangeEnd
     * @return Odd number from rangeStart up to rangeEnd
     */
    @Override
    public List<Integer> getOddNumbersInRange(int rangeStart, int rangeEnd) {
        return listUtils.getOddNumbersInRange(rangeStart, rangeEnd);
    }

    /**
     * (Generic) Get List of Keys from provided Map
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return List of Keys from provided Map
     */
    @Override
    public <K, V> List<K> getKeyList(final Map<K, V> map) {
        return listUtils.getKeyList(map);
    }


    /**
     * (Generic) Get List of Values from provided Map
     *
     * @param map map
     * @param <K> key
     * @param <V> value
     * @return List of Values from provided Map
     */
    @Override
    public <K, V> List<V> getValueList(final Map<K, V> map) {
        return listUtils.getValueList(map);
    }

    /**
     * Get Even Numbers from the List
     *
     * @param list of numbers
     * @return List of Integer with even numbers only
     */
    public List<Integer> getEvenNumbers(final List<Integer> list) {
        return integerUtils.getEvenNumbers(list);
    }

    /**
     * Get Odd Numbers from the List
     *
     * @param list
     * @return List of Integer with odd numbers only
     */
    public List<Integer> getOddNumbers(final List<Integer> list) {
        return integerUtils.getOddNumbers(list);
    }

    /**
     * Get all number from List bigger then specified number
     *
     * @param list
     * @param number
     * @return all number from List bigger then specified number
     */
    public List<Integer> getNumbersGreaterThen(final List<Integer> list, final Integer number) {
        return integerUtils.getNumbersGreaterThen(list, number);
    }

    /**
     * Get all number smaller then specified number
     *
     * @param list
     * @param number
     * @return all number smaller then specified number
     */
    public List<Integer> getNumbersSmallerThen(final List<Integer> list, final Integer number) {
        return integerUtils.getNumbersSmallerThen(list, number);
    }

    /**
     * Get random Integer number between a range.
     *
     * @param min minimum value
     * @param max maximal value
     * @return random Integer number between a range
     */
    public Integer getRandomNumberInRange(final Integer min, final Integer max) {
        return integerUtils.getRandomNumberInRange(min, max);
    }

    /**
     * ***************************************************************************************
     * END of List Utils
     * ***************************************************************************************
     */

    /**
     * ***************************************************************************************
     * Set Utils
     * ***************************************************************************************
     */

    /**
     * Get difference elements set which exists in Set 1 but not exists in S2.
     *
     * @param set1
     * @param set2
     * @return
     */
    @Override
    public Set<String> getDifference(final Set<String> set1, final Set<String> set2) {
        Assert.notNull(set1, "set1 cannot be null!");
        Assert.notNull(set2, "set2 cannot be null!");
        return setUtils.getDifference(set1, set2);
    }

    /**
     * Get intersection elements set which exists in both Sets.
     *
     * @param set1
     * @param set2
     * @return
     */
    @Override
    public Set<String> getIntersection(final Set<String> set1, final Set<String> set2) {
        Assert.notNull(set1, "set1 cannot be null!");
        Assert.notNull(set2, "set2 cannot be null!");
        return setUtils.getIntersection(set1, set2);
    }

    /**
     * ***************************************************************************************
     * END of Set Utils
     * ***************************************************************************************
     */

    /**
     * ***************************************************************************************
     * Number Utils
     * ***************************************************************************************
     */

    /**
     * Null Safe, Double to Integer conversion
     *
     * @param inputValue
     * @return
     */
    @Override
    public Integer toInteger(final Double inputValue) {
        return numberUtils.toInteger(inputValue);
    }

    /**
     * Null Safe, Double to Long conversion
     *
     * @param inputValue
     * @return
     */
    @Override
    public Long toLong(final Double inputValue) {
        return numberUtils.toLong(inputValue);
    }

    /**
     * Null Safe, get a rounded Integer value on less number
     *
     * @param inputValue
     * @return
     */
    @Override
    public Integer roundOnLess(final Double inputValue) {
        return numberUtils.roundOnLess(inputValue);
    }

    /**
     * ***************************************************************************************
     * END of Number Utils
     * ***************************************************************************************
     */

    /**
     * ***************************************************************************************
     * Data & Time
     * ***************************************************************************************
     */

    /**
     * Get a String representation of Current DateTime, based of provided format
     *
     * @param dataTimeFormat Format options: "yyyy/MM/dd HH:mm:ss" hh - 12h format, HH - 24h format; yyyy/MM/dd HH:mm:ss; yyyy/MM/dd
     * @return String representation of Current DateTime, based of required format
     */
    @Override
    public String getCurrentDateTime(final String dataTimeFormat) {
        return timeUtils.getCurrentDateTime(dataTimeFormat);
    }

    /**
     * *****************************
     * End of Data & Time Utils
     * *****************************
     */

    /**
     * ***************************************************************************************
     * Input/Output File Management
     * ***************************************************************************************
     */

    /**
     * List all files from the project folder as a root folder
     *
     * @return
     */
    @Override
    public String listFiles() {
        try {
            return fileIOUtils.listFilesInDefaultFolder();
        } catch (IOException e) {
            logger.error("Error listing fileIOUtils.", e);
        }
        return StringUtils.EMPTY;
    }


    /**
     * List of Files from the specified Folder
     *
     * @param folderName
     * @return
     */
    @Override
    public  String listFiles(final String folderName) {
        Assert.notNull(folderName, "folderName cannot be null!");
        try {
            return fileIOUtils.listFilesInSpecificFolder(folderName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    /**
     * Read from the file under 'resource' (classpath) folder
     *
     * @param fileName
     * @param folderName
     * @return lines from file
     */
    @Override
    public  List<String> readResourceFile(final String fileName, final String folderName) throws IOException {
        Assert.notNull(fileName, "fileName cannot be null!");
        Assert.notNull(fileName, "folderName cannot be null!");

        try {
            return fileIOUtils.readResourceFile(fileName, folderName);
        } catch (IOException e) {
            throw new IOException(String.format("Cannot read a resource file [%s] from folder [%s]!", fileName, folderName));
        }
    }

    /**
     * Read from the file in the specified folder
     *
     * @param fileName
     * @param folderName
     * @return lines from file
     */
    @Override
    public List<String> readFile(final String fileName, final String folderName) throws IOException {
        Assert.notNull(fileName, "fileName cannot be null!");
        Assert.notNull(fileName, "folderName cannot be null!");
        try {
            return fileIOUtils.readFile(fileName, folderName);
        } catch (IOException e) {
            throw new IOException(String.format("Cannot read a file [%s] from folder [%s]!", fileName, folderName));
        }
    }

    @Override
    public Path findFile(final String fileName, final String folderName) throws IOException {
        Assert.notNull(fileName, "fileName cannot be null!");
        Assert.notNull(fileName, "folderName cannot be null!");
        try {
            return fileIOUtils.findSpecificFilePathInSpecificFolder(fileName, folderName, 0);
        } catch (IOException e) {
            throw new IOException(String.format("Cannot read a file [%s] from folder [%s]!", fileName, folderName));
        }
    }

    /**
     * Search for File in the whole system
     *
     * @param fileName
     * @return Path of the File if exists
     */
    @Override
    public List<Path> findFiles(final String fileName) {
        Assert.notNull(fileName, "fileName cannot be null!");
        try {
            return fileIOUtils.findFilesInWholeSystem(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.singletonList(Paths.get(StringUtils.EMPTY));
    }

    /**
     * Write in the Resource file under 'resource' (classpath) folder.
     * File is moved during the build in the target/classes/...
     * You can find this file under target/classes/
     *
     * @param fileName
     * @param folderName
     * @param input
     */
    @Override
    public <T> void writeInResourceFile(final String fileName, final String folderName, final Collection<T> input) throws IOException {
        Assert.notNull(fileName, "fileName cannot be null!");
        Assert.notNull(folderName, "folderName cannot be null!");
        Assert.notNull(input, "input cannot be null!");

        final List<String> inputText = listUtils.convertToStringList(input);

        try {
            fileIOUtils.writeInResourceFile(fileName, folderName, inputText);
        } catch (IOException e) {
            throw new IOException(String.format("Cannot write in a file [%s] from folder [%s]!", fileName, folderName));
        }
    }

    /**
     * Write in the specified File
     *
     * @param input content for writing in the file
     */
    @Override
    public <T> void writeInFile(final String fileName, final String folderName, final Collection<T> input) {
        Assert.notNull(fileName, "fileName cannot be null!");
        Assert.notNull(folderName, "folderName cannot be null!");
        Assert.notNull(input, "input cannot be null!");

        final List<String> inputText = listUtils.convertToStringList(input);

        try {
            fileIOUtils.writeInFile(fileName, folderName, inputText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write in the specified File
     *
     * @param filePath  path of the File
     * @param inputText content for writing in the file
     */
    @Override
    public void writeInFile(final Path filePath, final List<String> inputText) {
        Assert.notNull(filePath, "filePath cannot be null!");
        Assert.notNull(inputText, "inputText cannot be null!");

        try {
            fileIOUtils.writeInFile(filePath, inputText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * *****************************
     * END of Input/Output File Management
     * *****************************
     */
}
