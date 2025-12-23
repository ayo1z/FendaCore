package io.a.fendacore.listener

import io.a.fendacore.service.PlayerLifecycleService
import org.bukkit.event.player.PlayerJoinEvent
import taboolib.common.platform.event.SubscribeEvent

object PlayerLifecycleListener {

    @SubscribeEvent
    private fun onPlayerJoinServer(event: PlayerJoinEvent) {
        PlayerLifecycleService.callJoin(event.player)
    }
}