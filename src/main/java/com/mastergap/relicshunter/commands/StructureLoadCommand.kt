package com.mastergap.relicshunter.commands

import com.mastergap.relicshunter.Msg
import com.mastergap.relicshunter.misc.Util
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin


class StructureLoadCommand(
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
            Msg.send(player, "Please select a saved structure")
            return true
        }
        val sx = args[0].toDouble()
        val sy = args[1].toDouble()
        val sz = args[2].toDouble()
        val name = args[3]

        Util.loadStructure(plugin, player.world, sx, sy, sz, name)

        return true
    }
}