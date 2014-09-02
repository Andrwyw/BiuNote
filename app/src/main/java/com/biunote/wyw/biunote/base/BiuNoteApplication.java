package com.biunote.wyw.biunote.base;

import android.app.Application;
import android.content.Intent;

import com.biunote.wyw.biunote.service.AssistiveViewDisplayService;
import com.biunote.wyw.biunote.util.L;

/**
 * Created by wang on 2014/9/2.
 */
public class BiuNoteApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        L.d("BiuNoteApplication create.");

        Intent i = new Intent(AssistiveViewDisplayService.ROOT_VIEW_DISPLAY_SERVICE_START_ACTION);
        startService(i);
    }
}
