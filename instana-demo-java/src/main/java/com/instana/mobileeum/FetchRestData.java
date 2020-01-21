package com.instana.mobileeum;

import android.os.AsyncTask;

public class FetchRestData extends AsyncTask<Void, Void, Boolean> {

    @Override
    protected Boolean doInBackground(Void... params) {
        return HttpUrlConnectionRequests.INSTANCE.doGet("https://reqres.in/api/users/23", true);
    }
}