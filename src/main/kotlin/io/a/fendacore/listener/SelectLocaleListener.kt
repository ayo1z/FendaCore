package io.a.fendacore.listener

import io.a.fendacore.FendaCorePlugin
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.lang.event.PlayerSelectLocaleEvent

object SelectLocaleListener {

    @SubscribeEvent
    fun onSelectLocale(event: PlayerSelectLocaleEvent) {
        event.locale = FendaCorePlugin.config.getString("lang", "zh_CN")!!
    }
}