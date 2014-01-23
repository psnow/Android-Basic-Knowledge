package com.neiyo.demos.activitylaunchmode;

import java.util.HashMap;
import java.util.Stack;

import android.app.ActivityManager;
import android.app.Application;

/**
 * @author jpwang
 * @date 2012-8-1
 */
public class BaseApplication extends Application {
	private boolean intentFilterMode;
	private ActivityManager manager;
	private HashMap<Integer, Stack<BaseActivity>> tasks;

	public Stack<BaseActivity> getCurrentTask() {
		return (Stack) this.tasks.get(Integer.valueOf(getCurrentTaskId()));
	}

	public int getCurrentTaskId() {
		return ((ActivityManager.RunningTaskInfo) this.manager.getRunningTasks(
				1).get(0)).id;
	}

	public boolean isIntentFilterMode() {
		return this.intentFilterMode;
	}

	public void onCreate() {
		super.onCreate();
		this.manager = ((ActivityManager) getSystemService("activity"));
		this.tasks = new HashMap();
	}

	public void pushToStack(BaseActivity paramBaseActivity) {
		int i = getCurrentTaskId();
		if (!this.tasks.containsKey(Integer.valueOf(i)))
			this.tasks.put(Integer.valueOf(i), new Stack());
		((Stack) this.tasks.get(Integer.valueOf(i))).add(paramBaseActivity);
	}

	public void removeFromStack(BaseActivity paramBaseActivity) {
		Stack localStack = (Stack) this.tasks.get(Integer
				.valueOf(getCurrentTaskId()));
		if (localStack != null)
			localStack.remove(paramBaseActivity);
	}

	public void toggleIntentFilterMode() {
		if (this.intentFilterMode)
			;
		for (boolean bool = false;; bool = true) {
			this.intentFilterMode = bool;
			return;
		}
	}
}
