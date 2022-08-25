package com.mastergap.relicshunter.misc

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.io.File

class CommandsTab(
    var plugin : Plugin
) : TabCompleter {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String>? {
        if(command.name == "structurescan"){
            if(args.size == 1 || args.size == 4){
                val cords = ArrayList<String>()
                val player = sender as Player
                val block = player.getTargetBlock(8)
                if (block != null) {
                    cords.add(block.x.toString())
                }
                return cords
            }
            if(args.size == 2 || args.size == 5){
                val cords = ArrayList<String>()
                val player = sender as Player
                val block = player.getTargetBlock(8)
                if (block != null) {
                    cords.add(block.y.toString())
                }
                return cords
            }
            if(args.size == 3 || args.size == 6){
                val cords = ArrayList<String>()
                val player = sender as Player
                val block = player.getTargetBlock(8)
                if (block != null) {
                    cords.add(block.z.toString())
                }
                return cords
            }
            if(args.size == 7){
                val struc = ArrayList<String>()
                struc.add("structureName")
                return struc
            }
        }
        if(command.name == "structureload"){
            if(args.size == 1){
                val cords = ArrayList<String>()
                val player = sender as Player
                val block = player.getTargetBlock(8)
                if (block != null) {
                    cords.add(block.x.toString())
                }
                return cords
            }
            if(args.size == 2){
                val cords = ArrayList<String>()
                val player = sender as Player
                val block = player.getTargetBlock(8)
                if (block != null) {
                    cords.add(block.y.toString())
                }
                return cords
            }
            if(args.size == 3){
                val cords = ArrayList<String>()
                val player = sender as Player
                val block = player.getTargetBlock(8)
                if (block != null) {
                    cords.add(block.z.toString())
                }
                return cords
            }
            if(args.size == 4){
                val names = ArrayList<String>()
                val path = plugin.dataFolder.toPath().toString()
                File(path).walk().forEach {
                    val name = it.name
                    if(name != "RelicsHunter"){
                        names.add(name)
                    }
                }
                return names
            }
        }
        if(command.name == "generatedungeon"){
            if(args.size == 1){
                val cords = ArrayList<String>()
                val player = sender as Player
                val block = player.getTargetBlock(8)
                if (block != null) {
                    cords.add(block.x.toString())
                }
                return cords
            }
            if(args.size == 2){
                val cords = ArrayList<String>()
                val player = sender as Player
                val block = player.getTargetBlock(8)
                if (block != null) {
                    cords.add(block.y.toString())
                }
                return cords
            }
            if(args.size == 3){
                val cords = ArrayList<String>()
                val player = sender as Player
                val block = player.getTargetBlock(8)
                if (block != null) {
                    cords.add(block.z.toString())
                }
                return cords
            }
            if(args.size == 4) return arrayListOf<String>("roomCount")
        }
        return null
    }
}