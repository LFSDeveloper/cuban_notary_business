package com.techbyfloig.cubannotary.business.base

/**
 * Base class to an env provider
 */
interface EnvironmentProvider {
    /**
     * Tells if the current environment is debug or not.
     */
    val isDebug: Boolean
}