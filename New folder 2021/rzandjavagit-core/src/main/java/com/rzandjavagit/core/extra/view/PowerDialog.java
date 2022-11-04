package com.rzandjavagit.core.extra.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

public class PowerDialog {
    private Activity activity = null;
    private Context context = null;
    private Dialog dialog = null;
    private View rootRowView = null;

    public PowerDialog(Activity argActivity) {
        activity = argActivity;
        dialog = new Dialog(activity);
    }

    public PowerDialog removeDialogTitleBar() {
        if (dialog == null) {
            return this;
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return this;
    }

    public PowerDialog isCancelable(boolean argIsCancelable) {
        if (dialog == null) {
            return this;
        }
        dialog.setCancelable(argIsCancelable);
        dialog.setCanceledOnTouchOutside(argIsCancelable);
        return this;
    }

    public View setContentView(Context argContext, int argResourceId) {
        context = argContext;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootRowView = inflater.inflate(argResourceId, null, false);
        //dialog.setContentView(argResourceId);
        dialog.setContentView(rootRowView.getRootView());
        return rootRowView;
    }

    public PowerDialog setOnClickListener(View argView, View.OnClickListener argOnClickListener) {
        if (dialog == null) {
            return this;
        }
        argView.setOnClickListener(argOnClickListener);
        return this;
    }

    public PowerDialog setOnClickListener(int argResourceId, View.OnClickListener argOnClickListener) {
        if (dialog == null || rootRowView == null) {
            return this;
        }
        rootRowView.findViewById(argResourceId).setOnClickListener(argOnClickListener);
        return this;
    }

    public void show() {
        dialog.show();
    }

    public boolean isShowing() {
        if (dialog == null) {
            return false;
        }
        return dialog.isShowing();
    }

    public void dismiss() {
        if (dialog == null) {
            return;
        }
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}