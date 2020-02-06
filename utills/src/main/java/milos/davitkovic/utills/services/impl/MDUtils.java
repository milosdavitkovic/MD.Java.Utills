package milos.davitkovic.utills.services.impl;

import milos.davitkovic.utills.services.impl.utils.Array.ArrayFn;
import milos.davitkovic.utills.services.impl.utils.File.FileFn;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Resource
    private static FileFn files;
    @Resource
    private static ArrayFn arrays;

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
    public static Set<String> getDifference(final Set<String> set1, final Set<String> set2) {
        return arrays.getDifference(set1, set2);
    }

    /**
     * Get intersection elements set which exists in both Sets.
     *
     * @param set1
     * @param set2
     * @return
     */
    public static Set<String> getIntersection(final Set<String> set1, final Set<String> set2) {
        return arrays.getIntersection(set1, set2);
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
    public String listFiles() {
        try {
            return files.listFilesInDefaultFolder();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }


    public static String listFiles(final String folderName) {
        try {
            return files.listFilesInSpecificFolder(folderName);
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
     * @return
     */
    public static List<String> readResourceFile(final String fileName, final String folderName) {
        try {
            return files.readResourceFile(fileName, folderName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    public static Path findFile(final String fileName, final String folderName) {
        try {
            return files.findSpecificFilePathInSpecificFolder(fileName, folderName, 0);
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
    public static List<Path> findFiles(final String fileName) {
        try {
            return files.findFilesInWholeSystem(fileName);
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
    public static void writeInResourceFile(final String fileName, final String folderName, final List<String> inputText) {
        try {
            files.writeInResourceFile(fileName, folderName, inputText);
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
    public static void writeInFile(final Path filePath, final List<String> inputText) {
        try {
            files.writeInFile(filePath, inputText);
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
