package com.javaweb.utils;

public class NumberUtil {
	public static boolean isNumber(String value) {
		try {
			Integer number = Integer.parseInt(value);
		}catch(NumberFormatException ex){
			return false;
		}
		return true;
	}
}
