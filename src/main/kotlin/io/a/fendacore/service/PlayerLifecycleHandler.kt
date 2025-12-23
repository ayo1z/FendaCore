package io.a.fendacore.service

import org.bukkit.entity.Player

interface PlayerLifecycleHandler {

    fun onPlayerJoin(player: Player)
}