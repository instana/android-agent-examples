package com.instana.mobileeum;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class TestWorker extends Worker {

    public TestWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        String json = "{\"name\":\"morpheus\",\"job\":\"leader\"}";
        boolean request = HttpUrlConnectionRequests.INSTANCE.doPost("https://reqres.in/api/users", json, true);
        if (request) {
            return Result.success();
        }
        return Result.failure();
    }
}