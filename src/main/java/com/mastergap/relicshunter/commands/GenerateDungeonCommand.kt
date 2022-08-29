package com.mastergap.relicshunter.commands

import com.mastergap.relicshunter.Msg
import com.mastergap.relicshunter.dungeon.Generator
import com.mastergap.relicshunter.misc.Util
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class GenerateDungeonCommand(
    private var plugin: Plugin
) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(!sender.hasPermission("admin")) {
            Msg.send(sender, "no perms")
            return true
        }
        val player = sender as Player

        if(args.size < 3){
            Msg.send(player, "Place a valid source coordinates")
            return true
        }else if(args.size < 4){
            Msg.send(player, "Enter a room count")
            return true
        }else if(args.size < 5){
            Msg.send(player, "Enter a theme")
            return true
        }
        var sx = args[0].toDouble()
        val sy = args[1].toDouble()
        var sz = args[2].toDouble()
        val roomCount = args[3].toInt()
        val theme = args[4]

        Generator.generate(sx, sy, sz, roomCount, theme, player.world, plugin, player)

        return true
    }
}