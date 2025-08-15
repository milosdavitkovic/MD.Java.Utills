package milos.davitkovic.javautil.utills.services.logs;

/**
 * Service interface for log processing and analysis operations.
 * 
 * <p>This interface defines the contract for log file processing, including
 * filtering, parsing, and extracting specific data patterns from log entries.
 * It provides core functionality for log analysis and data extraction tasks.</p>
 * 
 * @author Milos Davitkovic
 * @version 1.0
 * @since 1.0
 */
public interface LogsService {

    /**
     * Creates a filtered log file based on a specific key message pattern.
     * 
     * <p>This method reads a source log file and creates a new file containing
     * only entries that match the specified key message. The filtering is useful
     * for isolating specific types of log entries for focused analysis.</p>
     * 
     * @param folderName the directory containing the source log file
     * @param sourceFileName the name of the source log file to process
     * @param resultFileName the name of the output filtered log file
     * @param keyMessage the message pattern to filter log entries by
     */
    void createClearLogsFile(final String folderName, final String sourceFileName, final String resultFileName, final String keyMessage);

    /**
     * Extracts email payload data from O2O log entries.
     * 
     * <p>This method parses log entries to extract email-related payload data
     * for Online-to-Offline operations. The extracted data is typically used
     * for email processing and delivery tracking.</p>
     * 
     * @param inputLog the log entry to parse for email payload
     * @return the extracted email payload as a JSON string, or null if not found
     */
    String getO2OEmailPayload(final String inputLog);
    
    /**
     * Extracts general data payload from O2O log entries.
     * 
     * <p>This method parses log entries to extract general data payload for
     * Online-to-Offline operations. The extracted data is typically used for
     * data processing and analytics.</p>
     * 
     * @param inputLog the log entry to parse for data payload
     * @return the extracted data payload as a JSON string, or null if not found
     */
    String getO2ODataPayload(final String inputLog);
}
