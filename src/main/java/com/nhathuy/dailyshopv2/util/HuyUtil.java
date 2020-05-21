package com.nhathuy.dailyshopv2.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HuyUtil {
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean isEmailValid(String emailStr) {
		return VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr).find();
	}

	public String numFormat(String value) {
		DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
		return decimalFormat.format(Double.parseDouble(value));
	}

	// year - month - day
	public String dateToString(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		date = Calendar.getInstance().getTime();
		return df.format(date);
	}

//	// year - month - day
//	public java.sql.Date getCurrentTimeStamp() {
//		return new java.sql.Date(Calendar.getInstance().getTime().getTime());
//	}
}
