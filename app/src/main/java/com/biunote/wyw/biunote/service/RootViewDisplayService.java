package com.biunote.wyw.biunote.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.biunote.wyw.biunote.IRootViewDisplayServiceInterface;
import com.biunote.wyw.biunote.util.StringUtil;

import java.util.Random;

/**
 * Created by wang on 2014/8/27.
 */
public class RootViewDisplayService extends Service {

    public static final int NOTIFICATION_ID = new Random(System.currentTimeMillis()).nextInt() + 1000;

    @Override
    public void onCreate() {
        super.onCreate();

        startForeground(NOTIFICATION_ID,new Notification());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();

        if (StringUtil.isEmpty(action)){
            return START_STICKY;
        }

        if (action.equals(ROOT_VIEW_DISPLAY_SERVICE_START_ACTION)){

        }else if (action.equals(ROOT_VIEW_DISPLAY_SERVICE_END_ACTION)){

        }else{
            throw new IllegalStateException(TAG+"shouldn't response this action:"+action);
        }

        return START_STICKY;
    }

    private final IRootViewDisplayServiceInterface.Stub mBinder = new IRootViewDisplayServiceInterface.Stub() {
        @Override
        public boolean isRunning() throws RemoteException {
            return false;
        }

        @Override
        public void start() throws RemoteException {

        }

        @Override
        public void end() throws RemoteException {

        }

        @Override
        public void refresh() throws RemoteException {

        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }




    public static final String ROOT_VIEW_DISPLAY_SERVICE_START_ACTION = "com.biutnote.wyw.action.displayservice.start";
    public static final String ROOT_VIEW_DISPLAY_SERVICE_END_ACTION = "com.biutnote.wyw.action.displayservice.end";

    private static final String TAG = RootViewDisplayService.class.getSimpleName();
}
