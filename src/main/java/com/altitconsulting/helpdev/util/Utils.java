package com.altitconsulting.helpdev.util;

/**
 * Utility class.
 * @author ange-louistoma
 */
public final class Utils {

	/**
	 * Utility class does not have a constructor.
	 */
	private Utils() {
		throw new IllegalArgumentException("Utility class does not have a constructor.");
	}
	
	public static boolean isNotNull(final String s) {
		boolean result = false;
		if (s != null && s.length() > 0) {
			result = true;
		}
		return result;
	}
	
}
