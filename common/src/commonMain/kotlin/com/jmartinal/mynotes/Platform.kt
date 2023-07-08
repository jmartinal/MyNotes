package com.jmartinal.mynotes

fun getAppTitle() = "My notes - ${getPlatformName()}"

expect fun getPlatformName(): String