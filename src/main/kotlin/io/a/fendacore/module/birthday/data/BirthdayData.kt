package io.a.fendacore.module.birthday.data

import java.time.LocalDate

data class BirthdayData(
    var birthday: LocalDate? = null,
    var lastSetTime: Long = 0L,
    var claimCount: Int = 0,
    var songDisabled: Boolean = false
)
