package com.example.tdppokedex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun initLogger()