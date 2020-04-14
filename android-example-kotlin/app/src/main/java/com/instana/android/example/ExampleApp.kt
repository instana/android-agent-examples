package com.instana.android.example

import android.app.Application
import com.instana.android.Instana
import com.instana.android.core.InstanaConfig

class ExampleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val instanaConfig = InstanaConfig(
            key = KEY,
            reportingURL = REPORTING_URL
        )
        Instana.setup(this, instanaConfig)
    }
}
