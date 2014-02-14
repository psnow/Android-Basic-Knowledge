/**
 *
 * Copyright 2014 TeleNav, Inc. All rights reserved.
 * UnmanagedDialogActivity.java
 *
 */
package com.telenav.demo.showdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author Shao Weichao (wchshao@telenav.cn)
 * @date Jan 29, 2014
 */
public class UnmanagedDialogActivity extends Activity
{

    public static boolean execute(Activity ownerActivity) {
        Intent intent = new Intent(ownerActivity, UnmanagedDialogActivity.class);
        ownerActivity.startActivity(intent);
        return true;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.showDialogBtn:
                new AlertDialog.Builder(this).setTitle("Dialog Title").setMessage("Dialog content").create().show();
                break;
        }
    }
}
