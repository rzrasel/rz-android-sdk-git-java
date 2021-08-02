package com.rzandjavagit.core.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class KetchupFields {
    private View parentView;
    private OnEventListener onEventListener;
    private List<FIELD_TYPE> fieldTypeList;
    private int viewCounter = 0;

    public KetchupFields() {
        viewCounter = 0;
    }

    public KetchupFields setEventListener(OnEventListener agrOnEventListener) {
        onEventListener = agrOnEventListener;
        return this;
    }

    public KetchupFields withFieldList(List<FIELD_TYPE> argFieldTypeList) {
        fieldTypeList = argFieldTypeList;
        return this;
    }

    public KetchupFields withParentView(View argParentView) {
        parentView = argParentView;
        return this;
    }

    public void execute() {
        findInsideParent(parentView);
    }

    private void findInsideParent(View argParentView) {
        if (fieldTypeList == null) {
            return;
        }
        if (argParentView instanceof ViewGroup) {
            /*System.out.println("VIEW_TRACE: " + argParentView.getClass().getSimpleName());
            System.out.println("VIEW_TRACE: " + argParentView.getClass().getName());*/
            ViewGroup viewGroup = (ViewGroup) argParentView;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View view = viewGroup.getChildAt(i);
                if (view instanceof ViewGroup) {
                    findInsideParent(view);
                } else {
                    onCheckField(view, viewCounter);
                }
                viewCounter++;
            }
        }
    }

    private void onCheckField(View argView, int argPosition) {
        /*System.out.println("VIEW_TRACE: " + argView.getClass().getSimpleName());
        System.out.println("VIEW_TRACE: " + argView.getClass().getName());
        System.out.println("VIEW_TRACE: " + argView.getClass());*/
        //System.out.println("VIEW_TRACE: " + argView.getClass());
        for (FIELD_TYPE item : fieldTypeList) {
            //System.out.println("VIEW_TRACE: " + item.getItem());
            if (item == FIELD_TYPE.TEXT_VIEW && argView instanceof TextView) {
                onEventListener.getField(argView, item, argPosition);
            }
        }
    }

    public interface OnEventListener {
        public void getField(View argView, FIELD_TYPE argFieldType, int argPosition);
    }

    public enum FIELD_TYPE {
        IMAGE_VIEW("image_view"),
        TEXT_VIEW("text_view");
        private final String item;

        FIELD_TYPE(String argItem) {
            this.item = argItem;
        }

        public String getItem() {
            return this.item;
        }
    }
}