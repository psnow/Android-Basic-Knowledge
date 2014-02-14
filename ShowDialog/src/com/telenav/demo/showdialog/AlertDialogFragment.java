/**
 *
 * Copyright 2014 TeleNav, Inc. All rights reserved.
 * AlertDialogFragment.java
 *
 */
package com.telenav.demo.showdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * @author Shao Weichao (wchshao@telenav.cn)
 * @date Feb 8, 2014
 */
public class AlertDialogFragment extends DialogFragment
{

    public AlertDialogFragment() {
        // Empty constructor required for DialogFragment
    }
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity(), getTheme()).setTitle("Dialog Title").setMessage("Dialog content").create();
    }
}
