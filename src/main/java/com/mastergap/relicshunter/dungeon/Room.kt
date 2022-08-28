package com.mastergap.relicshunter.dungeon

data class Room(var x: Double, var z: Double, var isCorridor: Boolean) {
    fun noCorridor() : Room {return Room(x,z,false)}
    fun add(a: Double, b: Double) : Room {return Room(x+a,z+b,isCorridor)}
}