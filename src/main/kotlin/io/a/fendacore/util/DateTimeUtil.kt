package io.a.fendacore.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

private val chineseFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss", Locale.CHINA)

fun LocalDateTime.toChineseDateTime(): String {
    return this.format(chineseFormatter)
}