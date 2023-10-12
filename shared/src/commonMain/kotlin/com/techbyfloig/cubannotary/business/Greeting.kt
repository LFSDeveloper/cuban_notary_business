package com.techbyfloig.cubannotary.business

class Greeting {
    private val platform: Platform = getPlatform()

    suspend fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}