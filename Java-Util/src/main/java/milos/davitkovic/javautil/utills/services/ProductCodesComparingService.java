package milos.davitkovic.javautil.utills.services;

import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Service interface for comparing product codes across different files.
 * 
 * <p>This interface provides functionality for analyzing and comparing product codes
 * stored in different files. It supports operations to find intersections, differences,
 * and unique elements between two lists of product codes.</p>
 * 
 * <p>The service is designed to handle large datasets efficiently and provides
 * detailed comparison results for data analysis and validation purposes.</p>
 * 
 * @author Milos Davitkovic
 * @version 1.0
 * @since 1.0
 */
public interface ProductCodesComparingService {

    /**
     * Compares two files containing product codes and generates detailed comparison results.
     * 
     * <p>This method performs a comprehensive comparison between two files containing
     * product codes. It extracts and analyzes:</p>
     * <ul>
     *   <li>Elements that exist in both files (intersection)</li>
     *   <li>Elements unique to the first file</li>
     *   <li>Elements unique to the second file</li>
     * </ul>
     * 
     * <p>The comparison results are written to a result file for further analysis.</p>
     * 
     * @param folderName the directory containing the source files
     * @param sourceFileName1 the name of the first source file to compare
     * @param sourceFileName2 the name of the second source file to compare
     * @param resultFileName the name of the output file containing comparison results
     * @throws IOException if an I/O error occurs during file operations
     */
    void compare2Files(String folderName, String sourceFileName1, String sourceFileName2, String resultFileName) throws IOException;
}
