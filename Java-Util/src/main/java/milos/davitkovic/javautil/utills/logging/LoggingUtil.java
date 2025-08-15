package milos.davitkovic.javautil.utills.logging;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.javautil.utills.annotations.UtilClass;
import org.apache.commons.lang3.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@UtilClass
public class LoggingUtil {

    private static final String LOG_TIMESTAMP_FORMAT = "dd.MM.yyyy HH:mm:ss";
    private static final String LOG_TIMESTAMP_LONG_FORMAT = "dd.MM.yyyy HH:mm:ss.SSS";
    private static final String LOG_TIME_FORMAT = "HH:mm:ss.SSS";

    private static final SimpleDateFormat LOG_DATE_TIME_FORMATTER = new SimpleDateFormat(LOG_TIMESTAMP_FORMAT);
    private static final SimpleDateFormat LOG_DATE_TIME_LONG_FORMATTER = new SimpleDateFormat(LOG_TIMESTAMP_LONG_FORMAT);
    private static final SimpleDateFormat LOG_TIME_FORMATTER = new SimpleDateFormat(LOG_TIME_FORMAT);

    private LoggingUtil() {
        // private constructor
    }

    public static String getLogTimeStamp() {
        return LOG_DATE_TIME_FORMATTER.format(new Date());
    }

    public static String getLogTimeStampLong() {
        return LOG_DATE_TIME_LONG_FORMATTER.format(new Date());
    }

    public static String getLogTimeStampLong(final long unixTimestamp) {
        return LOG_DATE_TIME_LONG_FORMATTER.format(new Date(unixTimestamp));
    }

    public static String getLogTime() {
        return LOG_TIME_FORMATTER.format(new Date());
    }

    public static String getLogTimeStampPlusMinutes(final Date date, final int minutes) {
        if (date == null) {
            return StringUtils.EMPTY;
        }

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);

        final Date dateInFuture = calendar.getTime();
        return LOG_DATE_TIME_FORMATTER.format(dateInFuture);
    }

    public static String getLogTimeStampPlusMinutes(final int minutes) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minutes);

        final Date dateInFuture = calendar.getTime();
        return LOG_DATE_TIME_FORMATTER.format(dateInFuture);
    }

    public static Date getDatePlusMinutes(final Date date, final int minutes) {
        if (date == null) {
            log.error("[Sap Marketing DataBuffer] Date is null. Can't calculate the date.");
            return null;
        }

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);

        return calendar.getTime();
    }

    public static Date getDateMinusSeconds(final Date date, final int seconds) {
        if (date == null) {
            log.error("[Sap Marketing DataBuffer] Date is null. Can't calculate the date.");
            return null;
        }

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, -seconds);

        return calendar.getTime();
    }

    public static String getDifferenceInMinutes(final Date startDate, final Date endDate) {
        if (startDate == null || endDate == null) {
            return "[Sap Marketing DataBuffer] Start or end date is null";
        }

        // Get the time in milliseconds
        long time1 = startDate.getTime();
        long time2 = endDate.getTime();

        // Calculate the difference in milliseconds
        long diffInMillis = time2 - time1;

        // Convert milliseconds to minutes
        long diffInMinutes = Math.abs(diffInMillis / (1000 * 60));

        return String.valueOf(diffInMinutes);
    }

    public static String getDifferenceInSeconds(final Date startDate, final Date endDate) {
        if (startDate == null || endDate == null) {
            return "[Sap Marketing DataBuffer] Start or end date is null";
        }

        // Get the time in milliseconds
        long time1 = startDate.getTime();
        long time2 = endDate.getTime();

        // Calculate the difference in milliseconds
        long diffInMillis = time2 - time1;

        // Convert milliseconds to seconds
        long diffInSeconds = Math.abs(diffInMillis / 1000);
        if (diffInSeconds == 0) {
            return String.format("%s milliseconds", diffInMillis);
        }

        return String.format("%s seconds", diffInSeconds);
    }

    public static String getDifference(final Date startDate, final Date endDate) {
        if (startDate == null || endDate == null) {
            return "[Sap Marketing DataBuffer] Start or end date is null";
        }

        // Get the time in milliseconds
        long time1 = startDate.getTime();
        long time2 = endDate.getTime();

        // Calculate the difference in milliseconds
        long differenceMilliseconds = Math.abs(time2 - time1);
        log.debug("[Sap Marketing DataBuffer] [{}] Difference in milliseconds: {}", getLogTimeStamp(), differenceMilliseconds);

        // Convert milliseconds to hours
        long differenceHours = Math.abs(differenceMilliseconds / (1000 * 60 * 60));
        if (differenceHours > 0) {
            log.debug("[Sap Marketing DataBuffer] [{}] Difference in hours: {}", getLogTimeStamp(), differenceHours);
            return String.format("%s hours", differenceHours);
        }

        // Convert milliseconds to minutes
        long differenceMinutes = Math.abs(differenceMilliseconds / (1000 * 60));
        if (differenceMinutes > 0) {
            log.debug("[Sap Marketing DataBuffer] [{}] Difference in minutes: {}", getLogTimeStamp(), differenceMinutes);
            return String.format("%s minutes", differenceMinutes);
        }

        // Convert milliseconds to seconds
        long differenceSeconds = Math.abs(differenceMilliseconds / 1000);
        if (differenceSeconds > 0) {
            log.debug("[Sap Marketing DataBuffer] [{}] Difference in seconds: {}", getLogTimeStamp(), differenceSeconds);
            return String.format("%s seconds", differenceSeconds);
        }

        return String.format("%s milliseconds", differenceMilliseconds);
    }

    public static String getDifferenceInMinutes(final Instant startDate, final Instant endDate) {
        if (startDate == null || endDate == null) {
            log.error("[Sap Marketing DataBuffer] ZVS – Start date or end date is null. Cannot calculate the minutes.");
            return StringUtils.EMPTY;
        }

        long differenceInMinutes = Math.abs(Duration.between(startDate, endDate).toMinutes());
        return String.valueOf(differenceInMinutes);
    }

    public static Date getLogDate(final String stringDate) {
        if (StringUtils.isEmpty(stringDate)) {
            return null;
        }

        try {
            return LOG_DATE_TIME_FORMATTER.parse(stringDate);
        } catch (ParseException e) {
            log.error("[Sap Marketing DataBuffer] Error while parsing date: {}", e.getMessage());
        }
        return null;
    }

    public static long getMinutes(Instant startDate, Instant endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }

        return Math.abs(Duration.between(startDate, endDate).toMinutes());
    }

    public static String getLogTimeStamp(final Instant dateTime) {
        if (dateTime == null) {
            return StringUtils.EMPTY;
        }

        final Date timestamp = Date.from(dateTime);
        return LOG_DATE_TIME_FORMATTER.format(timestamp);
    }

    public static String getLogTimeStamp(final Date dateTime) {
        if (dateTime == null) {
            return StringUtils.EMPTY;
        }

        return LOG_DATE_TIME_FORMATTER.format(dateTime);
    }

}
