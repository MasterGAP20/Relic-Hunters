package com.mastergap.relicshunter.dungeon

class Room(x: Double, z: Double, isCorridor: Boolean) {
    var x = x
    var z = z
    var isCorridor = isCorridor

    fun noCorridor() : Room {return Room(x,z,false)}
    fun add(a: Double, b: Double) : Room {return Room(x+a,z+b,isCorridor)}
}