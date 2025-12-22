package io.a.fendacore.hook

import org.bukkit.entity.Player
import taboolib.platform.compat.PlaceholderExpansion

object PlaceholderAPIHook : PlaceholderExpansion {

    override val identifier: String = "fendacore"

    override fun onPlaceholderRequest(player: Player?, args: String): String {
        return when (args) {
            "empty" -> "[]"
            else -> "未知变量"
        }
    }
}