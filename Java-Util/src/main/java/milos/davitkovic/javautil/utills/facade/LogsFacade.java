package milos.davitkovic.javautil.utills.facade;

/**
 * Facade interface for log processing and analysis operations.
 * 
 * <p>This interface provides high-level methods for processing log files,
 * extracting specific data patterns, and generating clean log files for
 * analysis and debugging purposes.</p>
 * 
 * @author Milos Davitkovic
 * @version 1.0
 * @since 1.0
 */
public interface LogsFacade
{
	/**
	 * Creates a clean log file by filtering entries based on a key message.
	 * 
	 * <p>This method processes a source log file and creates a new file containing
	 * only log entries that match the specified key message. Useful for isolating
	 * specific types of log entries for analysis.</p>
	 * 
	 * @param folderName the directory containing the source log file
	 * @param sourceFileName the name of the source log file to process
	 * @param resultFileName the name of the output clean log file
	 * @param keyMessage the message pattern to filter log entries by
	 */
	void createClearLogsFile(final String folderName, final String sourceFileName, final String resultFileName, final String keyMessage);

	/**
	 * Extracts O2O (Online-to-Offline) email payload from a log entry.
	 * 
	 * <p>This method parses a log entry to extract email-related payload data
	 * for O2O operations. The extracted data is typically used for email
	 * processing and delivery tracking.</p>
	 * 
	 * @param inputLog the log entry to parse for email payload
	 * @return the extracted email payload as a JSON string, or null if not found
	 */
	String getO2OEmailPayload(final String inputLog);
	
	/**
	 * Extracts O2O (Online-to-Offline) data payload from a log entry.
	 * 
	 * <p>This method parses a log entry to extract general data payload for
	 * O2O operations. The extracted data is typically used for data processing
	 * and analytics.</p>
	 * 
	 * @param inputLog the log entry to parse for data payload
	 * @return the extracted data payload as a JSON string, or null if not found
	 */
	String getO2ODataPayload(final String inputLog);
}
