package com.neiyo.demos.activitylaunchmode;

import java.util.Stack;

import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author jpwang
 * @date 2012-8-1
 */
public class TaskInfoDisplayer implements Runnable, Constants {
	private final BaseApplication app;
	private final TextView taskIdField;
	private final LinearLayout taskView;

	public TaskInfoDisplayer(BaseActivity paramBaseActivity) {
		this.app = ((BaseApplication) paramBaseActivity.getApplication());
		this.taskIdField = ((TextView) paramBaseActivity
				.findViewById(R.id.task_id_field));
		this.taskView = ((LinearLayout) paramBaseActivity
				.findViewById(R.id.task_view));
		this.taskView.removeAllViews();
	}

	private ImageView getActivityRepresentation(BaseActivity paramBaseActivity) {
		ImageView localImageView = new ImageView(paramBaseActivity);
		localImageView.setBackgroundResource(paramBaseActivity
				.getBackgroundColour());
		LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT, 10);
		localLayoutParams.setMargins(2, 2, 2, 2);
		localImageView.setLayoutParams(localLayoutParams);
		return localImageView;
	}

	private void showActivityDetails(BaseActivity paramBaseActivity) {
		Log.v("ActivitesLaunchDemo", paramBaseActivity.getClass()
				.getSimpleName());
		ImageView localImageView = getActivityRepresentation(paramBaseActivity);
		this.taskView.addView(localImageView);
	}

	private void showCurrentTaskActivites() {
		Stack localStack = this.app.getCurrentTask();
		Log.v("ActivitesLaunchDemo", "localStack-size : "
				+ (localStack == null ? 0 : localStack.size()));
		taskView.removeAllViews();

		if (localStack == null) {
			return;
		}

		for (int i = localStack.size() - 1;; i--) {
			if (i < 0)
				return;
			showActivityDetails((BaseActivity) localStack.get(i));
		}
	}

	private void showCurrentTaskId() {
		int i = this.app.getCurrentTaskId();
		String str = "Activities in current task (id: " + i + ")";
		this.taskIdField.setText("Task id: " + i);
		Log.v("ActivitesLaunchDemo", str);
	}

	public void run() {
		Log.v("ActivitesLaunchDemo", "===============================");
		showCurrentTaskId();
		showCurrentTaskActivites();
		Log.v("ActivitesLaunchDemo", "===============================");
	}
}

/*
 * Location: E:\Personal\Tool\dex2jar-0.0.9.8\classes_dex2jar.jar Qualified
 * Name: com.novoda.demos.activitylaunchmode.TaskInfoDisplayer JD-Core Version:
 * 0.6.0
 */