package com.rummycircle.utils;

import java.util.Calendar;

import org.mortbay.log.Log;

public class Utility {
	
	public static Calendar getTodaysDate(){
		Calendar today = Calendar.getInstance();
		return today;
	}
	
	public static int getTodaysDayOfMonth(){
		return getTodaysDate().get(Calendar.DAY_OF_MONTH);
	}
	
	public static int getIncrementedDay(int value){
		Calendar today = getTodaysDate();
		today.add(Calendar.DAY_OF_MONTH, value);
		Log.info(String.format("Incremented value %s", today.get(Calendar.DAY_OF_MONTH)));
		return today.get(Calendar.DAY_OF_MONTH);
	}

}
