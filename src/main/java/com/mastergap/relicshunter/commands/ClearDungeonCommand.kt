package com.mastergap.relicshunter.commands

import com.mastergap.relicshunter.Msg
import com.mastergap.relicshunter.dungeon.Generator
import com.mastergap.relicshunter.misc.Util
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class ClearDungeonCommand() : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(!sender.hasPermission("admin")) {
            Msg.send(sender, "no perms")
            return true
        }
        val player = sender as Player
        Util.clearDungeon(player.world)

        return true
    }
}