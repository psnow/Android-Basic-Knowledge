/**
 *
 * Copyright 2014 TeleNav, Inc. All rights reserved.
 * DialogFragmentActivity.java
 *
 */
package com.telenav.demo.showdialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author Shao Weichao (wchshao@telenav.cn)
 * @date Jan 29, 2014
 */
public class DialogFragmentActivity extends Activity
{

    static enum DialogIds {
        alertDlg
    }
    
    public static boolean execute(Activity ownerActivity) {
        Intent intent = new Intent(ownerActivity, DialogFragmentActivity.class);
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
                AlertDialogFragment messageDlg = new AlertDialogFragment();
                messageDlg.show(getFragmentManager(), DialogIds.alertDlg.name());
                break;
        }
    }
}
