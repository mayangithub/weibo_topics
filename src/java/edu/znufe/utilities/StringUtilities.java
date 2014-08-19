package edu.znufe.utilities;

import java.util.Date;

/**
 * This class handles general utilities and operations involving strings
 * 
 * @author Dmitriy Babichenko
 * @version 1.1
 */
public class StringUtilities {
	/**
	 * Takes current date, separates month, day, and year with underscores and adds a file extension
	 * @param extension - Extension of a file, usually a .txt
	 * @return File name
	 */
	public static String dateToFilename(String extension) {
		Date tempDate = new Date();
		return tempDate.getMonth() + "_" + tempDate.getDay() + "_" + tempDate.getYear() + "." + extension;
	}

	/**
	 * Checks if a String varialbe consists only of numbers.  For example, if a String variable contains "1234" (only numbers),
	 * this method will return "true".  If a String variable contains even one non-numeric character - "123x4" - this method
	 * will return false. 
	 * @param str - String variable - we need to check if it can be converted to a number without an error
	 * @return boolean (true/false)
	 */
	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} 
		catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
