package com.yznu.clinic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    /**
     * 获取当前日期前N个月所在月份
     *
     * @return
     */
    public static String getMonthNDate(int n, String date) {
        Date dNow = convert(date);
        ; // 当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(dNow);// 把当前时间赋给日历
        calendar.add(Calendar.MONTH, n); // 设置为前一月
        dBefore = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); // 设置时间格式
        String defaultEndDate = sdf.format(dBefore); // 格式化当前时间

        return defaultEndDate;
    }

    /**
     * 字符串转换成date类型
     *
     * @param date
     * @return
     */
    public static Date convert(String date) {
        Date retValue = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
            retValue = sdf.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return retValue;
    }

    /**
     * 字符串转换成date类型
     *
     * @param date
     * @return
     */
    public static Date convert2(String date) {
        Date retValue = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            retValue = sdf.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return retValue;
    }

    /**
     * 把yyyymmdd转成yyyy-MM-dd格式
     *
     * @param str
     * @return
     */
    public static String formatDate(String str) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMM");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM");
        String sfstr = "";
        try {
            sfstr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sfstr;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getNowTime() {
        Date dBefore = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
        String defaultEndDate = sdf.format(dBefore); // 格式化当前时间
        return defaultEndDate;
    }

    /**
     * 把yyyymmdd转成yyyy-MM-dd格式
     *
     * @param str
     * @return
     */
    public static String formatYYYYMMDDString(Date date) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        String dateStr = "";
        try {
            dateStr = sf1.format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }
    
    /**
     * 把yyyymmdd转成yyyy-MM-dd格式
     *
     * @param str
     * @return
     */
    public static String formatYYYYMMString(Date date) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMM");
        String dateStr = "";
        try {
            dateStr = sf1.format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }
    
    /**
     * 把yyyymmdd转成yyyy-MM-dd格式
     *
     * @param str
     * @return
     */
    public static String formatPreYYYYMMString(Date date) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMM");
        String dateStr = "";
        try {
        	Calendar c = Calendar.getInstance();
        	c.setTime(date);
        	c.add(Calendar.MONTH, -1);
        	
            dateStr = sf1.format(c.getTime());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }
    
    
    /**
     * 把yyyymmdd转成yyyy-MM-dd格式
     *
     * @param str
     * @return
     */
    public static String formatPreYYYY_MM_DDString(Date date,int interval) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = "";
        try {
        	Calendar c = Calendar.getInstance();
        	c.setTime(date);
        	c.add(Calendar.DAY_OF_MONTH, interval);
        	
            dateStr = sf1.format(c.getTime());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }
    /**
     * 把yyyymmdd转成yyyy-MM-dd格式
     *
     * @param str
     * @return
     */
    public static String formatPreYYYY_MM_DDString(Date date) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = "";
        try {
        	Calendar c = Calendar.getInstance();
        	c.setTime(date);
        	c.add(Calendar.MONTH, -1);
        	
            dateStr = sf1.format(c.getTime());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }
    
    /**
     * 把yyyymmdd转成yyyy-MM-dd格式
     *
     * @param str
     * @return
     */
    public static String formatPreYYYY_MMString(Date date) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM");
        String dateStr = "";
        try {
        	Calendar c = Calendar.getInstance();
        	c.setTime(date);
        	c.add(Calendar.MONTH, -1);
        	
            dateStr = sf1.format(c.getTime());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }
    /**
     * 把yyyymmdd转成yyyy-MM-dd格式
     *
     * @param str
     * @return
     */
    public static String formatYYYYString(Date date) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy");
        String dateStr = "";
        try {
        	Calendar ca = Calendar.getInstance();
        	ca.setTime(date);
        	ca.add(Calendar.MONTH, -1);
            dateStr = sf1.format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }
    
    /**
     * 把yyyymmdd转成yyyy-MM-dd格式
     *
     * @param str
     * @return
     */
    public static String formatPreYYYYString(Date date) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy");
        String dateStr = "";
        try {
        	Calendar ca = Calendar.getInstance();
        	ca.setTime(date);
        	ca.add(Calendar.YEAR, -1);
            dateStr = sf1.format(ca.getTime());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }


    /**
     * 把yyyymmdd转成yyyy-MM-dd格式
     *
     * @param str
     * @return
     */
    public static String formatYYYMMDDString(String dateStr) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMMdd");
        String returnStr = "";
        try {
            Date date = sf1.parse(dateStr);
            returnStr = sf2.format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return returnStr;
    }

    /**
     * 获取当前时间后一年的时间
     */
    public static String getAfterYearDate() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
//        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.add(Calendar.YEAR, +1);
        date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
        String defaultEndDate = sdf.format(date); // 格式化当前时间
        return defaultEndDate;
    }

    /**
     * 获取当前时间后半年的时间
     */
    public static String getAfterSixMonthDate() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
//        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.add(Calendar.MONTH, +6);
        date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式
        String defaultEndDate = sdf.format(date); // 格式化当前时间
        return defaultEndDate;
    }

    /**
     * 获取当前日期,format格式例如YYYYMMdd
     *
     * @return
     */
    public static String getDayDate(Date dNow,String format) {
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(dNow);// 把当前时间赋给日历
        dBefore = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format); // 设置时间格式
        String defaultEndDate = sdf.format(dBefore); // 格式化当前时间

        return defaultEndDate;
    }

    /**
     * 获取当前日期所在月份
     *
     * @return
     */
    public static String getMonthDate(Date dNow) {
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(dNow);// 把当前时间赋给日历
        dBefore = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); // 设置时间格式
        String defaultEndDate = sdf.format(dBefore); // 格式化当前时间

        return defaultEndDate;
    }

    /**
     * 获取传入日期所在月份
     *
     * @return
     */
    public static String getThisMonthDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dNow = null;
        try {
            dNow = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getMonthDate(dNow);
    }

    /**
     * 获取当前日期后N个月所在月份
     *
     * @return
     */
    public static String getMonthNDate(int n) {
        Date dNow = new Date(); // 当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(dNow);// 把当前时间赋给日历
        calendar.add(Calendar.MONTH, n); // 设置为后一月
        dBefore = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); // 设置时间格式
        String defaultEndDate = sdf.format(dBefore); // 格式化当前时间

        return defaultEndDate;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getNowTimeByhhmmss() {
        Date dBefore = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式
        String defaultEndDate = sdf.format(dBefore); // 格式化当前时间
        return defaultEndDate;
    }
}
