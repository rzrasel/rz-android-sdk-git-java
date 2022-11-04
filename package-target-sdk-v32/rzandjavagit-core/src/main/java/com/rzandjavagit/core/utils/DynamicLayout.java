package com.rzandjavagit.core.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@Deprecated
public class DynamicLayout {
    public static View addLayout(Context argContext, int argLayoutResourceId, ViewGroup argRootView) {
        //Dynamically
        LayoutInflater inflater = (LayoutInflater) argContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View childView = inflater.inflate(argLayoutResourceId, null);
        argRootView.removeAllViews();
        argRootView.addView(childView);
        return childView;
        /*LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup parent = (ViewGroup)findViewById(R.id.where_you_want_to_insert);
        inflater.inflate(R.layout.the_child_view, parent);*/
    }

    public static void removeLayout(ViewGroup argRootView) {
        argRootView.removeAllViews();
    }
}
