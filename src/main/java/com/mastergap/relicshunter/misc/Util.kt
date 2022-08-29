package com.mastergap.relicshunter.misc

import com.github.shynixn.structureblocklib.api.bukkit.StructureBlockLibApi
import com.mastergap.relicshunter.relics.Relics
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.plugin.Plugin
import java.util.logging.Level

object Util {
    fun loadStructure(plugin: Plugin, world: World, sx: Double, sy: Double, sz: Double, name: String) {
        val path = plugin.dataFolder.toPath().resolve(name)
        StructureBlockLibApi.INSTANCE
            .loadStructure(plugin)
            .at(Location(world, sx, sy, sz))
            .loadFromPath(path)
            .onException { e: Throwable? ->
                plugin.logger.log(Level.SEVERE, "Failed to load structure.", e)
            }
            .onResult { e: Void? ->
                plugin.logger.log(Level.INFO, "Loaded structure '$name'.")
            }
        var relic = (0..20).random()
        if(relic > 18) world.dropItem(Location(world,sx+8.5,sy+4,sz+8.5), Relics.relics.random())
    }
}