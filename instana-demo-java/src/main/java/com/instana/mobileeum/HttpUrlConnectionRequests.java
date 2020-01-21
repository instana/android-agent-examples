package com.instana.mobileeum;

import android.util.Log;

import com.instana.android.Instana;
import com.instana.android.instrumentation.RemoteCallMarker;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;

class HttpUrlConnectionRequests {

    static final HttpUrlConnectionRequests INSTANCE = new HttpUrlConnectionRequests();

    private static final String TAG = "HttpRequests";
    private static final String ERROR = "error";

    boolean doGet(String desiredUrl, boolean enableManual) {
        HttpURLConnection urlConnection = null;
        RemoteCallMarker marker = null;
        try {
            urlConnection = (HttpURLConnection) new URL(desiredUrl).openConnection();
            if (enableManual) {
                marker = Instana.remoteCallInstrumentation.markCall(desiredUrl, "GET");
                urlConnection.setRequestProperty(marker.headerKey(), marker.headerValue());
            }
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            if (marker != null) {
                marker.endedWith(0, 0, responseCode);
            }
            return responseCode == HttpsURLConnection.HTTP_OK;
        } catch (IOException e) {
            Log.e(TAG, ERROR, e);
            if (marker != null) {
                marker.endedWith(e);
            }
            return false;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    boolean doPost(String desiredUrl, String json, boolean enableManual) {
        HttpURLConnection urlConnection = null;
        RemoteCallMarker marker = null;
        try {
            urlConnection = (HttpURLConnection) (new URL(desiredUrl).openConnection());
            if (enableManual) {
                marker = Instana.remoteCallInstrumentation.markCall(desiredUrl, "POST");
                urlConnection.setRequestProperty(marker.headerKey(), marker.headerValue());
            }
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            //Write
            OutputStream outputStream = urlConnection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, Charset.defaultCharset());
            writer.write(json);
            writer.close();
            outputStream.close();

            int responseCode = urlConnection.getResponseCode();
            if (marker != null) {
                marker.endedWith(0, 0, responseCode);
            }
            return responseCode == HttpsURLConnection.HTTP_OK;
        } catch (IOException e) {
            Log.e(TAG, ERROR, e);
            if (marker != null) {
                marker.endedWith(e);
            }
            return false;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    boolean doDelete(String desiredUrl, boolean enableManual) {
        HttpURLConnection urlConnection = null;
        RemoteCallMarker marker = null;
        try {
            urlConnection = (HttpURLConnection) (new URL(desiredUrl).openConnection());
            if (enableManual) {
                marker = Instana.remoteCallInstrumentation.markCall(desiredUrl, "DELETE");
                urlConnection.setRequestProperty(marker.headerKey(), marker.headerValue());
            }
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("DELETE");
            urlConnection.connect();

            int responseCode = urlConnection.getResponseCode();
            if (marker != null) {
                marker.endedWith(0, 0, responseCode);
            }
            return responseCode == HttpsURLConnection.HTTP_OK;
        } catch (IOException e) {
            Log.e(TAG, ERROR, e);
            if (marker != null) {
                marker.endedWith(e);
            }
            return false;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    boolean doPut(String desiredUrl, String json, boolean enableManual) {
        HttpURLConnection urlConnection = null;
        RemoteCallMarker marker = null;
        try {
            urlConnection = (HttpURLConnection) (new URL(desiredUrl).openConnection());
            if (enableManual) {
                marker = Instana.remoteCallInstrumentation.markCall(desiredUrl, "PUT");
                urlConnection.setRequestProperty(marker.headerKey(), marker.headerValue());
            }
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("PUT");
            urlConnection.connect();

            //Write
            OutputStream outputStream = urlConnection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, Charset.defaultCharset());
            writer.write(json);
            writer.close();
            outputStream.close();

            int responseCode = urlConnection.getResponseCode();
            if (marker != null) {
                marker.endedWith(0, 0, responseCode);
            }
            return responseCode == HttpsURLConnection.HTTP_OK;
        } catch (IOException e) {
            Log.e(TAG, ERROR, e);
            if (marker != null) {
                marker.endedWith(e);
            }
            return false;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}