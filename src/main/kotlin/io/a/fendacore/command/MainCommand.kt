package io.a.fendacore.command

import io.a.fendacore.FendaCorePlugin
import io.a.fendacore.util.sendPrefixedLang
import org.bukkit.command.CommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.subCommand
import taboolib.common.platform.function.console
import taboolib.expansion.createHelper
import taboolib.module.lang.Language
import taboolib.platform.util.onlinePlayers
import java.io.File
import java.time.LocalDateTime
import kotlin.time.measureTime

@CommandHeader(name = "fendacore", permission = "fendacore.use")
object MainCommand {

    @CommandBody
    val main = mainCommand {
        createHelper()
    }

    @CommandBody(permission = "fendacore.admin")
    val admin = subCommand {
        literal("重载") {
            execute<CommandSender> { sender, _, _ ->
                measureTime {
                    Language.reload()
                    FendaCorePlugin.config.reload()
                }.inWholeMilliseconds.also {
                    sender.sendPrefixedLang("command-reload", it)
                }
            }
        }

        literal("报告") {
            execute<CommandSender> { _, _, _ ->
                val players = onlinePlayers

                if (players.isEmpty()) {
                    return@execute
                }

                console().sendMessage("§7")
                console().sendMessage("§f当前时间: §e${LocalDateTime.now()}")
                console().sendMessage("§7")
                players.forEachIndexed { index, player ->
                    val loc = player.location

                    console().sendMessage(
                        "§7${index + 1}§8. §b${player.name} §7| §b${loc.world?.name ?: "未知世界"} §7| §f${loc.x}, ${loc.y}, ${loc.z}"
                    )
                }
                console().sendMessage("§7")
            }
        }

        literal("清理") {
            execute<CommandSender> { _, _, _ ->
                var count = 0

                onlinePlayers.forEach { player ->
                    val file = File(".", "${player.name}.yml")
                    if (file.exists() && file.delete()) {
                        count++
                    }
                }

                console().sendMessage("§f已清理 §e$count §f个在线玩家的名字YAML文件")
            }
        }
    }

    @CommandBody
    val birthday = subCommand {
        literal("设置") {
            execute<CommandSender> { sender, _, _ ->

            }
        }
    }
}