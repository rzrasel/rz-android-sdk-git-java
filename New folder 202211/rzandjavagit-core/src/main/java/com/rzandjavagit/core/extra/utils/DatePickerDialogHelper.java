package com.rzandjavagit.core.extra.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePickerDialogHelper {
    private Context context;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog.OnDateSetListener datePickerDialogListener;
    private DatePickerEventListener eventListener;
    private SimpleDateFormat simpleDateFormat;
    private String dateFormat = "yyyy-MM-dd";

    public DatePickerDialogHelper(Context argContext) {
        context = argContext;
        calendar = Calendar.getInstance();
    }

    public DatePickerDialogHelper setEventListener(DatePickerEventListener argDatePickerEventListener) {
        eventListener = argDatePickerEventListener;
        return this;
    }

    public DatePickerDialogHelper setDisable(View argView) {
        argView.setFocusable(false);
        argView.setFocusableInTouchMode(false);
        return this;
    }

    public DatePickerDialogHelper setDateFormat(String argDateFormat) {
        dateFormat = argDateFormat;
        return this;
    }

    private void setCalendarDate() {
        datePickerDialogListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker argDatePickerView, int argYear, int argMonthOfYear, int argDayOfMonth) {
                calendar.set(Calendar.YEAR, argYear);
                calendar.set(Calendar.MONTH, argMonthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, argDayOfMonth);
                if (eventListener != null) {
                    eventListener.getDate(getFormattedDate());
                }
            }

        };
    }

    public DatePickerDialogHelper setOnViewClickListener() {
        setCalendarDate();
        new DatePickerDialog(context, datePickerDialogListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
        return this;
    }

    private String getFormattedDate() {
        simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
        return simpleDateFormat.format(calendar.getTime());
    }

    public String getCurrentDateString() {
        Date toDate = new Date();
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(toDate);
    }

    public interface DatePickerEventListener {
        public void getDate(String argSelectedDate);
    }
}
