package com.instana.android.example;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.instana.android.Instana;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.execute_request);
        TextView responseTextView = findViewById(R.id.response);

        button.setOnClickListener(view ->
                (new AsyncQuery(responseTextView)).execute()
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        Instana.setView("Main screen");
    }

    private static class AsyncQuery extends AsyncTask<String, String, String> {

        private WeakReference<TextView> textViewReference;

        AsyncQuery(TextView textView) {
            textViewReference = new WeakReference<>(textView);
        }

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient okHttpClient = (new OkHttpClient.Builder()).build();
            Request okHttpRequest = (new Request.Builder())
                    .url("http://35.155.175.252:8080/")
                    .get()
                    .build();

            String response;
            try {
                ResponseBody body = okHttpClient.newCall(okHttpRequest).execute().body();
                response = body != null ? body.string() : null;
            } catch (IOException e) {
                response = e.getMessage();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if (textViewReference.get() != null) {
                textViewReference.get().setText(response);
            }
        }
    }
}
