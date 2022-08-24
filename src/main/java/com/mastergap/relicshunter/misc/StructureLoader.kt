package com.mastergap.relicshunter.misc

import com.github.shynixn.structureblocklib.api.bukkit.StructureBlockLibApi
import com.mastergap.relicshunter.Msg
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.util.logging.Level


class StructureLoader(
    private var plugin: Plugin
) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

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

        val path = plugin.dataFolder.toPath().resolve("$name")

        StructureBlockLibApi.INSTANCE
            .loadStructure(plugin)
            .at(Location(Bukkit.getWorld("world"), sx, sy, sz))
            .loadFromPath(path)
            .onException { e: Throwable? ->
                plugin.logger.log(Level.SEVERE, "Failed to load structure.", e)
            }
            .onResult { e: Void? ->
                plugin.logger.log(Level.INFO, "Loaded structure '$name'.")
            }

        return true
    }
}