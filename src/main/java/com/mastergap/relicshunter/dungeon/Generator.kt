package com.mastergap.relicshunter.dungeon

import com.mastergap.relicshunter.misc.Util
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import kotlin.random.Random
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

object Generator {
    val roomNames = listOf("e","es","esw","ew","n","ne","nes","nesw","new","ns","nsw","nw","s","sw","w")
    var team1Room = Room(0.0,0.0,false)
    var team2Room = Room(0.0,0.0,false)
    var initialCoords: Location? = null
    var dungeonGenerated = false
    var oneLift = false
    fun generate(sx: Double, sy: Double, sz: Double, roomCount: Int, theme: String, world: World, plugin: Plugin, sender: Player) {
        oneLift = false
        dungeonGenerated = false
        val startTime = measureTimeMillis {
            val path = generatePath(roomCount)
            path.forEach{
                var possibleRooms = roomNames
                it.getAdjacent(path).forEach { possibleRooms = possibleRooms.shouldInclude(it) }
                listOf('n','e','s','w','0').minus(it.getAdjacent(path).toSet()).forEach { possibleRooms = possibleRooms.shouldNotInclude(it) }
                sender.sendMessage("${it.x}/${it.z}/${it.isCorridor}")
                sender.sendMessage(path.indexOf(it).toString())
                sender.sendMessage(it.getAdjacent(path).toString())
                var room = possibleRooms.random()
                var lift = (0..20).random()
                it.isCorridor = Random.nextBoolean()

                if(room=="nesw" && (lift > 18 || (path.indexOf(it) in (25 until path.indices.last-25) && !oneLift))) {
                    it.isCorridor = false
                    room="lift"
                    oneLift = true
                }

                if(it.isCorridor) room = "corridors/$room"
                createRoom(plugin,world,sx+it.x,sy,sz+it.z,theme,room)
            }
            initialCoords = Location(world,sx,sy,sz)
            team1Room = path.first().add(8.5,7.5)
            team2Room = path.last().add(8.5,7.5)
        }
        sender.sendMessage("Elapsed time: $startTime")
        dungeonGenerated = true
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
        var direction = (0..3).random()
        list.add(Room(x,z,false))
        while(list.distinct().size < roomCount) {
            val randomRoom = list.random()
            val changeBranch = (0..10).random()
            var a = x
            var b = z
            when(direction) {
                0 -> b-=17
                1 -> a+=17
                2 -> b+=17
                3 -> a-=17
            }
            Bukkit.getConsoleSender().sendMessage("${list.contains(Room(a,b,false))}")
            if(!list.contains(Room(a,b,false))) {
                x=a
                z=b
                list.add(Room(x,z,false))
                Bukkit.getConsoleSender().sendMessage("Room: $x/$z/false (size: ${list.distinct().size})")
            }
            if(list.contains(Room(a,b,false))) {
                x=randomRoom.x
                z=randomRoom.z
                //direction = (0..3).random()
            }
            if(changeBranch > 8) direction = (0..3).random()
            list = list.distinct() as MutableList<Room>
        }
        return list
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