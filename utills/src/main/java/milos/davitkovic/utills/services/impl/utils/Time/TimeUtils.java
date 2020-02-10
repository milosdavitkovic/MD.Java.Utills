package milos.davitkovic.utills.services.impl.utils.Time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 
 * @author Miloš Davitković
 *
 */
@Service
public class TimeUtils {

	private static long NTPdifference;
	private static boolean NTPContacted = false;
	
	@Resource
	private NTPClient ntpClient;

	/**
	 * 
	 * @param format - Format options: "yyyy/MM/dd HH:mm:ss" hh - 12h format, HH - 24h format; yyyy/MM/dd HH:mm:ss; yyyy/MM/dd
	 * @return
	 */
	public String getCurrentDateTime(final String format) {
		final DateFormat dateFormat = new SimpleDateFormat(format);
		final Date date = new Date();
		return dateFormat.format(date);
	}
	
	/**
	 * Convert From Instant to String with System Time Zone.
	 * The Instant class doesn't contain Zone information, it only stores timestamp in milliseconds 
	 * from UNIX epoch, i.e. 1 Jan 1070 from UTC. So, formatter can't print a date because date always 
	 * printed for concrete time zone. You should set time zone to formatter and all will be fine
	 * @param instant
	 * @param utc - if utc is TRUE result will be in UTC (GMT0 time), if utc is FALSE result will be in System Time Zone
	 * @return
	 */
	public String fromInstantToString(Instant instant, boolean utc) {
		DateTimeFormatter formatter;
		if (utc) {
			formatter =
				    DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
				                     .withLocale( Locale.UK )
				                     .withZone( ZoneOffset.UTC );
		}else {
			formatter =
				    DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
				                     .withLocale( Locale.UK )
				                     .withZone( ZoneId.systemDefault() );
		}
		
		String output = formatter.format( instant );
		return output;
	}

	public Instant fromUnixToInstant(long unix) {
		Instant instant = Instant.ofEpochSecond(unix);
		return instant;
	}

	/**
	 * 
	 * @param instant An Instant represents a timestamp in Java 8.
	 * @return
	 */
	public Date fromInstantToDate(Instant instant) {
		Date date = java.util.Date.from( instant );
		return date;
	}

	/**
	 * With the static Date.from() method you can convert an Instant to a java.util.Date instance.
	 * @param unix
	 * @return
	 */
	public Date fromUnixToDate(long unix) {
		Date date = Date.from( Instant.ofEpochSecond( unix ) );
		return date;
	}

	/**
	 * Date's constructor expects the timeStamp value to be in milliseconds. 
	 * Multiply your timestamp's value with 1000, then pass is to the constructor.
	 * @param unix
	 * @return
	 */
	public Date fromUnixToDate2(long unix) {
		Date date = new Date ();
		date.setTime((long)unix*1000);
		return date;
	}

	/**
	 * 
	 * @param unix
	 * @param format - MM.dd.yyyy hh:mm:ss or something like that
	 * @return
	 */
	public String fromUnixToSpecificString(long unix, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date = sdf.format(unix);
		return date;
	}
	
	/**
	 * In file TimeZones.txt we have list of all poisble Time Zones
	 * @param unix
	 * @param format
	 * @return
	 */
	public String fromUnixToSpecificString(Double unix, String format) {
		SimpleDateFormat gmt0 = new SimpleDateFormat(format);
		gmt0.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
		Date inputDate = fromUnixToDate(unix.longValue());
		return gmt0.format(inputDate);
	}

	/**
	 * 
	 * @param unix_timestamp
	 * @return
	 * @throws ParseException
	 */
	public String fromUnixToStringDefault(String unix_timestamp) throws ParseException {    
		long timestamp = Long.parseLong(unix_timestamp) * 1000;

		SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy HH:mm:ss", Locale.US);
		String date = sdf.format(timestamp);

		return date.toString();
	}
	
//	public Date currentNTPTime() {
//		if (NTPContacted) {
//			 Date ntpDateTime = ntpClient.getNTPDate(false);
//			 Instant ntpTime = ntpDateTime.toInstant();
//			 Instant systemTime = Instant.now();
//			 Duration between = Duration.between(ntpTime, systemTime).abs();
//			 NTPdifference = between.toNanos();
//		}
//
//		Instant systemTime = Instant.now();
//		systemTime.plusNanos(NTPdifference);
//		return fromInstantToDate(systemTime);
//	}

	public Calendar getTomorrow() {
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_WEEK, +1);
 		calendar.getTime();
 		return calendar;
	}
}
