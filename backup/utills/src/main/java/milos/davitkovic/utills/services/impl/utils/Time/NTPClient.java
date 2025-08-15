package milos.davitkovic.utills.services.impl.utils.Time;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Miloš Davitković
 *
 */
@Service
public class NTPClient {

	/**
	 * 
	 * @param 	utc If you set utc on true you will have UTC (GMT0) time, if you set utc on false you will have 
	 * 			your time zone time
	 * @return 	Correct NTP time from pool of servers
	 */
//	public Date getNTPDate(boolean utc) {
//		System.out.println(" ************************************************************** " );
//		String[] hosts = new String[]{
////				"time.nist.gov",
//				"pool.ntp.org",
//				"europe.pool.ntp.org",
//				"asia.pool.ntp.org",
//				"oceania.pool.ntp.org",
//				"north-america.pool.ntp.org",
//				"south-america.pool.ntp.org",
//				"africa.pool.ntp.org",
//				"ntp1.inrim.it",
//				"ntp2.inrim.it",
//		"ntp.xs4all.nl"};
//
//		NTPUDPClient client = new NTPUDPClient();
//		// We want to timeout if a response takes longer than 5 seconds
//		client.setDefaultTimeout(5000);
//
//		for (String host : hosts) {
//
//			try {
//				InetAddress hostAddr = InetAddress.getByName(host);
//				System.out.println("> " + hostAddr.getHostName() + "/" + hostAddr.getHostAddress());
//				TimeInfo info = client.getTime(hostAddr);
//				//                Date date = new Date(DateTimeZone.UTC);
//				Date date = new Date(info.getReturnTime());
//
//				if (utc) {
//					return convertToUTC(date);
//				}else {
//					return date;
//				}
//			}
//			catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		client.close();
//
//		return null;
//
//	}

	public Date convertToUTC(Date inputDate) {
		Calendar calendar  = Calendar.getInstance();
		int utcOffset = calendar.get(Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET);
		long tempDate  = inputDate.getTime();

		return new Date(tempDate - utcOffset);
	}

}
