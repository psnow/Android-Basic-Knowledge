package com.db.loader;

import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LoaderTest extends ActionBarActivity implements LoaderManager.LoaderCallbacks<String> {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader_test);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        getLoaderManager().initLoader(0, null, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.loader_test, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            getLoaderManager().getLoader(0).cancelLoad();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new MyLoader(this, LoaderTest.this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_loader_test, container, false);
            return rootView;
        }

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    static class MyLoader extends AsyncTaskLoader<String> {

        LoaderTest activity;

        public MyLoader(Context context, LoaderTest activity) {
            super(context);
            this.activity = activity;
        }

        @Override
        public void deliverResult(String data) {
            super.deliverResult(data);

            TextView helloWorld = (TextView) activity.findViewById(R.id.hello_world);

            helloWorld.setText(data);
        }

        @Override
        protected void onStartLoading() {
            forceLoad();
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void cancelLoadInBackground() {
            Log.d("DB-Test", "cancelLoadInBackground....");
            super.cancelLoadInBackground();
        }

        @Override
        public void onCanceled(String data) {
            Log.d("DB-Test", "onCanceled...." + data);
            super.onCanceled(data);
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        protected boolean onCancelLoad() {
            Log.d("DB-Test", "onCancelLoad....");
            return super.onCancelLoad();
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public String loadInBackground() {

            Log.d("DB-Test", "startloading....");
            int i = 0;

            while (i++ < 10) {
                try {
                    synchronized (this) {
                        this.wait(1000);
                        Log.d("DB-Test", "sleep for " + i + " s");

                        if (isLoadInBackgroundCanceled()) {
                            Log.d("DB-Test", "sleep for " + i + " s");
                            return "Cancelled";
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "Finished";
        }
    }

}
