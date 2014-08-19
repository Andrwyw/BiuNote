package com.biunote.wyw.biunote.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wang on 2014/8/14.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "biunote.db";
    public static final int DB_VERSION = 1;

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_NOTE_ENTRY_TABLE);
    }

    /**
     * we do nothing here!
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public static final String SQL_CREATE_NOTE_ENTRY_TABLE = "create table if not exists " +
            NoteContract.NoteEntry.TABLE_NAME + " ( " +
            NoteContract.NoteEntry.COLUMN_NAME_CONTENT + " text not null , " +
            NoteContract.NoteEntry.COLUMN_NAME_CREATED_TIME + " text , " +
            NoteContract.NoteEntry.COLUMN_NAME_DATA1 + " text , " +
            NoteContract.NoteEntry.COLUMN_NAME_DATA2 + " text , " +
            NoteContract.NoteEntry.COLUMN_NAME_DATA3 + " text , " +
            NoteContract.NoteEntry.COLUMN_NAME_DATA4 + " text , " +
            NoteContract.NoteEntry.COLUMN_NAME_DATA5 + " text , " +
            NoteContract.NoteEntry.COLUMN_NAME_ENTRY_ID + " integer primary key autoincrement , " +
            NoteContract.NoteEntry.COLUMN_NAME_SUB_TITLE + " text , " +
            NoteContract.NoteEntry.COLUMN_NAME_TITLE + " text " +
            " ) ";
}

