package com.biunote.wyw.biunote.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.biunote.wyw.biunote.R;
import com.biunote.wyw.biunote.base.BaseActivity;
import com.biunote.wyw.biunote.database.dao.NoteEntryDataSource;
import com.biunote.wyw.biunote.model.NoteEntryModel;
import com.biunote.wyw.biunote.ui.adapter.NotesListAdapter;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingRightInAnimationAdapter;

import java.util.List;


/**
 * Created by wang on 2014/8/19.
 */
public class NotesListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        ListView listView = (ListView) findViewById(R.id.listView);

        NoteEntryDataSource dataSource = new NoteEntryDataSource(this);
        dataSource.open();
        List<NoteEntryModel> allNoteEntries = dataSource.getAllNoteEntries();
        dataSource.close();

        NotesListAdapter notesListAdapter = new NotesListAdapter(this, allNoteEntries);
        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(notesListAdapter);

        SwingRightInAnimationAdapter rightInAnimationAdapter = new SwingRightInAnimationAdapter(scaleInAnimationAdapter);
        rightInAnimationAdapter.setAbsListView(listView);

//        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(scaleInAnimationAdapter);
//        alphaInAnimationAdapter.setAbsListView(listView);
        listView.setAdapter(rightInAnimationAdapter);
    }


    public static Intent makeIntent(final Context context){
        final Intent intent = new Intent(context,NotesListActivity.class);

        return intent;
    }
}
