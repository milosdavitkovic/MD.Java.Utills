package milos.davitkovic.javautil.utills.facade;

import java.io.IOException;

/**
 * Facade interface for managing item types operations including file comparison,
 * duplicate detection, and IMPEX file creation.
 * 
 * <p>This interface provides a high-level abstraction for operations related to
 * product codes, file processing, and data transformation tasks commonly used
 * in e-commerce and data management scenarios.</p>
 * 
 * @author Milos Davitkovic
 * @version 1.0
 * @since 1.0
 */
public interface ItemTypesFacade {

    /**
     * Compares two files containing product codes and generates a result file with differences.
     * 
     * <p>This method reads two source files from the specified folder, compares their contents,
     * and writes the comparison results to a new file. The comparison is typically used for
     * identifying differences between product code lists.</p>
     * 
     * @param folderName the directory containing the source files
     * @param sourceFileName1 the name of the first source file to compare
     * @param sourceFileName2 the name of the second source file to compare
     * @param resultFileName the name of the output file containing comparison results
     * @throws IOException if an I/O error occurs during file operations
     */
    void compare2Files(String folderName, String sourceFileName1, String sourceFileName2, String resultFileName) throws IOException;

    /**
     * Identifies and extracts duplicate entries from a source file.
     * 
     * <p>This method processes a source file to find duplicate entries and writes
     * the duplicates to a result file. Useful for data cleaning and validation tasks.</p>
     * 
     * @param folderName the directory containing the source file
     * @param sourceFileName the name of the source file to analyze for duplicates
     * @param resultFileName the name of the output file containing duplicate entries
     * @throws IOException if an I/O error occurs during file operations
     */
    void getDuplicates(final String folderName, final String sourceFileName, final String resultFileName) throws IOException;

    /**
     * Creates an IMPEX file with primary keys for data import operations.
     * 
     * <p>This method generates an IMPEX file suitable for importing data into systems
     * like SAP Commerce. The generated file includes primary key definitions and
     * follows the IMPEX format specifications.</p>
     * 
     * @param folderName the directory where the source file is located
     * @param sourceFileName the name of the source file containing data to process
     * @param resultFileName the name of the output IMPEX file
     * @param header the IMPEX header defining the data structure and primary keys
     * @throws IOException if an I/O error occurs during file operations
     */
    void createUpdateImpexWithPKs(final String folderName, final String sourceFileName, final String resultFileName, final String header) throws IOException;

    /**
     * Creates an IMPEX file with primary keys and additional line content.
     * 
     * <p>This method generates an IMPEX file with primary keys and allows for additional
     * content to be appended to each line. Useful for complex data transformation scenarios.</p>
     * 
     * @param folderName the directory where the source file is located
     * @param sourceFileName the name of the source file containing data to process
     * @param resultFileName the name of the output IMPEX file
     * @param header the IMPEX header defining the data structure and primary keys
     * @param lineAddition additional content to append to each line in the IMPEX file
     * @throws IOException if an I/O error occurs during file operations
     */
    void createUpdateImpexWithPKs(final String folderName, final String sourceFileName, final String resultFileName, final String header, final String lineAddition) throws IOException;


}
