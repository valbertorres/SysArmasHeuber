package com.heuber.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilData {

	public UtilData() {
	}

	public static boolean validatData(String data) {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date = formato.parse(data);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

}
