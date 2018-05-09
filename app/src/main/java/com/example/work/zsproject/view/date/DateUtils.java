package com.example.work.zsproject.view.date;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class DateUtils {

	public static String splitYMD(String str) {
		if (str.indexOf(" ") > 0) {
			return str.substring(0, str.indexOf(" "));
		}

		return str;
	}

	public static String formatDate(int year, int month, int dayOfMonth) {

		String tempM = month + "";
		if (tempM.length() == 1) {
			tempM = "0" + tempM;
		}
		String tempD = dayOfMonth + "";
		if (tempD.length() == 1) {
			tempD = "0" + tempD;
		}
		return year + "-" + tempM + "-" + tempD;
	}

	public static String formatTime(Integer currentHour, Integer currentMinute) {
		String tempH = currentHour + "";
		if (tempH.length() == 1) {
			tempH = "0" + tempH;
		}

		String tempM = currentMinute + "";
		if (tempM.length() == 1) {
			tempM = "0" + tempM;
		}
		return tempH + ":" + tempM;
	}

	public static String getHM(String str) {
		if (!str.contains(":"))
			return str;
		int dex = str.lastIndexOf(":");
		String newstr = str.substring(0, dex);
		if (newstr.contains(":")) {
			return newstr;
		}
		return str;
	}
	
	/**
	 * 获取消息时间段  间隔一个月
	 */
	public static String getMsgRangTime(){
		Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
		ca.setTime(new Date());
		Date now = ca.getTime();
		ca.add(Calendar.MONTH, -1); //月份减1
		Date lastMonth = ca.getTime(); //结果
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(lastMonth)+"/"+sf.format(now);
	}

	/**
	 *  分钟转毫秒
	 */
	public static long minute2Mill(String minute) {

		try {
			return Long.parseLong(minute) * 60 * 1000;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}


	/**
	 *  日期转毫秒
	 */
	public static long date2mill(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date2 = sdf.parse(date);
			return date2.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 *  日期转毫秒
	 */
	public static long date2mill2(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2 = sdf.parse(date);
			return date2.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}public static long date2mill3(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date2 = sdf.parse(date);
			return date2.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/** 
	 *  毫秒转日期  格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String mill2date(long mill) {
		if(mill==0)
			return "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date(mill);
			return sdf.format(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	/**
	 *  毫秒转日期  格式：yyyy-MM-dd HH:mm
	 */
	public static String mill2date2(long mill) {
		if(mill==0)
			return "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = new Date(mill);
			return sdf.format(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	/**
	 *  毫秒转日期  格式：yyyyMMddHHmmss
	 */
	public static String mill2date3(long mill) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date(mill);
			return sdf.format(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	/**
	 *  毫秒转日期  格式：yyyy-MM-dd
	 */
	public static String mill2date4(long mill) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(mill);
			return sdf.format(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static String formatLongToTimeStr(long mill) {
	  	double d =  mill/1000;
	  	long time = (long) Math.floor(d);
        String timeStr = null;
        long hour = 0;  
        long minute = 0;  
        long second = 0;  
        if (time <= 0)  
            return "00:00:00";  
        else {  
            minute = time / 60;  
            if (minute < 60) {  
                second = time % 60;  
                timeStr = "00"+":"+unitFormat(minute) + ":" + unitFormat(second);  
            } else {  
                hour = minute / 60;  
                minute = minute % 60;  
                second = time - hour * 3600 - minute * 60;  
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);  
            }  
        }  
        return timeStr;  
	   }  
	  
	    public static String unitFormat(long i) {
	        String retStr = null;
	        if (i >= 0 && i < 10)  
	            retStr = "0" + String.valueOf(i);
	        else  
	            retStr = "" + i;  
	        return retStr;  
	    }


	/**
	 * 获取过去任意天内的日期数组
	 * @param intervals      intervals天内
	 * @return              日期数组
	 */
	public static ArrayList<String> getPastFewDates(int intervals ) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		for (int i = intervals-1; i >= 0; i--) {
			pastDaysList.add(getPastDate(i));
		}
		return pastDaysList;
	}

	/**
	 * 获取当日日期
	 * @return
	 */
	public static String getTodayDate(){

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		return sf.format(c.getTime());

	}
	/**
	 * 获取过去第几天的日期
	 *
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}
	/**
	 * 获取将来第几天的日期
	 *
	 * @param future
	 * @return
	 */
	public static String getFutureDate(int future) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + future);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}

	/**
	 * 获取本周开始日期
	 * @return
	 */
	public static String getTimeOfWeekStart(){
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.clear(Calendar.MINUTE);
		ca.clear(Calendar.SECOND);
		ca.clear(Calendar.MILLISECOND);
		ca.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(ca.getTime());
	}

	/**
	 * 获取本周结束日期
	 * @return
	 */
	public static String getTimeOfWeekEnd(){

		String timeOfWeekStart = getTimeOfWeekStart();
		long mill = date2mill3(timeOfWeekStart);
		long endDate = mill+6*24*60*60*1000;
		return mill2date4(endDate);
	}

	/**
	 * 获取本月开始日期
	 * @return
	 */
	public static String getTimeOfMonthStart(){
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.clear(Calendar.MINUTE);
		ca.clear(Calendar.SECOND);
		ca.clear(Calendar.MILLISECOND);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(ca.getTime());
	}

	/**
	 * 获取本月结束日期
	 * @return
	 */
	public static String getTimeOfMonthEnd(){
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String last = format.format(ca.getTime());
		return last;
	}

	/**
	 * 获取本年开始日期
	 * @return
	 */
	public static String getTimeOfYearStart(){
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.clear(Calendar.MINUTE);
		ca.clear(Calendar.SECOND);
		ca.clear(Calendar.MILLISECOND);
		ca.set(Calendar.DAY_OF_YEAR, 1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(ca.getTime());
	}


	/**
	 * 日期相加
	 * @param day 今日日期
	 * @param Num 相加的天数
	 * @return
	 */
	public static String getDatePlus(String day,int Num) {

		if(TextUtils.isEmpty(day)){
			return "";
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate = null;
		try {
			nowDate = df.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date newDate2 = new Date(nowDate.getTime() + (long)Num * 24 * 60 * 60 * 1000);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateOk = simpleDateFormat.format(newDate2);
		return dateOk;
	}
}
