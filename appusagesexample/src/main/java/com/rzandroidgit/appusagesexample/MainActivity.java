package com.rzandroidgit.appusagesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rzandroidgit.AndTest;
import com.rzandroidgit.core.AndCoreTest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndTest.onPrint("And Test");
        AndCoreTest.onPrint("And Core Test");
    }
}