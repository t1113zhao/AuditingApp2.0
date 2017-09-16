package com.example.administrator.financialauditingapppro.net;

import android.text.TextUtils;

import com.nostra13.universalimageloader.utils.L;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiaowei on 2015/9/16.
 * yyyy-MM-dd 1969-12-31
 * yyyy-MM-dd 1970-01-01
 * yyyy-MM-dd HH:mm 1969-12-31 16:00
 * yyyy-MM-dd HH:mm 1970-01-01 00:00
 * yyyy-MM-dd HH:mmZ 1969-12-31 16:00-0800
 * yyyy-MM-dd HH:mmZ 1970-01-01 00:00+0000
 * yyyy-MM-dd HH:mm:ss.SSSZ 1969-12-31 16:00:00.000-0800
 * yyyy-MM-dd HH:mm:ss.SSSZ 1970-01-01 00:00:00.000+0000
 * yyyy-MM-dd'T'HH:mm:ss.SSSZ 1969-12-31T16:00:00.000-0800
 * yyyy-MM-dd'T'HH:mm:ss.SSSZ 1970-01-01T00:00:00.000+0000
 */
public class DateUtil {
    static SimpleDateFormat dateFormate;
    static String[] formats;

    static {
        dateFormate = new SimpleDateFormat();
        formats = new String[]{
                "yyyy-MM-dd",
                "yyyy-MM-dd HH:mm",
                "yyyy-MM-dd HH:mm:ss"
        };
    }

    /**
     * @param milliseconds 毫秒数  the number of milliseconds since Jan. 1, 1970 GMT.
     * @return yyyy-MM-dd
     */
    public static final String getDateToDay(long milliseconds) {
        dateFormate.applyPattern(formats[0]);
        return dateFormate.format(new Date(milliseconds));
    }

    /**
     * @param milliseconds 毫秒数  the number of milliseconds since Jan. 1, 1970 GMT.
     * @return yyyy-MM-dd HH:mm
     */
    public static final String getDateToMinutue(long milliseconds) {
        dateFormate.applyPattern(formats[1]);
        return dateFormate.format(new Date(milliseconds));
    }

    /**
     * @param milliseconds 毫秒数  the number of milliseconds since Jan. 1, 1970 GMT.
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static final String getDateToSeconds(long milliseconds) {
        dateFormate.applyPattern(formats[2]);
        return dateFormate.format(new Date(milliseconds));
    }


    /**
     * @param datetime
     * @param type     0： yyyy-MM-dd 1:yyyy-MM-dd HH:mm 2:yyyy-MM-dd HH:mm:ss 其他:yyyy-MM-dd
     * @return
     */
    public static final String parseDateTime(String datetime, int type) {
        if (TextUtils.isEmpty(datetime)) return "";
        if (!datetime.matches("\\/Date\\([-+0-9]{1,}\\)\\/")) return "";
        datetime = datetime.replaceAll("[^-+0-9]{1,}", "");
        long timestamp = Long.parseLong(datetime.split("\\+")[0]);

        /**
         *   Calendar calendar = Calendar.getInstance();
         calendar.setTimeInMillis(timestamp);
         calendar.setTimeZone(TimeZone.getTimeZone("GMT" + datetime.replace("" + timestamp, "")));
         */
        if (type == 1) return getDateToMinutue(timestamp);
        if (type == 2) return getDateToSeconds(timestamp);

        return getDateToDay(timestamp);

    }

    public static final Date parseDateTime(String datetime) {
        if (TextUtils.isEmpty(datetime)) return new Date();
        if (!datetime.matches("\\/Date\\([-+0-9]{1,}\\)\\/")) return new Date();
        datetime = datetime.replaceAll("[^-+0-9]{1,}", "");
        long timestamp = Long.parseLong(datetime.split("\\+")[0]);
        Date date = new Date(timestamp);
        return date;

    }

    public static final String parseTimeToDate(String datetime) {
        if (TextUtils.isEmpty(datetime)) return "";
        datetime = datetime.replaceAll("[^-+0-9]{1,}", "");
        long timestamp = Long.parseLong(datetime.split("\\+")[0]);
        L.i("timestamp" +timestamp);
        return DateUtil.getDateToDay(timestamp);
    }
}
