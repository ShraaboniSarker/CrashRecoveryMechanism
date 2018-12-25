package com.example.techland.crashrecoverysystem;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

public class CrashRecoveryApplication extends Application {

    public static CrashRecoveryApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }
    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }
    public static CrashRecoveryApplication getInstance() {
        return instance;
    }

    private void handleUncaughtException (Thread thread, Throwable e) {

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);


    }
}
