package main.collections.utils

import java.util.*

fun start(title: String): Long {
    val lForStartTime = Date().time
    println("Start (" + title + "): $lForStartTime")

    return lForStartTime
}

fun end(lForStartTime: Long) {
    val lForEndTime = Date().time
    println("End: $lForEndTime")

    val lForDifference = lForEndTime - lForStartTime
    println("Elapsed time in milliseconds: $lForDifference")
    println("-------END-------\n")
}
