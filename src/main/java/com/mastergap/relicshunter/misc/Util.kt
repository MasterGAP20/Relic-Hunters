package com.mastergap.relicshunter.misc

import com.fastasyncworldedit.core.configuration.Settings.EXTENT
import com.fastasyncworldedit.core.extent.NullExtent
import com.fastasyncworldedit.core.extent.PassthroughExtent
import com.github.shynixn.structureblocklib.api.bukkit.StructureBlockLibApi
import com.mastergap.relicshunter.relics.Relics
import com.sk89q.worldedit.EditSession
import com.sk89q.worldedit.WorldEdit
import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldedit.bukkit.BukkitWorld
import com.sk89q.worldedit.function.pattern.Pattern
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
        val cube = setOf<BlockVector3>(BlockVector3.at(-300,-16,-300),BlockVector3.at(300,-1,300))
        WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(world)).setBlocks(cube,BlockState(BlockTypes.AIR,0,0))
    }
}