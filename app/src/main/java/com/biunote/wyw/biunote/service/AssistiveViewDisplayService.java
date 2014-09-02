package com.biunote.wyw.biunote.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.biunote.wyw.biunote.IRootViewDisplayServiceInterface;
import com.biunote.wyw.biunote.helper.AssistiveTouchViewHelper;
import com.biunote.wyw.biunote.util.L;
import com.biunote.wyw.biunote.util.StringUtil;

import java.util.Random;

/**
 * Created by wang on 2014/8/27.
 */
public class AssistiveViewDisplayService extends Service {

    public static final int NOTIFICATION_ID = new Random(System.currentTimeMillis()).nextInt() + 1000;
    private AssistiveTouchViewHelper mAssitiveTouchViewHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        L.d("assistiveViewDisplay service created!");
        mAssitiveTouchViewHelper = AssistiveTouchViewHelper.getInstance(this);
        startForeground(NOTIFICATION_ID,new Notification());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.d("display service onStartCommand.");
        String action = intent.getAction();

        if (StringUtil.isEmpty(action)){
            return START_STICKY;
        }

        if (action.equals(ROOT_VIEW_DISPLAY_SERVICE_START_ACTION)){
            startAssistiveViewDisplayService();
        }else if (action.equals(ROOT_VIEW_DISPLAY_SERVICE_END_ACTION)){
            stopAssistiveViewDisplayService();
        }else{
            throw new IllegalStateException(TAG+"shouldn't response this action:"+action);
        }

        return START_STICKY;
    }

    private void stopAssistiveViewDisplayService() {
        mAssitiveTouchViewHelper.removeView();
    }

    private void startAssistiveViewDisplayService() {
        L.d("startAssistiveViewDisplayService");
        mAssitiveTouchViewHelper.showTouchDotView();
    }

    private final IRootViewDisplayServiceInterface.Stub mBinder = new IRootViewDisplayServiceInterface.Stub() {
        @Override
        public boolean isRunning() throws RemoteException {
            return false;
        }

        @Override
        public void start() throws RemoteException {
            startAssistiveViewDisplayService();
        }

        @Override
        public void end() throws RemoteException {
            stopAssistiveViewDisplayService();
        }

        @Override
        public void refresh() throws RemoteException {
//            mAssitiveTouchViewHelper.refresh();
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }



    public static final String ROOT_VIEW_DISPLAY_SERVICE_START_ACTION = "com.biutnote.wyw.action.displayservice.start";
    public static final String ROOT_VIEW_DISPLAY_SERVICE_END_ACTION = "com.biutnote.wyw.action.displayservice.end";

    private static final String TAG = AssistiveViewDisplayService.class.getSimpleName();
}
