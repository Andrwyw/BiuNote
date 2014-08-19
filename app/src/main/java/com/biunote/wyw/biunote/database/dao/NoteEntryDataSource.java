package com.biunote.wyw.biunote.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.biunote.wyw.biunote.database.DbOpenHelper;
import com.biunote.wyw.biunote.database.NoteContract.NoteEntry;
import com.biunote.wyw.biunote.model.NoteEntryModel;
import com.biunote.wyw.biunote.util.DbUtils;
import com.biunote.wyw.biunote.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class NoteEntryDataSource {

    private SQLiteDatabase mDb;
    private DbOpenHelper mDbOpenHelper;

    public NoteEntryDataSource(Context context) {
        mDbOpenHelper = new DbOpenHelper(context);
    }

    public void open() {
        mDb = mDbOpenHelper.getWritableDatabase();
    }

    public void close() {
        mDbOpenHelper.close();
    }

    public long addNoteEntry(String content) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteEntry.COLUMN_NAME_CONTENT, content);
        contentValues.put(NoteEntry.COLUMN_NAME_CREATED_TIME, TimeUtils.currentTimeByDefaultFormat());

        if (mDb == null) {
            throw new IllegalStateException("mDb is null!calling this method must be following open()");
        }
        long rowId = mDb.insert(NoteEntry.TABLE_NAME, null, contentValues);

        return rowId;
    }

    public List<NoteEntryModel> getAllNoteEntries() {
        ArrayList<NoteEntryModel> noteEntryModels = new ArrayList<NoteEntryModel>();
        Cursor cursor = getAllNoteWithCursor();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            NoteEntryModel model = cursorToNoteEntryModel(cursor);
            noteEntryModels.add(model);
            cursor.moveToNext();
        }

        cursor.close();;
        return noteEntryModels;
    }

    public Cursor getAllNoteWithCursor(){
        String[] projections = {NoteEntry.COLUMN_NAME_CONTENT
                , NoteEntry.COLUMN_NAME_CREATED_TIME
                , NoteEntry.COLUMN_NAME_ENTRY_ID};

        if (mDb == null) {
            throw new IllegalStateException("mDb is null!calling this method must be following open()");
        }

        Cursor cursor = mDb.query(NoteEntry.TABLE_NAME, projections, null, null, null, null, null);

        return cursor;
    }

    private NoteEntryModel cursorToNoteEntryModel(Cursor cursor){

        NoteEntryModel model = new NoteEntryModel();
        model.setContent(DbUtils.getString(cursor, NoteEntry.COLUMN_NAME_CONTENT));
        model.setId(DbUtils.getString(cursor,NoteEntry.COLUMN_NAME_ENTRY_ID));
        model.setCreatedTime(DbUtils.getString(cursor,NoteEntry.COLUMN_NAME_CREATED_TIME));

        return model;
    }

}