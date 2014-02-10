package com.example.compassapp;

import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.hardware.Sensor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

public class SensorListAdapter implements ListAdapter {

	List<Sensor> deviceSensors;
	
	Context context;
	
	public SensorListAdapter(List<Sensor> deviceSensors, Context context)
	{
		this.deviceSensors = deviceSensors;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return deviceSensors.size();
	}

	@Override
	public Object getItem(int index) {
		return deviceSensors.get(index);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public int getItemViewType(int arg0) {
		return 0;
	}

	@Override
	public View getView(int index, View arg1, ViewGroup arg2) {
		
		TextView view = (TextView)arg1;
		
		if (view == null)
		{
			view = new TextView(context);
		}
		
		view.setText(getDisplayedStr(index));
		
		return view;
	}
	
	private String getDisplayedStr(int index)
	{
		String str = "null";
		if (index >=0 && index < deviceSensors.size())
		{
			Sensor sensor = deviceSensors.get(index);
			str = sensor.getName() + " , type : " + sensor.getType() + " vendor : " + sensor.getVendor();
		}
		
		return str;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver arg0) {

	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver arg0) {

	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public boolean isEnabled(int arg0) {
		return true;
	}

}
