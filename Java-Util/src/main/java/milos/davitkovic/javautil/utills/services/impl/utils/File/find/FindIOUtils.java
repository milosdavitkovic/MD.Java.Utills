package milos.davitkovic.javautil.utills.services.impl.utils.File.find;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Interface for file and directory search operations in the file system.
 * 
 * <p>This interface provides comprehensive functionality for finding files and
 * directories within the file system. It supports both local and system-wide
 * searches with various filtering and search criteria.</p>
 * 
 * <p>Key features include:</p>
 * <ul>
 *   <li>Finding files and directories by name</li>
 *   <li>System-wide file searches</li>
 *   <li>Folder listing and enumeration</li>
 *   <li>Path resolution and validation</li>
 *   <li>Ordinal-based file selection</li>
 * </ul>
 * 
 * @author Milos Davitkovic
 * @version 1.0
 * @since 1.0
 */
public interface FindIOUtils {

    /**
     * Finds all folders with the specified name in the file system.
     * 
     * @param folderName the name of the folder to search for
     * @return a list of folder paths as strings
     */
    List<String> findFolderList(String folderName);

    /**
     * Finds a specific folder in the system by name.
     * 
     * @param folderName the name of the folder to find
     * @return the path to the found folder, or null if not found
     */
    Path findFolderInSystem(final String folderName);

    /**
     * Finds all files with the specified name within a specific folder.
     * 
     * @param fileName the name of the file to search for
     * @param folderName the name of the folder to search in
     * @return a list of file paths found
     * @throws IOException if an I/O error occurs during the search
     */
    List<Path> findFilesInWholeSystem(final String fileName, final String folderName) throws IOException;

    /**
     * Finds a file with the specified name in the file system.
     * 
     * @param fileName the name of the file to find
     * @return the path to the found file, or null if not found
     */
    Path findFile(final String fileName);

    /**
     * Finds a specific file within a specific folder.
     * 
     * @param fileName the name of the file to find
     * @param folderName the name of the folder to search in
     * @return the path to the found file, or null if not found
     */
    Path findFileInSystem(final String fileName, final String folderName);

    /**
     * Finds a specific file path within a folder based on ordinal position.
     * 
     * @param fileName the name of the file to find
     * @param folderName the name of the folder to search in
     * @param ordinalNumberOfItem the ordinal position of the file (1-based)
     * @return the path to the found file, or null if not found
     * @throws IOException if an I/O error occurs during the search
     */
    Path findSpecificFilePathInSpecificFolder(String fileName, String folderName, Integer ordinalNumberOfItem) throws IOException;

    /**
     * Finds all files with the specified name in the entire file system.
     * 
     * @param fileName the name of the file to search for
     * @return a list of all file paths found
     * @throws IOException if an I/O error occurs during the search
     */
    List<Path> findFilesInWholeSystem(final String fileName) throws IOException;

    /**
     * Lists all files in the default folder.
     * 
     * @return a string representation of all files in the default folder
     */
    String listFilesInDefaultFolder();

    /**
     * Lists all files in a specific folder.
     * 
     * @param folderName the name of the folder to list files from
     * @return a string representation of all files in the specified folder
     */
    String listFilesInSpecificFolder(final String folderName);
}
