package com.example.bug;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment implements LoaderManager.LoaderCallbacks<Integer> {
    private static class IntegerArrayLoader extends AsyncTaskLoader<Integer> {

        private Integer result = null;
        
        public IntegerArrayLoader(Context context) {
            super(context);
            Log.i("CHEOK", "IntegerArrayLoader created!");
        }

        @Override
        public Integer loadInBackground() {
            Log.i("CHEOK", "Time start consuming !");
            this.result = 123456;
            
            int i = 0;
            while (i < 5)
            {
            	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	i ++;
            	Log.i("CHEOK", "Time consuming " + i + " second");
            }
            
            return result;
        }
        
        /**
         * Handles a request to cancel a load.
         */
        @Override 
        public void onCanceled(Integer integer) {
            super.onCanceled(integer);
        }
        
        /**
         * Handles a request to stop the Loader.
         * Automatically called by LoaderManager via stopLoading.
         */
        @Override 
        protected void onStopLoading() {
            // Attempt to cancel the current load task if possible.
            cancelLoad();
        }
        
        /**
         * Handles a request to start the Loader.
         * Automatically called by LoaderManager via startLoading.
         */
        @Override        
        protected void onStartLoading() {
            if (this.result != null) {
                deliverResult(this.result);
            }
            
            if (takeContentChanged() || this.result == null) {
                forceLoad();
            }
        }
        
        /**
         * Handles a request to completely reset the Loader.
         * Automatically called by LoaderManager via reset.
         */
        @Override 
        protected void onReset() {
            super.onReset();

            // Ensure the loader is stopped
            onStopLoading();

            // At this point we can release the resources associated with 'apps'
            // if needed.
            this.result = null;
        }        
    }
    
    @Override
    public Loader<Integer> onCreateLoader(int arg0, Bundle arg1) {
        Log.i("CHEOK", "onCreateLoader being called");
        return new IntegerArrayLoader(this.getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Integer> arg0, Integer arg1) {
        result = arg1;
        Log.i("CHEOK", "onLoadFinished --> " + this);
    }

    @Override
    public void onLoaderReset(Loader<Integer> arg0) {
        // TODO Auto-generated method stub
    	Log.i("CHEOK", "onLoaderReset --> " + this);
    }
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        Log.i("CHEOK", "onCreateView, Fragment --> " + this);
        return v;
    }
    
    // http://stackoverflow.com/questions/11293441/android-loadercallbacks-onloadfinished-called-twice
    @Override
    public void onResume()
    {
        super.onResume();
        
        if (result == null) {
            // Prepare the loader.  Either re-connect with an existing one,
            // or start a new one.
            getLoaderManager().initLoader(0, null, this);
        } else {
            // Restore from previous state. Perhaps through long pressed home
            // button.
        }
    }    
    
    private Integer result;
}
