package milos.davitkovic.utills.services.impl.string;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Restore IP Addresses
 *
 * author milos.davitkovic@gmail.com
 */
@Service
public class RestoreIpAddresses {

    // Input: "25525511135"
    //Output: ["255.255.11.135", "255.255.111.35"]

    private static int MIN_LENGTH = 4;
    private static int MAX_LENGTH = 12;
    private static int MAX_VALUE = 255;

    public List<String> restoreIpAddresses(String s) {

        if (s.length() < MIN_LENGTH || s.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Minimum length of the string is 4 (0.0.0.0) and maximum length is 12!");
        }

        int n = s.length();
        List<String> IPs = new ArrayList<>();
        restoreIpAddresses(s, n, 0, 0, "", IPs);
        return IPs;
    }

    public void restoreIpAddresses(String s, int n, int start, int subnet, String currentIP, List<String> IPs) {
        // If we are at the final subnet and the substring remaining is greater than 255 or it has a leading zero
        // and still some other chars after that(subnets can't have leading zeroes) return else add to the list
        if (subnet == 3) {
            if (s.substring(start).length() > 3 ||
                    Integer.parseInt(s.substring(start)) > MAX_VALUE ||
                    (s.charAt(start) == '0' && start != n - 1)) {
                return;
            }

            currentIP += s.substring(start);
            IPs.add(currentIP);
        }
        // for other subnets we take 1, 2 and 3 chars and for the third char check if its greater than 255
        else {
            for (int end = start + 1; end < start + 4 && end < n; end++) {
                if (end == start + 3 && Integer.parseInt(s.substring(start, end)) > MAX_VALUE) {
                    continue;
                }
                // temporary save for backtrack
                final String temp = currentIP;
                currentIP = currentIP + s.substring(start, end) + ".";
                restoreIpAddresses(s, n, end, subnet + 1, currentIP, IPs);
                // Backtrack
                currentIP = temp;
                //subnet starting with zero shouldnt consider next chars
                if (s.substring(start, end).equals("0")) {
                    break;
                }
            }
        }
    }
}
