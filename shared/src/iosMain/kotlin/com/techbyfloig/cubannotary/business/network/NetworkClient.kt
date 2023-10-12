package com.techbyfloig.cubannotary.business.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources

/**
 * Actual implementation of the HttpClient for iOS.
 */
actual fun getHttpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Darwin) {
    config(this)

    // installing plugins
    install(Resources)

    defaultRequest {
        url("https://api.cubannotary.tech")
    }

    // configuring engine
    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
}