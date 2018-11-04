# 方法一：通过String.substring()方法将最后的三位去掉

/** 
 * 获取精确到秒的时间戳 
 * @return 
 */  
public static int getSecondTimestamp(Date date){  
    if (null == date) {  
        return 0;  
    }  
    String timestamp = String.valueOf(date.getTime());  
    int length = timestamp.length();  
    if (length > 3) {  
        return Integer.valueOf(timestamp.substring(0,length-3));  
    } else {  
        return 0;  
    }  
}


#方法二：通过整除将最后的三位去掉

/ *
 * 获取精确到秒的时间戳 
  *@param date 
  *@return 
 */ 
public static int getSecondTimestampTwo(Date date){  
    if (null == date) {  
        return 0;  
    }  
    String timestamp = String.valueOf(date.getTime()/1000);  
    return Integer.valueOf(timestamp);  
} 









import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 2017/5/15.
 */
public class DateConvertUtils {

    public static final String TIME_INTERVAL_SECOND = "second";
    public static final String TIME_INTERVAL_MINUTE = "minute";
    public static final String TIME_INTERVAL_HOUR = "hour";
    public static final String TIME_INTERVAL_DAY = "day";
    public static final String TIME_INTERVAL_WEEK = "week";
    public static final String TIME_INTERVAL_MONTH = "month";
    public static final String TIME_INTERVAL_QUARTER = "quarter";
    public static final String TIME_INTERVAL_YEAR = "year";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date parse(String dateString, String dateFormat) {
        return parse(dateString, dateFormat, Date.class);
    }

    public static <T extends Date> T parse(String dateString,
                                           String dateFormat, Class<T> targetResultType) {
        if (StringUtils.isEmpty(dateString))
            return null;
        DateFormat df = new SimpleDateFormat(dateFormat);
        try {
            T t = targetResultType.getConstructor(new Class[]{Long.TYPE})
                    .newInstance(
                            new Object[]{Long.valueOf(df.parse(dateString)
                                    .getTime())});
            return t;
        } catch (ParseException e) {
            String errorInfo = "cannot use dateformat:" + dateFormat
                    + " parse datestring:" + dateString;
            throw new IllegalArgumentException(errorInfo, e);
        } catch (Exception e) {
            throw new IllegalArgumentException("error targetResultType:"
                    + targetResultType.getName(), e);
        }
    }

    public static String format(Date date, String dateFormat) {
        if (date == null)
            return null;
        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
    }

    public static String getDateString(String datePattern) {
        return new SimpleDateFormat(datePattern).format(new Date());
    }

    public static String getYesterdayString(String dateFormat) {
        Date yesterday = add(Calendar.DAY_OF_YEAR, new Date(), -1);
        return DateConvertUtils.format(yesterday, dateFormat);
    }

    public static Date add(int field, Date date, int value) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int fieldNewValue = (c.get(field) + value);
        c.set(field, fieldNewValue);
        return c.getTime();
    }

    public static long dateDiff(String timeInterval, Date date1, Date date2) {
        Calendar calendar = Calendar.getInstance();
        if (timeInterval.equals(TIME_INTERVAL_YEAR)) {
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR);
            calendar.setTime(date2);
            return time - calendar.get(Calendar.YEAR);
        }

        if (timeInterval.equals(TIME_INTERVAL_QUARTER)) {
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR) * 4;
            calendar.setTime(date2);
            time -= calendar.get(Calendar.YEAR) * 4;
            calendar.setTime(date1);
            time += calendar.get(Calendar.MONTH) / 4;
            calendar.setTime(date2);
            return time - calendar.get(Calendar.MONTH) / 4;
        }

        if (timeInterval.equals(TIME_INTERVAL_MONTH)) {
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR) * 12;
            calendar.setTime(date2);
            time -= calendar.get(Calendar.YEAR) * 12;
            calendar.setTime(date1);
            time += calendar.get(Calendar.MONTH);
            calendar.setTime(date2);
            return time - calendar.get(Calendar.MONTH);
        }

        if (timeInterval.equals(TIME_INTERVAL_WEEK)) {
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR) * 52;
            calendar.setTime(date2);
            time -= calendar.get(Calendar.YEAR) * 52;
            calendar.setTime(date1);
            time += calendar.get(Calendar.WEEK_OF_YEAR);
            calendar.setTime(date2);
            return time - calendar.get(Calendar.WEEK_OF_YEAR);
        }

        if (timeInterval.equals(TIME_INTERVAL_DAY)) {
            calendar.setTime(date1);
            int time = calendar.get(Calendar.DAY_OF_YEAR)
                    + calendar.get(Calendar.YEAR) * 365;
            calendar.setTime(date2);
            return time
                    - (calendar.get(Calendar.DAY_OF_YEAR) + calendar
                    .get(Calendar.YEAR) * 365);
        }

        if (timeInterval.equals(TIME_INTERVAL_HOUR)) {
            long time = date1.getTime() / 1000 / 60 / 60;
            return time - date2.getTime() / 1000 / 60 / 60;
        }

        if (timeInterval.equals(TIME_INTERVAL_MINUTE)) {
            long time = date1.getTime() / 1000 / 60;
            return time - date2.getTime() / 1000 / 60;
        }

        if (timeInterval.equals(TIME_INTERVAL_SECOND)) {
            long time = date1.getTime() / 1000;
            return time - date2.getTime() / 1000;
        }

        return date1.getTime() - date2.getTime();
    }

    public static long dateDiff(String timeInterval, Long unixTime1,
                                Long unixTime2) {
        return dateDiff(timeInterval, new Date(unixTime1), new Date(unixTime2));
    }

    public static void main(String[] args) {
        Date date1 = parse("2012-09-01 16:06:59", "yyyy-MM-dd HH:mm:ss");
        Date date2 = parse("2012-09-01 16:00:00", "yyyy-MM-dd HH:mm:ss");
        // System.out.println(dateDiff(TIME_INTERVAL_MONTH, date1, date2));
        // System.out.println(dateDiff(TIME_INTERVAL_YEAR, date1, date2));
        System.out.println(dateDiff(TIME_INTERVAL_MINUTE, date1, date2));
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return
     */
    public static int getCurrentTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }


    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String dateToTimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);

            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStampToDate(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null") || seconds.equals("0")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (seconds.length() > 10) return sdf.format(new Date(Long.valueOf(seconds)));
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * @param secs   秒数
     * @param format 时间格式如，yyyy-MM-dd HH:mm:ss
     * @return format格式化的时间字符串
     */
    public static String secsToDateFormat(Integer secs, String format) {
        if (secs == null || secs.equals(0)) {
            return "";
        }
        if (StringUtils.isBlank(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(secs + "000")));
    }

    /**
     * @param mills  毫秒
     * @param format 时间格式如，yyyy-MM-dd HH:mm:ss
     * @return format格式化的时间字符串
     */
    public static String millsToDateFormat(Long mills, String format) {
        if (mills == null || mills.equals(0L)) {
            return "";
        }
        if (StringUtils.isBlank(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(mills));
    }
}

    /**
     * 时间戳转换成Date型
     * @param seconds 精确到秒的字符串
     * @param formatStr
     * @return
     */
    public static Date timeStampToDate(String seconds) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return null;
        }
        return new Date(Long.valueOf(seconds+"000"));
    }



    /**
     * Date型转换成秒级时间戳
     * @param date
     * @return 精确到秒的时间戳 Integer
     */
    public static Integer dateToTimeStamp(Date date) {
        if(date == null) {
            return null;
        }
        Long longtime = date.getTime() / 1000;

        return longtime.intValue();
    }





###日期转换

SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance(); 
        //当前日期
        Date DateToday = new Date();
 
        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);
        Date d = c.getTime();
        String day = format.format(d);
        System.out.println("过去七天："+day);
         
        //过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);
        System.out.println("过去一个月："+mon);
         
         
        //过去一年
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date y = c.getTime();
        String year = format.format(y);
        System.out.println("过去一年："+year);


