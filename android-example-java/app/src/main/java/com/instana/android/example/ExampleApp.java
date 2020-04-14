package com.instana.android.example;

import android.app.Application;

import com.instana.android.Instana;
import com.instana.android.core.InstanaConfig;

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        InstanaConfig instanaConfig = new InstanaConfig(
                KEY,
                REPORTING_URL
        );
        Instana.setup(this, instanaConfig);
    }
}
