package milos.davitkovic.javautil.utills.number;

/**
 * Interface for number processing and conversion operations.
 * 
 * <p>This interface provides utility methods for handling numeric operations,
 * including string-to-number conversions and number validation. It is designed
 * to provide safe and efficient number processing capabilities.</p>
 * 
 * <p>The interface supports various numeric operations and conversions
 * with proper error handling and validation.</p>
 * 
 * @author Milos Davitkovic
 * @version 1.0
 * @since 1.0
 */
public interface NumberUtil {

    /**
     * Converts a string value to an integer.
     * 
     * <p>This method safely converts a string representation of a number to an integer.
     * It includes proper validation and error handling to ensure the conversion
     * is performed correctly.</p>
     * 
     * @param value the string value to convert to an integer
     * @return the integer value represented by the string
     * @throws NumberFormatException if the string does not contain a valid integer
     */
    int valueOf(String value);
}
