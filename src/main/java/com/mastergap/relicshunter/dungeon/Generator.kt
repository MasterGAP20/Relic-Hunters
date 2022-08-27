package com.mastergap.relicshunter.dungeon

import com.mastergap.relicshunter.dungeon.Generator.getAdjacent
import com.mastergap.relicshunter.misc.Util
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import kotlin.random.Random

object Generator {
    val roomNames = listOf<String>("e","es","esw","ew","n","ne","nes","nesw","new","ns","nsw","nw","s","sw","w")
    fun generate(sx: Double, sy: Double, sz: Double, roomCount: Int, theme: String, world: World, plugin: Plugin, sender: Player) {
        //var currentRoom = roomNames.random()
        val path = generatePath(roomCount)
        path.forEach{
            //createRoom(plugin,world,sx+it.x,sy,sz+it.z,theme,currentRoom)
            var possibleRooms = roomNames
            it.getAdjacent(path).forEach { possibleRooms = possibleRooms.shouldInclude(it) }
            it.getNotAdjacent(path).forEach { possibleRooms = possibleRooms.shouldNotInclude(it) }
            sender.sendMessage("${it.x}/${it.z}/${it.isCorridor}")
            sender.sendMessage(path.indexOf(it).toString())
            sender.sendMessage(it.getAdjacent(path).toString())
            var room = possibleRooms.random()
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
        var previous = list
        while(list.distinct().size < roomCount) {
            previous = list
            list.add(Room(x,z,false))
            val rand = (0..3).random()
            if(list.distinct() != previous) {
                when (rand) {
                    0 -> z -= 17
                    1 -> x += 17
                    2 -> z += 17
                    3 -> x -= 17
                }
            }
        }
        list = list.distinct() as MutableList<Room>
        list.forEach { it.isCorridor = Random.nextBoolean() }
        return list
    }

    private fun Room.getAdjacent(path: List<Room>) : List<Char> {
        val list = mutableListOf<Char>()
        //if(path.contains(Location(this.world,this.x,this.y,this.z-17.0))) list.add('n')
        //if(path.contains(Location(this.world,this.x+17.0,this.y,this.z))) list.add('e')
        //if(path.contains(Location(this.world,this.x,this.y,this.z+17.0))) list.add('s')
        //if(path.contains(Location(this.world,this.x-17.0,this.y,this.z))) list.add('w')
        path.forEach {
            if(this.noCorridor() == it.noCorridor().add(0.0,-17.0)) list.add('n')
            if(this.noCorridor() == it.noCorridor().add(17.0,0.0)) list.add('e')
            if(this.noCorridor() == it.noCorridor().add(0.0,17.0)) list.add('s')
            if(this.noCorridor() == it.noCorridor().add(-17.0,0.0)) list.add('w')
        }
        return list
    }

    private fun Room.getNotAdjacent(path: List<Room>) : List<Char> {
        val list = mutableListOf<Char>('n','e','s','w')
        //if(!path.contains(Location(this.world,this.x,this.y,this.z-17.0))) list.add('n')
        //if(!path.contains(Location(this.world,this.x+17.0,this.y,this.z))) list.add('e')
        //if(!path.contains(Location(this.world,this.x,this.y,this.z+17.0))) list.add('s')
        //if(!path.contains(Location(this.world,this.x-17.0,this.y,this.z))) list.add('w')
        path.forEach {
            if(this.noCorridor() == it.noCorridor().add(0.0,-17.0)) list.remove('n')
            if(this.noCorridor() == it.noCorridor().add(17.0,0.0)) list.remove('e')
            if(this.noCorridor() == it.noCorridor().add(0.0,17.0)) list.remove('s')
            if(this.noCorridor() == it.noCorridor().add(-17.0,0.0)) list.remove('w')
        }
        return list
    }
}