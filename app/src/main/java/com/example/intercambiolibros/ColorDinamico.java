package com.example.intercambiolibros;

import android.app.Application;

import com.google.android.material.color.DynamicColors;

public class ColorDinamico extends Application  {


    @Override
    public void onCreate() {
         super.onCreate();

        DynamicColors.applyToActivitiesIfAvailable(this);
    }
}
