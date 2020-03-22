package com.instana.android.example

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.instana.android.Instana
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.execute_request)
        val responseTextView = findViewById<TextView>(R.id.response)

        button.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) {
                val result = executeQuery()
                responseTextView.text = result
            }
        }
    }

    override fun onResume() {
        super.onResume()

        Instana.view = "Main screen"
    }

    private suspend fun executeQuery(): String? = withContext(Dispatchers.IO) {
        val okHttpClient = OkHttpClient.Builder().build()
        val okHttpRequest = Request.Builder()
            .url("http://35.155.175.252:8080/")
            .get()
            .build()
        return@withContext try {
            val response = okHttpClient.newCall(okHttpRequest).execute()
            response.body?.string()
        } catch (e: IOException) {
            e.message
        }
    }
}
