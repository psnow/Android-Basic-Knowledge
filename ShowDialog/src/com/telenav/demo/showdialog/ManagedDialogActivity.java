/**
 *
 * Copyright 2014 TeleNav, Inc. All rights reserved.
 * ManagedDialog.java
 *
 */
package com.telenav.demo.showdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author Shao Weichao (wchshao@telenav.cn)
 * @date Jan 29, 2014
 */
public class ManagedDialogActivity extends Activity
{
    static enum DialogIds {
        alertDlg
    }
    
    public static boolean execute(Activity ownerActivity) {
        Intent intent = new Intent(ownerActivity, ManagedDialogActivity.class);
        ownerActivity.startActivity(intent);
        return true;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    @SuppressWarnings("deprecation")
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.showDialogBtn:
                showDialog(DialogIds.alertDlg.ordinal());
                break;
        }
    }
    
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        if (id == DialogIds.alertDlg.ordinal()) {
            return new AlertDialog.Builder(this).setTitle("Dialog Title").setMessage("Dialog content").create();
        } else {
            return null;
        }
    }

}
