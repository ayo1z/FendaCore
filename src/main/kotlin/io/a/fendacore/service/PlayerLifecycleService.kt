package io.a.fendacore.service

import org.bukkit.entity.Player

object PlayerLifecycleService {

    private val handlers = mutableListOf<PlayerLifecycleHandler>()

    fun register(handler: PlayerLifecycleHandler) {
        handlers += handler
    }

    fun callJoin(player: Player) {
        handlers.forEach {
            it.onPlayerJoin(player)
        }
    }
}