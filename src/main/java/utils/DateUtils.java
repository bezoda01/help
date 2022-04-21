package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateUtils {

    public static String getFormatDate(long date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(date);
    }
}
