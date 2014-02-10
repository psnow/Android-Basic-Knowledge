package com.example.compassapp;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ListView;

public class SensorsListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sensors);
		
		SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		
		ListView listView = (ListView)this.findViewById(R.id.list_view);
		
		List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
		
		listView.setAdapter(new SensorListAdapter(deviceSensors, this));
	}

}
