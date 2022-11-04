package com.rzandroidgit.appusagesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rzandjavagit.propreferences.ProPreferences;

public class ActivityProPreferences extends AppCompatActivity {
    private Activity activity;
    private Context context;
    //
    private ProPreferences proPreferences;
    private Button sysButtonPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_preferences);
        activity = this;
        context = this;
        //
        sysButtonPreferences = (Button) findViewById(R.id.sysButtonPreferences);
        proPreferences = new ProPreferences.Builder()
                .withContext(context)
                .withPrefsName(getPackageName())
                .withMode(ProPreferences.Mode.PRIVATE)
                .withDefaultPrefs(false)
                .build();
        proPreferences.putLong("long_value", 1000L);
        sysButtonPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("DEBUG_LOG_PRINT: "+proPreferences.getLong("long_value"));
            }
        });
        proPreferences.logPrint();
    }
}