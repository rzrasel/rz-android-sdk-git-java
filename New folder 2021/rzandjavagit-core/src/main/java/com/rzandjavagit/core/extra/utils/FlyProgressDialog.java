package com.rzandjavagit.core.extra.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.rzandjavagit.core.R;

public class FlyProgressDialog {
    private Activity activity;
    private Context context;
    private AlertDialog alertDialog;
    private AlertDialog.Builder dialogBuilder;
    private TextView sysTextViewMessage;
    private String dialogMessage = "";
    private boolean isCancelable = false;

    public FlyProgressDialog() {
    }

    public FlyProgressDialog(Context argContext) {
        context = argContext;
    }

    private void show_01() {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public void show() {
        dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.fly_progress_dialog, null);
        sysTextViewMessage = (TextView) dialogView.findViewById(R.id.sysTextViewMessage);
        sysTextViewMessage.setText(dialogMessage + "");
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(isCancelable);
        alertDialog = dialogBuilder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setCancelable(isCancelable);
        alertDialog.setCanceledOnTouchOutside(isCancelable);
        alertDialog.show();
    }

    public FlyProgressDialog setMessage(String argDialogMessage) {
        dialogMessage = argDialogMessage;
        return this;
    }

    public FlyProgressDialog setCancelable(boolean argIsCancelable) {
        isCancelable = argIsCancelable;
        return this;
    }

    public void dismiss() {
        if (alertDialog == null) {
            return;
        }
        if (alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    public boolean isShowing() {
        if (alertDialog == null) {
            return false;
        }
        return alertDialog.isShowing();
    }
}