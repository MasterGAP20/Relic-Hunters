package com.mastergap.relicshunter.dungeon

import com.mastergap.relicshunter.misc.Util
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import kotlin.random.Random

object Generator {
    val roomNames = listOf("e","es","esw","ew","n","ne","nes","nesw","new","ns","nsw","nw","s","sw","w")
    fun generate(sx: Double, sy: Double, sz: Double, roomCount: Int, theme: String, world: World, plugin: Plugin, sender: Player) {
        val path = generatePath(roomCount)
        path.forEach{
            var possibleRooms = roomNames
            it.getAdjacent(path).forEach { possibleRooms = possibleRooms.shouldInclude(it) }
            listOf('n','e','s','w','0').minus(it.getAdjacent(path).toSet()).forEach { possibleRooms = possibleRooms.shouldNotInclude(it) }
            sender.sendMessage("${it.x}/${it.z}/${it.isCorridor}")
            sender.sendMessage(path.indexOf(it).toString())
            sender.sendMessage(it.getAdjacent(path).toString())
            var room = possibleRooms.random()
            it.isCorridor = Random.nextBoolean()
            if(it.isCorridor) room = "corridors/$room"
            createRoom(plugin,world,sx+it.x,sy,sz+it.z,theme,room)
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
    private fun generatePath(roomCount: Int) : List<Room> {
        var list = mutableListOf<Room>()
        var x = 0.0
        var z = 0.0
        list.add(Room(x,z,false))
        while(list.distinct().size < roomCount) {
            val direction = (0..3).random()
            var a = x
            var b = z
            when(direction) {
                0 -> b-=17
                1 -> a+=17
                2 -> b+=17
                3 -> a-=17
            }
            if(!list.contains(Room(a,b,false))) {
                x=a
                z=b
                list.add(Room(x,z,false))
                Bukkit.getConsoleSender().sendMessage("Room: $x/$z/false (list size: ${list.distinct().size})")
            }
        }
        return list.distinct()
    }

    private fun Room.getAdjacent(path: List<Room>) : List<Char> {
        val list = mutableListOf<Char>()
        var noCorridorPath = path
        noCorridorPath.forEach { it.isCorridor = false }
        if(noCorridorPath.contains(Room(this.x,this.z,false).add(0.0,-17.0))) list.add('n')
        if(noCorridorPath.contains(Room(this.x,this.z,false).add(17.0,0.0))) list.add('e')
        if(noCorridorPath.contains(Room(this.x,this.z,false).add(0.0,17.0))) list.add('s')
        if(noCorridorPath.contains(Room(this.x,this.z,false).add(-17.0,0.0))) list.add('w')
        return list
    }
}