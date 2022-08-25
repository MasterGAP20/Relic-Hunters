package com.mastergap.relicshunter.commands

import com.mastergap.relicshunter.Msg
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
        val player = sender as Player

        if(args.size < 3){
            Msg.send(player, "Place a valid source coordinates")
            return true
        }else if(args.size < 4){
            Msg.send(player, "Enter a room count")
            return true
        }
        var sx = args[0].toDouble()
        val sy = args[1].toDouble()
        var sz = args[2].toDouble()
        var nx = sx
        var nz = sz
        val roomCount = args[3].toInt()

        for(i in 1..roomCount) {
            Util.loadStructure(plugin, player.world, nx, sy, nz, "room1.nbt")
            val rnd = (0..3).random()
            when(rnd) {
                0 -> nx+=9
                1 -> nz+=9
                2 -> nx-=9
                3 -> nz-=9
            }
        }

        return true
    }
}