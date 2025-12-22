package io.a.fendacore.command

import io.a.fendacore.util.sendPrefixedLang
import io.a.fendacore.util.toChineseDateTime
import org.bukkit.command.CommandSender
import taboolib.common.platform.command.*
import taboolib.common.platform.function.console
import taboolib.expansion.createHelper
import taboolib.module.lang.Language
import taboolib.platform.util.onlinePlayers
import java.io.File
import java.time.LocalDateTime

@CommandHeader(name = "fendacore", permission = "fendacore.command")
object MainCommand {

    @CommandBody
    val main = mainCommand {
        createHelper()
    }

    @CommandBody(permissionDefault = PermissionDefault.OP)
    val reload = subCommand {
        execute<CommandSender> { sender, _, _ ->
            Language.reload()
            sender.sendPrefixedLang("command-reload")
        }
    }

    @CommandBody(permissionDefault = PermissionDefault.OP)
    val expansion = subCommand {
        literal("报告") {
            execute<CommandSender> { _, _, _ ->
                val players = onlinePlayers

                console().sendMessage("§f当前时间: §e${LocalDateTime.now().toChineseDateTime()} §f在线玩家数: §e${onlinePlayers.size}")

                if (players.isEmpty()) {
                    return@execute
                }

                players.forEach {
                    val loc = it.location
                    val world = loc.world?.name ?: "未知世界"

                    console().sendMessage("§7- §e${it.name} §7[${world}] §fx:${loc.x}, y:${loc.y}, z:${loc.z}")
                }
            }
        }

        literal("删除") {
            execute<CommandSender> { _, _, _ ->
                var count = 0

                onlinePlayers.forEach { player ->
                    val file = File(".", "${player.name}.yml")
                    if (file.exists() && file.delete()) {
                        count++
                    }
                }

                console().sendMessage("§f已删除 $count 个玩家的名字配置文件")
            }
        }
    }

    val birthday = subCommand {

    }
}