package com.instana.mobileeum;

import android.util.Log;

import com.instana.android.Instana;
import com.instana.android.instrumentation.RemoteCallMarker;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpRequests {

    public static final OkHttpRequests INSTANCE = new OkHttpRequests();

    private static final String TAG = "OkHttpRequests";
    private static final String ERROR = "error";

    private MediaType mediaType = MediaType.get("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    boolean doGet(String desiredUrl, boolean enableManual) {
        RemoteCallMarker marker = null;
        try {

            Request.Builder requestBuilder = new Request.Builder()
                    .url(desiredUrl)
                    .get();

            if (enableManual) {
                marker = Instana.remoteCallInstrumentation.markCall(desiredUrl, "GET");
                requestBuilder.addHeader(marker.headerKey(), marker.headerValue());
            }

            Request request = requestBuilder.build();
            OkHttpClient newClient = new OkHttpClient();
            Response response = newClient.newCall(request).execute();

            if (marker != null) {
                marker.endedWith(response);
            }
            return response.code() == HttpsURLConnection.HTTP_OK;
        } catch (IOException e) {
            Log.e(TAG, ERROR, e);
            if (marker != null) {
                marker.endedWith(e);
            }
            return false;
        }
    }

    boolean doPost(String desiredUrl, String json, boolean enableManual) {
        RemoteCallMarker marker = null;
        try {

            Request.Builder requestBuilder = new Request.Builder()
                    .url(desiredUrl)
                    .post(RequestBody.create(mediaType, json));

            if (enableManual) {
                marker = Instana.remoteCallInstrumentation.markCall(desiredUrl, "POST");
                requestBuilder.addHeader(marker.headerKey(), marker.headerValue());
            }

            Request request = requestBuilder.build();
            Response response = client.newCall(request).execute();

            if (marker != null) {
                marker.endedWith(response);
            }
            return response.code() == HttpsURLConnection.HTTP_OK;
        } catch (IOException e) {
            Log.e(TAG, ERROR, e);
            if (marker != null) {
                marker.endedWith(e);
            }
            return false;
        }
    }

    boolean doDelete(String desiredUrl, boolean enableManual) {
        RemoteCallMarker marker = null;
        try {

            Request.Builder requestBuilder = new Request.Builder()
                    .url(desiredUrl)
                    .delete();

            if (enableManual) {
                marker = Instana.remoteCallInstrumentation.markCall(desiredUrl, "DELETE");
                requestBuilder.addHeader(marker.headerKey(), marker.headerValue());
            }

            Request request = requestBuilder.build();
            Response response = client.newCall(request).execute();

            if (marker != null) {
                marker.endedWith(response);
            }
            return response.code() == HttpsURLConnection.HTTP_OK;
        } catch (IOException e) {
            Log.e(TAG, ERROR, e);
            if (marker != null) {
                marker.endedWith(e);
            }
            return false;
        }
    }

    boolean doPut(String desiredUrl, String json, boolean enableManual) {
        RemoteCallMarker marker = null;
        try {

            Request.Builder requestBuilder = new Request.Builder()
                    .url(desiredUrl)
                    .put(RequestBody.create(mediaType, json));

            if (enableManual) {
                marker = Instana.remoteCallInstrumentation.markCall(desiredUrl, "PUT");
                requestBuilder.addHeader(marker.headerKey(), marker.headerValue());
            }

            Request request = requestBuilder.build();
            Response response = client.newCall(request).execute();

            if (marker != null) {
                marker.endedWith(response);
            }
            return response.code() == HttpsURLConnection.HTTP_OK;
        } catch (IOException e) {
            Log.e(TAG, ERROR, e);
            if (marker != null) {
                marker.endedWith(e);
            }
            return false;
        }
    }
}
