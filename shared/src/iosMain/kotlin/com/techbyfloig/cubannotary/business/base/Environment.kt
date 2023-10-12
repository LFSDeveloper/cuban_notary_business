package com.techbyfloig.cubannotary.business.base

/**
 * Base class to the business library environment.
 */
actual object Environment {

    /**
     * Base URL for the Cuban Notary Business rest APIs.
     */
    actual val baseUrl: String
        get() {
            val isDebug = Config.isDebug

            return if (isDebug) "https://debug.api.com" else "https://release.api.com"
        }
}