package com.biunote.wyw.biunote.ui.views;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.biunote.wyw.biunote.R;

/**
 * Created by wang on 2014/8/27.
 */
public class TouchDotView extends LinearLayout{


    private Context mContext;
    private GestureDetector mGestureDetector;
    private ImageView mDotImgView;
    private OnTouchDotViewListener mOnTouchDotViewlistener;

    public TouchDotView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    private void init() {
        mGestureDetector = new GestureDetector(mContext,mOnGestureListener);
        inflate(mContext, R.layout.view_touch_dot_view_layout, this);
        mDotImgView = (ImageView) findViewById(R.id.img_dot);
    }


    GestureDetector.SimpleOnGestureListener mOnGestureListener = new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (mOnTouchDotViewlistener != null){
                int w = getWidth()/2;
                int h = getHeight()/2;
                int x = (int) (e2.getRawX() - w);
                int y = (int) (e2.getRawY() - h);
                mOnTouchDotViewlistener.scrollTo(TouchDotView.this, x,y);
                return true;
            }else {
                return false;
            }
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            mDotImgView.setImageResource(R.drawable.ic_touch_dot_pressed);
            if (mOnTouchDotViewlistener != null){
                mOnTouchDotViewlistener.onSingleTap(TouchDotView.this);
                return true;
            }else {
                return false;
            }

        }
//TODO wang 2014/8/27 why not use this method.
//        @Override
//        public boolean onSingleTapUp(MotionEvent e) {
//            return super.onSingleTapUp(e);
//        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = mGestureDetector.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_UP){
            mDotImgView.setImageResource(R.drawable.ic_touch_dot);
            if (mOnTouchDotViewlistener != null){
                int w = getWidth()/2;
                int h = getHeight() / 2;
                int x = (int) (event.getRawX() - w);
                int y = (int) (event.getRawY() - h);
                mOnTouchDotViewlistener.onTouchUp(this, x,y);
            }
        }

        return result;
    }

    public void setOnTouchDotViewLister(OnTouchDotViewListener listener){
        mOnTouchDotViewlistener = listener;
    }

    public abstract interface OnTouchDotViewListener {
        public abstract void scrollTo(View v,int x,int y);
        public abstract void onTouchUp(View v,int x, int y);
        public abstract void onSingleTap(View view);
    }

    private static final String TAG = TouchDotView.class.getSimpleName();
}
