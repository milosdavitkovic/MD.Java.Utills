package arrays;

import java.util.*;
import java.lang.*;
import java.io.*;


public class RotatingArrayByDElements {

    public static void main (String[] args) throws IOException
    {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        Integer t = Integer.parseInt(br.readLine());

        while(t --> 0) {
            Integer n = Integer.parseInt(br.readLine());
            String inputArray = br.readLine();
            List<String> array = new ArrayList<String>(Arrays.asList(inputArray.split(" ")));
            Integer d = Integer.parseInt(br.readLine());
            rotateArrayByD(d, array);
        }
        br.close();
    }

    private static void rotateArrayByD(final Integer d, final List<String> array) {

        for (int i = 0; i < d; i++) {
            array.add(array.get(0)); 	// add on last position
            array.remove(0);		// remove first position
        }
        StringBuffer sb = new StringBuffer();
        array.forEach(value -> sb.append(value + " "));
        System.out.println(sb);
    }
}
