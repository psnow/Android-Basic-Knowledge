package com.telenav.demo.testjni;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener
{

    private TextView tvDisplay;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
    }

    private void setUpViews()
    {
        Button btn = (Button) findViewById(R.id.btn_returnInt);
        btn.setOnClickListener(this);
        tvDisplay = (TextView) findViewById(R.id.tv_display);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_returnInt:
                int result = Game.getInstance().returnInt();
                tvDisplay.setText(Integer.toString(result));
                break;

            default:
                break;
        }
        
    }

}
