package com.example.compassapp;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements SensorEventListener {

	// define the display assembly compass picture
	private ImageView image;

	// record the compass picture angle turned
	private float currentDegree = 0f;

	// device sensor manager
	private SensorManager mSensorManager;

	TextView tvHeading;
	TextView tvX;
	TextView tvY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// our compass image
		image = (ImageView) findViewById(R.id.imageViewCompass);

		// TextView that will tell the user what degree is he heading
		tvHeading = (TextView) findViewById(R.id.tvHeading);
		tvX = (TextView) findViewById(R.id.tvX);
		tvY = (TextView) findViewById(R.id.tvY);

		// initialize your android device sensor capabilities
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
        case R.id.action_settings:
        	listSensors();
            return true;
        default:
            return super.onOptionsItemSelected(item);
		}
    }

	private void listSensors() {
		Intent intent = new Intent(this, SensorsListActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		// for the system's orientation sensor registered listeners
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		// to stop the listener and save battery
		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		// get the angle around the z-axis rotated
		float degree = Math.round(event.values[0]);
		float degree_X = Math.round(event.values[1]);
		float degree_Y = Math.round(event.values[2]);

		tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");
		tvX.setText("X : " + Float.toString(degree_X) + " degrees");
		tvY.setText("Y : " + Float.toString(degree_Y) + " degrees");

		// create a rotation animation (reverse turn degree degrees)
		RotateAnimation ra = new RotateAnimation(
				currentDegree, 
				-degree,
				Animation.RELATIVE_TO_SELF, 0.5f, 
				Animation.RELATIVE_TO_SELF,
				0.5f);

		// how long the animation will take place
		ra.setDuration(210);

		// set the animation after the end of the reservation status
		ra.setFillAfter(true);

		// Start the animation
		image.startAnimation(ra);
		currentDegree = -degree;

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// not in use
	}
}
