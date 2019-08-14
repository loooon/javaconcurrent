package lock

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

fun main() {
    val d = SimpleDateFormat("yyyy-MM-dd").parse("2019-04-30")
    val y = SimpleDateFormat("yyyy-MM-dd").parse("2019-08-04")
    println(y.day - d.day)
}