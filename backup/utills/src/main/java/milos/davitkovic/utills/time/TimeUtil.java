package sapmarketing.docstore.core.util.time;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import sapmarketing.docstore.core.configuration.annotation.UtilClass;
import sapmarketing.docstore.core.util.LoggingUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Date;

/**
 * Utility class for time-related operations.
 */
@Slf4j
@UtilClass
public class TimeUtil {

    private static final String UTC = "UTC";
    private static final String UNIX_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String LOG_DATE_FORMAT = "dd.MM.yyyy HH:mm:ss";

    private static final String ABAP_SHORT_DATE_FORMAT = "yyyyMMdd";
    private static final String ABAP_LONG_DATE_FORMAT = "yyyyMMddHHmmss";
    private static final String ABAP_SAP_LONG_DATE_FORMAT = "yyyyMMddHHmmss.SSSSSSS";

    private static final SimpleDateFormat abapShortSimpleDateFormat = new SimpleDateFormat(ABAP_SHORT_DATE_FORMAT);
    private static final SimpleDateFormat abapLongSimpleDateFormat = new SimpleDateFormat(ABAP_LONG_DATE_FORMAT);
    private static final SimpleDateFormat abapSapLongSimpleDateFormat = new SimpleDateFormat(ABAP_SAP_LONG_DATE_FORMAT);

    private static final DateTimeFormatter abapShortFormat = DateTimeFormatter.ofPattern(ABAP_SHORT_DATE_FORMAT);
    private static final DateTimeFormatter abapLongFormat = DateTimeFormatter.ofPattern(ABAP_LONG_DATE_FORMAT);
    private static final DateTimeFormatter abapSapLongFormat = DateTimeFormatter.ofPattern(ABAP_SAP_LONG_DATE_FORMAT);

    private TimeUtil() {
        // A private constructor to hide the implicit public one
    }

    /**
     * Gets the current Unix timestamp.
     *
     * @return the current Unix timestamp as a string.
     */
    public static String getUnixTimeStamp() {
        final Instant instant = Instant.now();

        final String formattedInstant = getUnixTimestamp(instant);

        log.debug("[Sap Marketing DocStore] [{}] Unix Timestamp: {}",
                LoggingUtil.getLogTimeStamp(), formattedInstant);
        return formattedInstant;
    }

    /**
     * Gets the Unix timestamp for a given input timestamp.
     *
     * @param inputTimestamp the input timestamp in ABAP long date format.
     * @return the Unix timestamp as a string.
     */
    public static String getUnixTimeStamp(final String inputTimestamp) {
        if (StringUtils.isEmpty(inputTimestamp)) {
            log.error("[Sap Marketing DocStore] [{}] Empty timestamp provided. Returning current Timestamp.",
                    LoggingUtil.getLogTimeStamp());
            return getUnixTimeStamp();
        }

        try {
            // Define the input format
            final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(ABAP_LONG_DATE_FORMAT);

            // Parse the string timestamp to LocalDateTime
            final LocalDateTime dateTime = LocalDateTime.parse(inputTimestamp, inputFormatter);

            // Convert LocalDateTime to Instant
            final Instant instant = dateTime.atZone(ZoneId.of(UTC)).toInstant();

            // Format the Instant
            final String formattedInstant = getUnixTimestamp(instant);

            // Output the Unix time
            log.debug("[Sap Marketing DocStore] [{}] Input Timestamp {}, output Unix Timestamp: {}",
                    LoggingUtil.getLogTimeStamp(), inputTimestamp, formattedInstant);

            return formattedInstant;
        } catch (DateTimeParseException exception) {
            log.debug("[Sap Marketing DocStore] [{}] Invalid timestamp provided: {}. Returning current Timestamp.",
                    LoggingUtil.getLogTimeStamp(), inputTimestamp);
        }

        return getUnixTimeStamp();
    }

    /**
     * Formats an Instant to Unix timestamp format.
     *
     * @param instant the Instant to format.
     * @return the formatted Unix timestamp as a string.
     */
    private static String getUnixTimestamp(Instant instant) {
        return new DateTimeFormatterBuilder()
                .appendPattern(UNIX_DATE_FORMAT)
                .appendFraction(ChronoField.NANO_OF_SECOND, 7, 7, true)
                .appendLiteral('Z')
                .toFormatter()
                .withZone(ZoneId.of(UTC))
                .format(instant);
    }

    /**
     * Gets the current timestamp in ISO format.
     *
     * @return the current timestamp in ISO format as a string.
     */
    public static String getIsoTimestamp() {
        // Example timestamp
        final long timestamp = System.currentTimeMillis();

        // Convert to LocalDateTime
        final LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.of(UTC));

        // Define the desired format
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(UNIX_DATE_FORMAT);

        // Format the LocalDateTime
        final String formattedDateTime = dateTime.format(formatter);

        log.info("[Sap Marketing DocStore] [{}] ISO Offset Date Time: {}",
                LoggingUtil.getLogTimeStamp(), formattedDateTime);
        return formattedDateTime;
    }

    /**
     * Converts a given timestamp to ISO_OFFSET_DATE_TIME format.
     *
     * @param timestamp the timestamp to be converted.
     * @return the converted timestamp in ISO_OFFSET_DATE_TIME format.
     */
    public static String getIsoTimestamp(final String timestamp) {
        if (StringUtils.isEmpty(timestamp)) {
            log.debug("[Sap Marketing DocStore] [{}] Empty timestamp provided. Returning empty string.",
                    LoggingUtil.getLogTimeStamp());
            return StringUtils.EMPTY;
        }

        // Verify if the timestamp is a valid date-time
        boolean isValid = isValidDateTime(timestamp);
        if (BooleanUtils.isFalse(isValid)) {
            log.error("[Sap Marketing DocStore] [{}] Invalid timestamp provided: {}. Returning empty string.",
                    LoggingUtil.getLogTimeStamp(), timestamp);
            return StringUtils.EMPTY;
        }

        // Define the input format
        final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

        // Parse the String timestamp to LocalDateTime
        final LocalDateTime dateTime = LocalDateTime.parse(timestamp, inputFormatter);

        // Define the desired output format
        final DateTimeFormatter outputFormatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS")
                .appendLiteral('Z')
                .toFormatter()
                .withZone(ZoneId.of(UTC));

        // Format the LocalDateTime to the desired format
        String formattedDateTime = dateTime.format(outputFormatter);

        log.info("[Sap Marketing DocStore] [{}] Original Timestamp: {}. Converted ISO Offset Date Time: {}",
                LoggingUtil.getLogTimeStamp(), timestamp, formattedDateTime);
        return formattedDateTime;
    }

    /**
     * Converts a given ABAP date string to a Date object.
     *
     * @param inputDate the ABAP date string to be converted.
     * @return the converted Date object.
     */
    public static Date getAbapDate(final String inputDate) {
        if (StringUtils.isEmpty(inputDate)) {
            log.warn("[Sap Marketing DocStore] [{}] Empty date provided. Returning now date.",
                    LoggingUtil.getLogTimeStamp());
            return new Date();
        }

        try {
            final Date date = abapShortSimpleDateFormat.parse(inputDate);
            log.debug("[Sap Marketing DocStore] [{}] ABAP (from short date format) Date: {}",
                    LoggingUtil.getLogTimeStamp(), date);
            return date;
        } catch (ParseException e) {
            log.error("[Sap Marketing DocStore] [{}] ABAP timestamp: {} is not in the short date format {}. Exception: {}",
                    LoggingUtil.getLogTimeStamp(), inputDate, ABAP_SHORT_DATE_FORMAT, e.getMessage());
        }

        try {
            final Date date = abapLongSimpleDateFormat.parse(inputDate);
            log.debug("[Sap Marketing DocStore] [{}] ABAP (from long date format) Date: {}",
                    LoggingUtil.getLogTimeStamp(), date);
            return date;
        } catch (ParseException e) {
            log.error("[Sap Marketing DocStore] [{}] ABAP timestamp: {} is not in the long date format {}. Exception: {}",
                    LoggingUtil.getLogTimeStamp(), inputDate, ABAP_LONG_DATE_FORMAT, e.getMessage());
        }

        try {
            final Date date = abapSapLongSimpleDateFormat.parse(inputDate);
            log.debug("[Sap Marketing DocStore] [{}] ABAP (from sap-long date format) Date: {}",
                    LoggingUtil.getLogTimeStamp(), date);
            return date;
        } catch (ParseException e) {
            log.error("[Sap Marketing DocStore] [{}] ABAP timestamp: {} is not in the sap long date format {}. Exception: {}",
                    LoggingUtil.getLogTimeStamp(), inputDate, ABAP_SAP_LONG_DATE_FORMAT, e.getMessage());
        }

        log.error("[Sap Marketing DocStore] [{}] Invalid ABAP date provided: {}. Returning now date.",
                LoggingUtil.getLogTimeStamp(), inputDate);
        return new Date();
    }

    public static String getLogData(final String unixTimestamp) {
        if (StringUtils.isEmpty(unixTimestamp)) {
            log.warn("[Sap Marketing DocStore] [{}] Empty date provided.",
                    LoggingUtil.getLogTimeStamp());
            return StringUtils.EMPTY;
        }

        try {
            final long timestamp = Long.parseLong(unixTimestamp);
            return getLogData(timestamp);
        } catch (NumberFormatException e) {
            log.error("[Sap Marketing DocStore] [{}] Invalid Unix timestamp provided: {}. Exception: {}",
                    LoggingUtil.getLogTimeStamp(), unixTimestamp, e.getMessage());
            return StringUtils.EMPTY;
        }
    }

    public static String getLogData(final long unixTimestamp) {
        try {
            final Date date = new Date(unixTimestamp);
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(LOG_DATE_FORMAT);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            log.error("[Sap Marketing DocStore] [{}] Invalid Unix timestamp provided: {}. Exception: {}",
                    LoggingUtil.getLogTimeStamp(), unixTimestamp, e.getMessage());
            return StringUtils.EMPTY;
        }
    }

    public static String getLongLogData(final long unixTimestamp) {
        try {
            final Date date = new Date(unixTimestamp);
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(LOG_DATE_FORMAT);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            log.error("[Sap Marketing DocStore] [{}] Invalid Unix timestamp provided: {}. Exception: {}",
                    LoggingUtil.getLogTimeStamp(), unixTimestamp, e.getMessage());
            return StringUtils.EMPTY;
        }
    }


    /**
     * Converts a given ABAP date string to an Instant object.
     *
     * @param inputAbapDate the ABAP date string to be converted.
     * @return the converted Instant object.
     */
    public static Instant getAbapInstantDate(final String inputAbapDate) {
        if (StringUtils.isEmpty(inputAbapDate)) {
            log.warn("[Sap Marketing DocStore] [{}] Empty date provided. Returning now timestamp.",
                    LoggingUtil.getLogTimeStamp());
            return Instant.now();
        }

        try {
            final LocalDate localDate = LocalDate.parse(inputAbapDate, abapShortFormat);
            return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        } catch (DateTimeParseException e) {
            log.error("[Sap Marketing DocStore] [{}] ABAP timestamp: {} is not in the short date format {}. Exception: {}",
                    LoggingUtil.getLogTimeStamp(), inputAbapDate, ABAP_SHORT_DATE_FORMAT, e.getMessage());
        }

        try {
            final LocalDate localDate = LocalDate.parse(inputAbapDate, abapLongFormat);
            return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        } catch (DateTimeParseException e) {
            log.error("[Sap Marketing DocStore] [{}] ABAP timestamp: {} is not in the long date format {}. Exception: {}",
                    LoggingUtil.getLogTimeStamp(), inputAbapDate, ABAP_LONG_DATE_FORMAT, e.getMessage());
        }

        try {
            final LocalDate localDate = LocalDate.parse(inputAbapDate, abapSapLongFormat);
            return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        } catch (DateTimeParseException e) {
            log.error("[Sap Marketing DocStore] [{}] ABAP timestamp: {} is not in the long date format {}. Exception: {}",
                    LoggingUtil.getLogTimeStamp(), inputAbapDate, ABAP_SAP_LONG_DATE_FORMAT, e.getMessage());
        }

        log.error("[Sap Marketing DocStore] [{}] Invalid ABAP date provided: {}. Returning now timestamp.",
                LoggingUtil.getLogTimeStamp(), inputAbapDate);
        return Instant.now();
    }

    /**
     * Checks if a given timestamp is valid.
     *
     * @param timestamp the timestamp to be checked.
     * @return true if the timestamp is valid, false otherwise.
     */
    private static boolean isValidDateTime(@NonNull final String timestamp) {
        try {
            OffsetDateTime.parse(timestamp, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            log.debug("[Sap Marketing DocStore] [{}] Valid timestamp provided: {}",
                    LoggingUtil.getLogTimeStamp(), timestamp);
            return true;
        } catch (DateTimeParseException e) {
            log.debug("[Sap Marketing DocStore] [{}] Invalid timestamp provided: {}",
                    LoggingUtil.getLogTimeStamp(), timestamp);
            return false;
        }
    }

    /**
     * Checks if a given Instant is in the past.
     *
     * @param dateTime the Instant to be checked.
     * @return true if the Instant is in the past, false otherwise.
     */
    public static boolean isDateTimeInPast(final Instant dateTime) {
        return dateTime.isBefore(Instant.now());
    }

    /**
     * Checks if a given ABAP date string represents a date-time in the past.
     *
     * @param inputAbapDate the ABAP date string to be checked.
     * @return true if the date-time is in the past, false otherwise.
     */
    public static boolean isAbapDateTimeInPast(@NonNull final String inputAbapDate) {
        if(StringUtils.isEmpty(inputAbapDate)) {
            log.warn("[Sap Marketing DocStore] [{}] Empty ABAP date provided. Returning true - ABAP date is in the past.",
                    LoggingUtil.getLogTimeStamp());
            return true;
        }

        try {
            final Instant instantAbapDate = getAbapInstantDate(inputAbapDate);
            return isDateTimeInPast(instantAbapDate);
        } catch (DateTimeParseException e) {
            log.error("[Sap Marketing DocStore] [{}] Invalid ABAP date provided: {}",
                    LoggingUtil.getLogTimeStamp(), inputAbapDate);
        }

        return true;
    }

    /**
     * Converts a given Instant to a short ABAP timestamp string.
     *
     * @param inputAbapDate the Instant to be converted.
     * @return the converted short ABAP timestamp string.
     */
    public static String getShortAbapTimestamp(@NonNull final Instant inputAbapDate) {
        try {
            final LocalDateTime dateTime = LocalDateTime.ofInstant(inputAbapDate, ZoneId.systemDefault());
            return dateTime.format(abapShortFormat);
        } catch (DateTimeParseException | UnsupportedTemporalTypeException e) {
            log.debug("[Sap Marketing DocStore] [{}] Invalid ABAP date time (Instant to String): [{}]. Exception: {}",
                    LoggingUtil.getLogTimeStamp(), inputAbapDate, e.getMessage());
        }

        try {
            final ZonedDateTime zonedDateTime = inputAbapDate.atZone(ZoneId.of("UTC"));
            return zonedDateTime.format(abapShortFormat);
        } catch (DateTimeParseException | UnsupportedTemporalTypeException e) {
            log.error("[Sap Marketing DocStore] [{}] Invalid ABAP date time (Instant to String): [{}]. Exception: {}",
                    LoggingUtil.getLogTimeStamp(), inputAbapDate, e.getMessage());
        }

        log.warn("[Sap Marketing DocStore] [{}] Invalid ABAP date time (Instant to String): [{}]. Returning current date.",
                LoggingUtil.getLogTimeStamp(), inputAbapDate);
        return getShortAbapTimestamp();
    }

    public static String getShortAbapTimestamp() {
        final LocalDate currentDate = LocalDate.now();
        return currentDate.format(abapShortFormat);
    }

    /**
     * Converts a given Instant to a long ABAP timestamp string.
     *
     * @param inputAbapDate the Instant to be converted.
     * @return the converted long ABAP timestamp string.
     */
    public static String getLongAbapTimestamp(@NonNull final Instant inputAbapDate) {
        try {
            final LocalDateTime dateTime = LocalDateTime.ofInstant(inputAbapDate, ZoneId.systemDefault());
            return dateTime.format(abapLongFormat);
        } catch (DateTimeParseException | UnsupportedTemporalTypeException e) {
            log.error("[Sap Marketing DocStore] [{}] Invalid ABAP date time (String to Instant): {}",
                    LoggingUtil.getLogTimeStamp(), inputAbapDate, e);
        }

        return null;
    }

    public int getCurrentSecond() {
        return LocalDateTime.now().getSecond();
    }

    public boolean isCurrentSecondEven() {
        int currentSecond = getCurrentSecond();
        return currentSecond % 2 == 0;
    }

    public boolean isCurrentSecondOdd() {
        return !isCurrentSecondEven();
    }
}