package com.instana.mobileeum;

import android.content.ComponentCallbacks2;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.instana.android.Instana;

public class JavaActivity extends AppCompatActivity {

    private static final String TAG = "JavaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        OneTimeWorkRequest compressionWork = new OneTimeWorkRequest.Builder(TestWorker.class).build();
        WorkManager.getInstance().enqueue(compressionWork);

        findViewById(R.id.http_url_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String json = "{\"name\":\"morpheus\",\"job\":\"leader\"}";
                        Log.e(TAG, json);
                        HttpUrlConnectionRequests.INSTANCE.doPost("https://reqres.in/api/users", json, true);
                    }
                }).start();
            }
        });

        findViewById(R.id.http_url_put).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String json = "{\"name\":\"morpheus\",\"job\":\"zion resident\"}";
                        HttpUrlConnectionRequests.INSTANCE.doPut("https://reqres.in/api/users/2", json, true);
                    }
                }).start();
            }
        });

        findViewById(R.id.http_url_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpUrlConnectionRequests.INSTANCE.doDelete("https://reqres.in/api/users/2", true);
                    }
                }).start();
            }
        });

        findViewById(R.id.http_url_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpUrlConnectionRequests.INSTANCE.doGet("https://reqres.in/api/users/23", true);
                    }
                }).start();
            }
        });

        findViewById(R.id.ok_http_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpRequests.INSTANCE.doGet("https://reqres.in/api/users/23", false);
                    }
                }).start();
            }
        });

        findViewById(R.id.ok_http_put).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String json = "{\"name\":\"morpheus\",\"job\":\"zion resident\"}";
                        OkHttpRequests.INSTANCE.doPut("https://reqres.in/api/users/2", json, false);
                    }
                }).start();
            }
        });

        findViewById(R.id.ok_http_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String json = "{\"name\":\"morpheus\",\"job\":\"leader\"}";
                        OkHttpRequests.INSTANCE.doPost("https://reqres.in/api/users", json, false);
                    }
                }).start();
            }
        });

        findViewById(R.id.ok_http_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpRequests.INSTANCE.doDelete("https://reqres.in/api/users/2", false);
                    }
                }).start();
            }
        });

        findViewById(R.id.crash_me_runtime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = null;
                string.indexOf("crash");
            }
        });

        findViewById(R.id.crash_me_throwable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new ArrayIndexOutOfBoundsException("crash2");
            }
        });

        findViewById(R.id.invoke_low_memory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApplication().onTrimMemory(ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL);
            }
        });

        findViewById(R.id.invoke_frame_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.invoke_anr_sleep).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Thread.sleep(12000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Instana.crashReporting.leave("onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Instana.crashReporting.leave("onResume");
    }
}