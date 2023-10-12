package com.techbyfloig.cubannotary.business

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform