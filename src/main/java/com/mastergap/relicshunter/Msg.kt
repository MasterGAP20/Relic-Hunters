package com.mastergap.relicshunter

import org.bukkit.ChatColor
import org.bukkit.command.CommandSender

class Msg {
    companion object {
        fun send(sender: CommandSender, message: String){
            send(sender, message, "&a")
        }
        fun send(sender: CommandSender, message: String, prefix: String){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message))
        }
    }
}