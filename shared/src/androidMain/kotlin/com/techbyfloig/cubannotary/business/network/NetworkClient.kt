package com.techbyfloig.cubannotary.business.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources
import java.util.concurrent.TimeUnit

/**
 * Actual implementation of the HttpClient for Android.
 */
actual fun getHttpClient(
    config: HttpClientConfig<*>.() -> Unit
): HttpClient = HttpClient(OkHttp) {
    config(this)

    // installing plugins
    install(Resources)

    defaultRequest {
        url("https://api.cubannotary.tech")
    }

    // configuring engine
    engine {
        config {
            retryOnConnectionFailure(true)
            connectTimeout(0, TimeUnit.SECONDS)
        }
    }
}