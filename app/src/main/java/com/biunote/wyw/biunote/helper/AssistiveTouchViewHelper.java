package com.biunote.wyw.biunote.helper;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.biunote.wyw.biunote.database.dao.NoteEntryDataSource;
import com.biunote.wyw.biunote.ui.views.AssistiveDialogView;
import com.biunote.wyw.biunote.ui.views.TouchDotView;
import com.biunote.wyw.biunote.util.L;


/**
 * Created by wang on 2014/8/27.
 *
 * 辅助类，控制AssitiveTouchView在window上的显示与隐藏.
 */

public class AssistiveTouchViewHelper {

    private static Object mLock = new Object();
    private static AssistiveTouchViewHelper mAssistiveTouchViewHelper;
    private final Context mContext;

    public static AssistiveTouchViewHelper getInstance(Context context){
        synchronized (mLock) {
            if (mAssistiveTouchViewHelper == null) {
                mAssistiveTouchViewHelper = new AssistiveTouchViewHelper(context);
            }

            return mAssistiveTouchViewHelper;
        }
    }


    private WindowManager.LayoutParams mTouchDotParams = null;
    private WindowManager.LayoutParams mDialogViewParams = null;
    private WindowManager mWindowManager;
    private TouchDotView mTouchDotView;
    private AssistiveDialogView mDialogView;

    private int mCurrentShowingType = 0;
    public static final int ASSISTIVE_TOUCH_VIEW_TYPE_DOT = 1;
    public static final int ASSISTIVE_TOUCH_VIEW_TYPE_DIALOG = 2;

    private AssistiveTouchViewHelper(Context context) {
        mContext = context;

        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        setupLayoutParams();
    }

    private void setupLayoutParams() {
        mTouchDotParams = new WindowManager.LayoutParams();
        mTouchDotParams.type = LayoutParams.TYPE_PRIORITY_PHONE;
        mTouchDotParams.format = PixelFormat.RGBA_8888;
        //LayoutParams.FLAG_NOT_TOUCH_MODAL|  <-  FLAG_NOT_FOCUSABLE enable the left.
        mTouchDotParams.flags =  LayoutParams.FLAG_NOT_FOCUSABLE;
        mTouchDotParams.gravity = Gravity.LEFT | Gravity.TOP;

        mTouchDotParams.x = 0;
        mTouchDotParams.y = 0;
        mTouchDotParams.width = LayoutParams.WRAP_CONTENT;
        mTouchDotParams.height = LayoutParams.WRAP_CONTENT;

        mDialogViewParams = new WindowManager.LayoutParams();
        mDialogViewParams.type = LayoutParams.TYPE_PRIORITY_PHONE;
        mDialogViewParams.format = PixelFormat.RGBA_8888;
        mDialogViewParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL;
        mDialogViewParams.gravity = Gravity.CENTER;
        mDialogViewParams.width = LayoutParams.WRAP_CONTENT;
        mDialogViewParams.height = LayoutParams.WRAP_CONTENT;
    }

    public void removeView(){
        if (mWindowManager != null) {
            mWindowManager.removeView(mTouchDotView);
        }
    }

    public void showTouchDotView(){
        if (mTouchDotView == null) {
            createTouchDotView();
        }

        if (mCurrentShowingType == ASSISTIVE_TOUCH_VIEW_TYPE_DOT){
            return;
        }else if (mCurrentShowingType == ASSISTIVE_TOUCH_VIEW_TYPE_DIALOG){
            mWindowManager.removeView(mDialogView);
        }

        mWindowManager.addView(mTouchDotView,mTouchDotParams);
        mCurrentShowingType = ASSISTIVE_TOUCH_VIEW_TYPE_DOT;
    }

    public void showDialogView(){
        if (mDialogView == null){
            createDialogView();
        }

        if (mCurrentShowingType == ASSISTIVE_TOUCH_VIEW_TYPE_DIALOG){
            return;
        }else if(mCurrentShowingType == ASSISTIVE_TOUCH_VIEW_TYPE_DOT){
            mWindowManager.removeView(mTouchDotView);
        }

        mWindowManager.addView(mDialogView,mDialogViewParams);
        mCurrentShowingType = ASSISTIVE_TOUCH_VIEW_TYPE_DIALOG;
    }

    private void createDialogView() {
        mDialogView = new AssistiveDialogView(mContext);
        mDialogView.setBtnsClickListener(new AssistiveDialogView.OnAssitiveDialogBtnsClickListener() {
            @Override
            public void onCloseBtnClicked() {
                showTouchDotView();
            }

            @Override
            public void onSaveBtnClicked(String msgToSave) {
                L.toast(mContext,msgToSave);
                addToDb(msgToSave);
                showTouchDotView();
            }
        });
    }

    private void addToDb(String msgToSave) {
        NoteEntryDataSource dataSource = new NoteEntryDataSource(mContext);
        dataSource.open();
        dataSource.addNoteEntry(msgToSave);
        dataSource.close();
    }

    private void createTouchDotView() {
        mTouchDotView = new TouchDotView(mContext.getApplicationContext());
        mTouchDotView.setOnTouchDotViewLister(mTouchDotViewListener);
    }

    private TouchDotView.OnTouchDotViewListener mTouchDotViewListener = new TouchDotView.OnTouchDotViewListener() {
        @Override
        public void scrollTo(View v, int x, int y) {
            mTouchDotParams.x = x;
            mTouchDotParams.y = y;
            mWindowManager.updateViewLayout(v,mTouchDotParams);
        }

        @Override
        public void onTouchUp(View v, int x, int y) {
        }

        @Override
        public void onSingleTap(View view) {
            showDialogView();
        }
    };

    private static final String TAG = AssistiveTouchViewHelper.class.getSimpleName();
}
