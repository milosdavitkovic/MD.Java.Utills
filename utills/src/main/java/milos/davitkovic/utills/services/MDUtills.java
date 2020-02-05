package milos.davitkovic.utills.services;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

/**
 * Milos Davitkovic's Java Spring Utills interface
 *
 * @author milos.davitkovic@gmail.com
 */
public interface MDUtills {

    /**
     * *****************************
     * Arrays
     * *****************************
     */

    /**
     * Get difference of elements which exists in Set 1 but not exists in S2.
     *
     * @param set1
     * @param set2
     * @return
     */
    Set<String> getDifference(final Set<String> set1, final Set<String> set2);

    /**
     * Get intersection of elements which exists in both Sets.
     *
     * @param set1
     * @param set2
     * @return
     */
    Set<String> getIntersection(final Set<String> set1, final Set<String> set2);

    /**
     * *****************************
     * Input/Output File Management
     * *****************************
     */

    /**
     * Input/Output
     *
     * @return
     */
    String listFiles();

    String listFiles(String folderName);

    List<String> readFile(String fileName, String folderName);

    Path findFile(String fileName, String folderName);

    void writeInFile(String fileName, String folderName, String inputText);
}
