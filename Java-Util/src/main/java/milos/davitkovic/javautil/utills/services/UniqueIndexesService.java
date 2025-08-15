package milos.davitkovic.javautil.utills.services;

import java.io.IOException;

/**
 * Service interface for identifying and managing duplicate entries in data files.
 * 
 * <p>This interface provides functionality for detecting duplicate entries within
 * a single file or dataset. It is designed to help with data cleaning and validation
 * by identifying redundant or duplicate records that may cause issues in data processing.</p>
 * 
 * <p>The service is optimized for handling large datasets and provides efficient
 * duplicate detection algorithms.</p>
 * 
 * @author Milos Davitkovic
 * @version 1.0
 * @since 1.0
 */
public interface UniqueIndexesService {

    /**
     * Identifies and extracts duplicate entries from a source file.
     * 
     * <p>This method processes a source file to find duplicate entries and writes
     * them to a result file. The duplicate detection is based on exact matching
     * of entries and is useful for data cleaning and validation tasks.</p>
     * 
     * @param folderName the directory containing the source file
     * @param sourceFileName the name of the source file to analyze for duplicates
     * @param resultFileName the name of the output file containing duplicate entries
     * @throws IOException if an I/O error occurs during file operations
     */
    void getDuplicates(final String folderName, final String sourceFileName, final String resultFileName) throws IOException;
}
