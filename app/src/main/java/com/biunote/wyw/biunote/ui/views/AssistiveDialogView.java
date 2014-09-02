package com.biunote.wyw.biunote.ui.views;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.biunote.wyw.biunote.R;

/**
 * Created by wang on 2014/9/2.
 */
public class AssistiveDialogView extends LinearLayout {

    private final Context mContext;
    private OnAssitiveDialogBtnsClickListener mListener;
    private EditText mNoteInputEdittext;

    public AssistiveDialogView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    private void init() {
        inflate(mContext, R.layout.view_assistive_dialog,this);
        mNoteInputEdittext = (EditText) findViewById(R.id.edittext_note);

        findViewById(R.id.btn_close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.onCloseBtnClicked();
                }
            }
        });

        findViewById(R.id.btn_save).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onSaveBtnClicked(mNoteInputEdittext.getText().toString());
                }
            }
        });
    }

    public void setBtnsClickListener(OnAssitiveDialogBtnsClickListener listener){
        mListener = listener;
    }

    public interface OnAssitiveDialogBtnsClickListener{
        public void onCloseBtnClicked();
        public void onSaveBtnClicked(String msgToSave);
    }



}
