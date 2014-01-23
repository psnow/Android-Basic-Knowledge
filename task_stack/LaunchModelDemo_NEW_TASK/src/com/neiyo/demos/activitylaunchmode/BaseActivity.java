package com.neiyo.demos.activitylaunchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * @author jpwang
 * @date 2012-8-1
 */
public abstract class BaseActivity extends Activity implements Constants {
	private BaseApplication app;
	private Handler handler;
	private TextView lifecycle;
	private StringBuilder lifecycleFlow;

	public BaseActivity() {
		this.lifecycleFlow = new StringBuilder();
		this.handler = new Handler();
	}

	private String getLaunchMode() {
		return "[" + hashCode() + "] " + getClass().getSimpleName();
	}

	private String getMethodName() {
		return java.lang.Thread.currentThread().getStackTrace()[4]
				.getMethodName();
	}

	private Intent getNextIntent(View paramView) {
		Object localObject = null;
		int flag = -1;
		switch (paramView.getId()) {
		case R.id.btn_standardA:
			localObject = StandardA.class;
			break;
		case R.id.btn_standardB:
			localObject = StandardB.class;
			break;
		case R.id.btn_TaskAffinityC:
			localObject = TaskAffinityC.class;
			flag = Intent.FLAG_ACTIVITY_NEW_TASK;
			break;
		case R.id.btn_TaskAffinityD:
			localObject = TaskAffinityD.class;
			flag = Intent.FLAG_ACTIVITY_NEW_TASK;
			break;
		case R.id.btn_singleInstance:
			localObject = SingleInstance.class;
			break;
		}
		Intent intent = new Intent(this, (Class) localObject);
		if (flag != -1)
		{
			intent.setFlags(flag);
		}
		return intent;
	}

	private void logMethodName() {
		String str = getMethodName();
		Log.v("ActivitesLaunchDemo", getLaunchMode() + ": " + str);
		this.lifecycleFlow.append(str).append("\n");
		if (this.lifecycle != null)
			this.lifecycle.setText(this.lifecycleFlow.toString());
	}

	private void setupView() {
		findViewById(R.id.main_layout).setBackgroundResource(
				getBackgroundColour());
		((TextView) findViewById(R.id.header)).setText(getLaunchMode());
		this.lifecycle = ((TextView) findViewById(R.id.lifecycle_content));
		this.lifecycle.setMovementMethod(new ScrollingMovementMethod());
	}

	public void generalOnClick(View paramView) {
		startActivity(getNextIntent(paramView));
	}

	public abstract int getBackgroundColour();

	public void onContentChanged() {
		logMethodName();
		super.onContentChanged();
	}

	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		logMethodName();
		setContentView(R.layout.main);
		setupView();
		this.app = ((BaseApplication) getApplication());
		this.app.pushToStack(this);
	}

	public boolean onCreateOptionsMenu(Menu paramMenu) {
		logMethodName();
		getMenuInflater().inflate(R.menu.base_activity, paramMenu);
		return true;
	}

	protected void onDestroy() {
		logMethodName();
		this.app.removeFromStack(this);
		super.onDestroy();
	}

	protected void onNewIntent(Intent paramIntent) {
		logMethodName();
		super.onNewIntent(paramIntent);
		setIntent(paramIntent);
	}

	protected void onPause() {
		logMethodName();
		super.onPause();
	}

	protected void onPostCreate(Bundle paramBundle) {
		logMethodName();
		super.onPostCreate(paramBundle);
	}

	protected void onPostResume() {
		logMethodName();
		super.onPostResume();
	}

	protected void onRestart() {
		logMethodName();
		super.onRestart();
	}

	protected void onRestoreInstanceState(Bundle paramBundle) {
		super.onRestoreInstanceState(paramBundle);
		logMethodName();
	}

	protected void onResume() {
		super.onResume();
		logMethodName();
		TaskInfoDisplayer localTaskInfoDisplayer = new TaskInfoDisplayer(this);
		this.handler.postDelayed(localTaskInfoDisplayer, 1000L);
	}

	public Object onRetainNonConfigurationInstance() {
		logMethodName();
		return super.onRetainNonConfigurationInstance();
	}

	protected void onSaveInstanceState(Bundle paramBundle) {
		super.onSaveInstanceState(paramBundle);
		logMethodName();
	}

	protected void onStart() {
		logMethodName();
		super.onStart();
	}

	protected void onStop() {
		logMethodName();
		super.onStop();
	}
}