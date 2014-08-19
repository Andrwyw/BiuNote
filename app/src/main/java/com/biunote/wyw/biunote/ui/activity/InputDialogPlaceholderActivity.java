package com.biunote.wyw.biunote.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.biunote.wyw.biunote.R;
import com.biunote.wyw.biunote.base.BaseActivity;
import com.biunote.wyw.biunote.database.dao.NoteEntryDataSource;

import static com.biunote.wyw.biunote.util.LogUtils.makeLogTag;


public class InputDialogPlaceholderActivity extends BaseActivity {

	private static final String tag = makeLogTag(InputDialogPlaceholderActivity.class);

    private EditText mContentEditText;
    private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mContext = this;
		setContentView(R.layout.activity_input_dialog_placeholder);

        mContentEditText = (EditText) findViewById(R.id.editText1);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToDb();
            }
        });

        findViewById(R.id.btn_gotoList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoList();
            }
        });
	}

    private void gotoList() {
        Intent intent = NotesListActivity.makeIntent(mContext);
        startActivity(intent);
    }

    private void addToDb() {
        String content = mContentEditText.getText().toString();
        NoteEntryDataSource dataSource = new NoteEntryDataSource(mContext);
        dataSource.open();
        dataSource.addNoteEntry(content);
        dataSource.close();
    }

}
