package milos.davitkovic.javautil.utills.services.impex;

import java.io.IOException;

/**
 * Service interface for creating and managing IMPEX files for data import operations.
 * 
 * <p>This interface provides functionality for generating IMPEX files that are used
 * for importing data into systems like SAP Commerce. IMPEX files follow a specific
 * format that includes headers, primary key definitions, and data records.</p>
 * 
 * <p>The service supports various IMPEX operations including:</p>
 * <ul>
 *   <li>Creating IMPEX files with primary key definitions</li>
 *   <li>Adding custom content to IMPEX entries</li>
 *   <li>Formatting data according to IMPEX specifications</li>
 * </ul>
 * 
 * @author Milos Davitkovic
 * @version 1.0
 * @since 1.0
 */
public interface ImpexFilesCreationService {

    /**
     * Creates an IMPEX file with primary key definitions for data import operations.
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
    void createUpdateImpexWithPKs(String folderName, String sourceFileName, String resultFileName, String header) throws IOException;

    /**
     * Creates an IMPEX file with primary keys and additional line content.
     * 
     * <p>This method generates an IMPEX file with primary keys and allows for additional
     * content to be appended to each line. Useful for complex data transformation scenarios
     * where extra data needs to be included in the import file.</p>
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
