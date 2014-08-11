package com.wyw.biunote.util;

import android.util.Log;

import com.wyw.biunote.base.Config;

public class LogUtils {
	private static final String LOG_PREFIX = "biunote_";
	private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();

	// IllegalArgumentException - is thrown if the tag.length() > 23.
	private static final int MAX_LOG_TAG_LENGTH = 23;

	public static String makeLogTag(String str) {
		if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
			return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH);
		}

		return LOG_PREFIX + str;
	}

	public static String makeLogTag(Class clazz) {
		return makeLogTag(clazz.getSimpleName());
	}

	public static void LOGD(final String tag, String message) {
		boolean loggable = Log.isLoggable(tag, Log.DEBUG);
		LOGE(tag, "isloggable:"+loggable);
		if (Config.IS_DEBUG && Log.isLoggable(tag, Log.DEBUG)) {
			Log.d(tag, message);
		}
	}

	public static void LOGV(final String tag, String message) {
		if (Config.IS_DEBUG && Log.isLoggable(tag, Log.VERBOSE)) {
			Log.v(tag, message);
		}
	}

	public static void LOGI(final String tag, String message) {
		Log.i(tag, message);
	}

	public static void LOGW(final String tag, String message) {
		Log.w(tag, message);
	}

	public static void LOGE(final String tag, String message) {
		Log.e(tag, message);
	}

	private LogUtils() {

	}

}
