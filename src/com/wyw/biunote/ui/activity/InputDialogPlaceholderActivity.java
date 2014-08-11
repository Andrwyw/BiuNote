package com.wyw.biunote.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.wyw.biunote.R;

import static com.wyw.biunote.util.LogUtils.LOGD;
import static com.wyw.biunote.util.LogUtils.makeLogTag;
import static com.wyw.biunote.util.LogUtils.LOGE;

public class InputDialogPlaceholderActivity extends Activity {

	private static final String tag = makeLogTag(InputDialogPlaceholderActivity.class);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_dialog_placeholder);
		
		LOGD(tag, "tag length:" + tag.length());
		LOGE(tag, "tag length:" + tag.length());
		LOGE(tag, "tag:" + tag);
	}

}
