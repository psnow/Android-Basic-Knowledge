package com.example.bug;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ChildActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
    }
}