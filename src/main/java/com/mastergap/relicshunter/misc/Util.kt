package com.mastergap.relicshunter.misc

import com.fastasyncworldedit.core.extent.NullExtent
import com.github.shynixn.structureblocklib.api.bukkit.StructureBlockLibApi
import com.mastergap.relicshunter.relics.Relics
import com.sk89q.worldedit.bukkit.BukkitWorld
import com.sk89q.worldedit.math.BlockVector3
import com.sk89q.worldedit.regions.CuboidRegion
import com.sk89q.worldedit.regions.Region
import com.sk89q.worldedit.world.block.BaseBlock
import com.sk89q.worldedit.world.block.BlockState
import com.sk89q.worldedit.world.block.BlockTypes
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
            .includeEntities(true)
            .loadFromPath(path)
            .onException { e: Throwable? ->
                plugin.logger.log(Level.SEVERE, "Failed to load structure.", e)
            }
            .onResult { e: Void? ->
                plugin.logger.log(Level.INFO, "Loaded structure '$name'.")
            }
        var relic = (0..20).random()
        if(relic > 17) world.dropItem(Location(world,sx+8.5,sy+4,sz+8.5), Relics.relics.random())
    }

    fun clearDungeon(world: World) {
        val cube = CuboidRegion(BukkitWorld(world), BlockVector3.at(-200,-16,-200), BlockVector3.at(200,-1,200))
        NullExtent().setBlocks(cube as Region,BaseBlock(BlockState(BlockTypes.AIR,0,0)))
    }
}