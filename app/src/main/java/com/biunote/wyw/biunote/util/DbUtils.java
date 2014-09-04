package com.biunote.wyw.biunote.util;

import android.database.Cursor;

/**
 * Created by wang on 2014/8/17.
 */
public class DbUtils {

    public static String getString(Cursor cursor,String columnName){
        int columnIndex = cursor.getColumnIndex(columnName);
        String cursorString = cursor.getString(columnIndex);
        return cursorString;
    }

    public static long getLong(Cursor cursor, String columnName){
        int columnIndex = cursor.getColumnIndex(columnName);
        long cursorLong = cursor.getLong(columnIndex);

        return cursorLong;
    }
}
