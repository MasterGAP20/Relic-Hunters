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
import java.io.File
import java.util.logging.Level

class StructureScanner(
    private var plugin: Plugin
) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = sender as Player

        if(args.size < 6){
            Msg.send(player, "Place two valid sets of coordinates")
            return true
        }else if(args.size < 7){
            Msg.send(player, "Place set a name to the structure")
            return true
        }

        val cx1 = args[0].toInt()
        val cy1 = args[1].toInt()
        val cz1 = args[2].toInt()
        val cx2 = args[3].toInt()
        val cy2 = args[4].toInt()
        val cz2 = args[5].toInt()
        val structureName =  args[6]

        val sizeX = getDif(cx1,cx2)
        val sizeY = getDif(cy1,cy2)
        val sizeZ = getDif(cz1,cz2)

        Msg.send(player,"X: $sizeX, Y: $sizeY, Z: $sizeZ")

        val path = plugin.dataFolder.toPath().resolve("$structureName.nbt")
        val file = File(path.toString())

        if(file.exists()){
            Msg.send(player,"Structure Already Exists")
        }else{
            if(file.createNewFile()){
                plugin.logger.info("Structure File Created Succesfully")
            }else{
                plugin.logger.info("Failed to Create Structure File")
            }
        }

        StructureBlockLibApi.INSTANCE
            .saveStructure(plugin)
            .at(Location(Bukkit.getWorld("world"), cx1.toDouble(), cy1.toDouble(), cz1.toDouble()))
            .sizeX(sizeX)
            .sizeY(sizeY)
            .sizeZ(sizeZ)
            .saveToPath(path)
            .onException { e: Throwable? ->
                plugin.logger.log(Level.SEVERE, "Failed to save structure.", e)
            }
            .onResult { e: Void? ->
                Msg.send(player, "Saved structure '$structureName'.")
            }

        return true
    }

    private fun getDif(n1: Int, n2: Int ): Int {
        return if(n1 > n2){
            (n2 - n1) - 1
        }else{
            (n2 - n1) + 1
        }
    }

}