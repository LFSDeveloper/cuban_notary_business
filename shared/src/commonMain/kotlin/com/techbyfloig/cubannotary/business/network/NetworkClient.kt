package com.techbyfloig.cubannotary.business.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

/**
 * Generate a [HttpClient] with the given [config]. Implemented on each platform.
 */
expect fun getHttpClient(
    config: HttpClientConfig<*>.() -> Unit
 ): HttpClient