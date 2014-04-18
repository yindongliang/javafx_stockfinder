/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockfinder.uitl;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author doyin
 */
public class ViewUtils {

    public static String dateFormater(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        String dateStr = sdf.format(date);
        return dateStr;
    }

    public static String timer(long startTime) {
        long longtime = (System.currentTimeMillis() - startTime) / 1000;
        String time = String.format("%02d:%02d:%02d", longtime / 3600, longtime / 60, longtime % 60);
        return time;
    }

  
}
