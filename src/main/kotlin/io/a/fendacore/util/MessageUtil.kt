package io.a.fendacore.util

import org.bukkit.command.CommandSender
import taboolib.platform.util.asLangText

/**
 * 发送带前缀的消息
 */
fun CommandSender.sendPrefixedLang(node: String, vararg args: Any) {
    val prefix = asLangText("command-prefix")
    val message = asLangText(node, *args)
    sendMessage("$prefix$message")
}