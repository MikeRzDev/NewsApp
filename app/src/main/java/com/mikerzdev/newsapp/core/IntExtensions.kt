package com.mikerzdev.newsapp.core

val Int.MegaBytes: Long
    get() = (this * 1024 * 1024).toLong()