package io.a.fendacore.module.birthday

import io.a.fendacore.service.PlayerLifecycleHandler
import io.a.fendacore.service.PlayerLifecycleService
import org.bukkit.entity.Player
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake

object BirthdayListener : PlayerLifecycleHandler {

    @Awake(LifeCycle.ENABLE)
    fun register() {
        PlayerLifecycleService.register(this)
    }

    override fun onPlayerJoin(player: Player) {

    }
}