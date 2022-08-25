package com.mastergap.relicshunter.dungeon

import com.mastergap.relicshunter.misc.Util
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.plugin.Plugin
import java.util.zip.DeflaterOutputStream

object Generator {
    val roomNames = listOf<String>("e","es","esw","ew","n","ne","nes","nesw","new","ns","nsw","nw","s","sw","w")
    val corridorNames = listOf<String>("es","esw","ew","ne","nes","nesw","new","ns","nsw","nw","sw")
    private val OK = Material.LIME_CONCRETE
    fun generate(sx: Double, sy: Double, sz: Double, roomCount: Int, theme: String, world: World, plugin: Plugin) {
        var currentRoom = roomNames.random()
        var rooms = 0
        var nx = sx
        var nz = sz
        while(rooms < roomCount) {
            if(rooms == 0) {
                createRoom(plugin, world, sx, sy, sz, theme, currentRoom)
                rooms+=1
            }
            for (i in currentRoom.indices) {
                when(currentRoom[i]){
                    'n' -> {
                        var possible = roomNames.shouldInclude('s')
                        currentRoom = possible.random()
                    }
                    'e' -> {
                        var possible = roomNames.shouldInclude('w')
                        currentRoom = possible.random()
                    }
                    's' -> {
                        var possible = roomNames.shouldInclude('n')
                        currentRoom = possible.random()
                    }
                    'w' -> {
                        var possible = roomNames.shouldInclude('e')
                        currentRoom = possible.random()
                    }
                }
                rooms+=1
            }
        }
    }

    private fun createRoom(plugin: Plugin,world: World,sx: Double,sy: Double,sz: Double,theme: String,name: String) {
        Util.loadStructure(plugin,world,sx,sy,sz,"$theme/$name.nbt")
    }

    private fun List<String>.shouldInclude(char: Char): List<String> {
        var possibleRooms = this
        possibleRooms.forEach{ if (!it.contains(char)) possibleRooms = possibleRooms.drop(possibleRooms.indexOf(it)) }
        return possibleRooms
    }
    private fun List<String>.shouldNotInclude(char: Char): List<String> {
        var possibleRooms = this
        possibleRooms.forEach{ if (it.contains(char)) possibleRooms = possibleRooms.drop(possibleRooms.indexOf(it)) }
        return possibleRooms
    }
    private fun checkAdjacent(x: Double, y: Double, z: Double, direction: Char) {

    }
}