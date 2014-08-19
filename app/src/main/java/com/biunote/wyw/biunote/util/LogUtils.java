package com.biunote.wyw.biunote.util;

import android.util.Log;

import com.biunote.wyw.biunote.base.Config;


public class LogUtils {
	private static final String LOG_PREFIX = "bn_";
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
		if (Config.IS_DEBUG && Log.isLoggable(tag, Log.DEBUG)) {
			Log.d(tag, message);
		}
	}

    /**
     *
     * adb shell setprop log.tag.<TAGNAME> VERBOSE or SystemProperties.set(PROPERTY_TAG, "VERBOSE");
     *
     * @param tag
     * @param message
     */
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
