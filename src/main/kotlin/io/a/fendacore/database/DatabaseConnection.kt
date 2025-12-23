package io.a.fendacore.database

import io.a.fendacore.FendaCorePlugin
import io.a.fendacore.module.birthday.BirthdayManager
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.module.database.getHost

object DatabaseConnection {

    val host = FendaCorePlugin.config.getHost("database")

    val dataSource by lazy { host.createDataSource() }

    @Awake(LifeCycle.ENABLE)
    private fun initialize() {
        BirthdayManager.table.createTable(dataSource)
    }
}