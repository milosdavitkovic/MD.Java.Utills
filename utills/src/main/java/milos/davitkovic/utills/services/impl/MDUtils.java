package milos.davitkovic.utills.services.impl;

import milos.davitkovic.utills.services.impl.utils.Array.ArrayUtils;
import milos.davitkovic.utills.services.impl.utils.Array.ListUtils;
import milos.davitkovic.utills.services.impl.utils.File.FileIOUtils;
import milos.davitkovic.utills.services.impl.utils.Time.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Milos Davitkovic's Java Spring Utils
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class MDUtils {

    final static Logger logger = LoggerFactory.getLogger(MDUtils.class);

    @Resource(name = "fileIOUtils")
    private FileIOUtils fileIOUtils;
    @Resource(name = "arrayUtils")
    private ArrayUtils arrayUtils;
    @Resource(name = "timeUtils")
    private TimeUtils timeUtils;
    @Resource(name = "listUtils")
    private ListUtils listUtils;

    /**
     * *****************************
     * Arrays
     * *****************************
     */

    /**
     * Get difference elements set which exists in Set 1 but not exists in S2.
     *
     * @param set1
     * @param set2
     * @return
     */
    public Set<String> getDifference(final Set<String> set1, final Set<String> set2) {
        Assert.notNull(set1, "set1 cannot be null!");
        Assert.notNull(set2, "set2 cannot be null!");
        return arrayUtils.getDifference(set1, set2);
    }

    /**
     * Get intersection elements set which exists in both Sets.
     *
     * @param set1
     * @param set2
     * @return
     */
    public Set<String> getIntersection(final Set<String> set1, final Set<String> set2) {
        Assert.notNull(set1, "set1 cannot be null!");
        Assert.notNull(set2, "set2 cannot be null!");
        return arrayUtils.getIntersection(set1, set2);
    }

    /**
     * *****************************
     * END of Arrays
     * *****************************
     */


    /**
     * *****************************
     * Input/Output File Management
     * *****************************
     */

    /**
     * List all files from the project folder as a root folder
     *
     * @return
     */
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
    public  List<String> readResourceFile(final String fileName, final String folderName) {
        try {
            return fileIOUtils.readResourceFile(fileName, folderName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * Read from the file in the specified folder
     *
     * @param fileName
     * @param folderName
     * @return lines from file
     */
    public List<String> readFile(final String fileName, final String folderName) {
        Assert.notNull(fileName, "fileName cannot be null!");
        Assert.notNull(fileName, "folderName cannot be null!");
        try {
            return fileIOUtils.readFile(fileName, folderName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    public Path findFile(final String fileName, final String folderName) {
        Assert.notNull(fileName, "fileName cannot be null!");
        Assert.notNull(fileName, "folderName cannot be null!");
        try {
            return fileIOUtils.findSpecificFilePathInSpecificFolder(fileName, folderName, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Paths.get(StringUtils.EMPTY);
    }

    /**
     * Search for File in the whole system
     *
     * @param fileName
     * @return Path of the File if exists
     */
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
     * @param inputText
     */
    public void writeInResourceFile(final String fileName, final String folderName, final List<String> inputText) {
        try {
            fileIOUtils.writeInResourceFile(fileName, folderName, inputText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Write in the specified File
     *
     * @param inputText content for writing in the file
     */
    public void writeInFile(final String fileName, final String folderName, final List<String> inputText) {
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
    public void writeInFile(final Path filePath, final List<String> inputText) {
        Assert.notNull(filePath, "filePath cannot be null!");
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

    /**
     * *****************************
     * Data & Time
     * *****************************
     */

    /**
     * Get a String representation of Current DateTime, based of required format
     *
     * @param dataTimeFormat Format options: "yyyy/MM/dd HH:mm:ss" hh - 12h format, HH - 24h format; yyyy/MM/dd HH:mm:ss; yyyy/MM/dd
     * @return String representation of Current DateTime, based of required format
     */
    public String getCurrentDateTime(final String dataTimeFormat) {
        return timeUtils.getCurrentDateTime(dataTimeFormat);
    }

    /**
     * *****************************
     * End of Data & Time Utils
     * *****************************
     */


    /**
     * *****************************
     * Array Utils
     * *****************************
     */

    /**
     * (Generic) Convert Static Array into Collection
     *
     * @param a
     * @param c
     * @param <T>
     */
    public <T> void arrayToCollection(T[] a, Collection<T> c) {
        arrayUtils.arrayToCollection(a, c);
    }

    /**
     * 
     *
     * @param array
     * @param <T>
     * @return
     */
    public <T> List<T> arrayToList(T[] array) {
        return arrayUtils.arrayToList(array);
    }

    /**
     * *****************************
     * END of Array Utils
     * *****************************
     */

    /**
     * *****************************
     * List Utils
     * *****************************
     */

    /**
     * Remove duplicate elements from ArrayList implementation of List interface.
     * @param inputList
     * @return ArrayList implementation of List interface without duplicated elements.
     */
    public List<?> removeDuplicates(final List<?> inputList) {
        return listUtils.removeDuplicates(inputList);
    }

    /**
     * *****************************
     * END of List Utils
     * *****************************
     */
}
