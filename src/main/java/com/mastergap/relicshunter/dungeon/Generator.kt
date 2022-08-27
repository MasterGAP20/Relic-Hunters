package com.mastergap.relicshunter.dungeon

import com.mastergap.relicshunter.misc.Util
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

object Generator {
    val roomNames = listOf<String>("e","es","esw","ew","n","ne","nes","nesw","new","ns","nsw","nw","s","sw","w")
    val corridorNames = listOf<String>("es","esw","ew","ne","nes","nesw","new","ns","nsw","nw","sw")
    private val OK = Material.LIME_CONCRETE
    fun generate(sx: Double, sy: Double, sz: Double, roomCount: Int, theme: String, world: World, plugin: Plugin, sender: Player) {
        //var currentRoom = roomNames.random()
        val path = generatePath(roomCount,world)
        path.forEach{
            //createRoom(plugin,world,sx+it.x,sy,sz+it.z,theme,currentRoom)
            var possibleRooms = roomNames
            it.getAdjacent(path).forEach { possibleRooms = possibleRooms.shouldInclude(it) }
            it.getNotAdjacent(path).forEach { possibleRooms = possibleRooms.shouldNotInclude(it) }
            sender.sendMessage(it.toString())
            sender.sendMessage(path.indexOf(it).toString())
            sender.sendMessage(it.getAdjacent(path).toString())
            createRoom(plugin,world,sx+it.x,sy,sz+it.z,theme,possibleRooms.random())
        }
    }

    private fun createRoom(plugin: Plugin,world: World,sx: Double,sy: Double,sz: Double,theme: String,name: String) {
        Util.loadStructure(plugin,world,sx,sy,sz,"$theme/$name.nbt")
    }

    private fun List<String>.shouldInclude(char: Char): List<String> {
        var possibleRooms = this
        possibleRooms.forEach{ if (!it.contains(char)) possibleRooms = possibleRooms.minus(it) }
        return possibleRooms
    }
    private fun List<String>.shouldNotInclude(char: Char): List<String> {
        var possibleRooms = this
        possibleRooms.forEach{ if (it.contains(char)) possibleRooms = possibleRooms.minus(it) }
        return possibleRooms
    }
    private fun generatePath(roomCount: Int, world: World) : List<Location> {
        var list = mutableListOf<Location>()
        var x = 0.0
        val y = 0.0
        var z = 0.0
        while(list.distinct().size < roomCount) {
            list.add(Location(world,x,y,z))
            val rand = (0..3).random()
            when(rand) {
                0 -> z-=17
                1 -> x+=17
                2 -> z+=17
                3 -> x-=17
            }
        }
        return list.distinct()
    }

    private fun Location.getAdjacent(path: List<Location>) : List<Char> {
        val list = mutableListOf<Char>()
        if(path.contains(Location(this.world,this.x,this.y,this.z-17.0))) list.add('n')
        if(path.contains(Location(this.world,this.x+17.0,this.y,this.z))) list.add('e')
        if(path.contains(Location(this.world,this.x,this.y,this.z+17.0))) list.add('s')
        if(path.contains(Location(this.world,this.x-17.0,this.y,this.z))) list.add('w')
        return list
    }

    private fun Location.getNotAdjacent(path: List<Location>) : List<Char> {
        val list = mutableListOf<Char>()
        if(!path.contains(Location(this.world,this.x,this.y,this.z-17.0))) list.add('n')
        if(!path.contains(Location(this.world,this.x+17.0,this.y,this.z))) list.add('e')
        if(!path.contains(Location(this.world,this.x,this.y,this.z+17.0))) list.add('s')
        if(!path.contains(Location(this.world,this.x-17.0,this.y,this.z))) list.add('w')
        return list
    }
}