package com.example.juns.application;

import android.app.Application;

/**
 * Created by Juns on 2015-02-03.
 */
public class AndExam_Application extends Application {
    private int mMode;
    static final int BEGINNER = 0;
    static final int PREFESSIONAL = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        mMode = BEGINNER;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public int getMode() {
        return mMode;
    }

    public void setMode(int mMode) {
        this.mMode = mMode;
    }
}
