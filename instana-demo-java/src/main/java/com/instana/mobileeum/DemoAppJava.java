package com.instana.mobileeum;

import android.app.Application;

import com.instana.android.Instana;
import com.instana.android.core.InstanaConfiguration;

public class DemoAppJava extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Instana.init(this, new InstanaConfiguration("http://10.0.2.2:3000/v1/api", "42"));
    }
}