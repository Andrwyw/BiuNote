package com.biunote.wyw.biunote.database;

import android.provider.BaseColumns;

public final class NoteContract{

    public NoteContract(){
    }

    public static abstract class NoteEntry implements BaseColumns{

        public static final String TABLE_NAME = "noteEntry";

        public static final String COLUMN_NAME_ENTRY_ID = "noteId";
        public static final String COLUMN_NAME_TITLE = "noteTitle";
        public static final String COLUMN_NAME_SUB_TITLE = "noteSubTitle";
        public static final String COLUMN_NAME_CONTENT = "noteContent";
        public static final String COLUMN_NAME_CREATED_TIME = "createdTime";

        public static final String COLUMN_NAME_DATA1 = "data1";
        public static final String COLUMN_NAME_DATA2 = "data2";
        public static final String COLUMN_NAME_DATA3 = "data3";
        public static final String COLUMN_NAME_DATA4 = "data4";
        public static final String COLUMN_NAME_DATA5 = "data5";
    }
}