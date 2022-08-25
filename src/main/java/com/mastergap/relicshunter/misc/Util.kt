package com.mastergap.relicshunter.misc

import com.github.shynixn.structureblocklib.api.bukkit.StructureBlockLibApi
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
    }
}