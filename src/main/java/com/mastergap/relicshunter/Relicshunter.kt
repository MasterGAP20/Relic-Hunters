package com.mastergap.relicshunter

import com.mastergap.relicshunter.commands.*
import com.mastergap.relicshunter.minigame.BreadListener
import com.mastergap.relicshunter.minigame.CoinsScoreboard
import com.mastergap.relicshunter.minigame.EndGame
import com.mastergap.relicshunter.minigame.SellNPC
import com.mastergap.relicshunter.minigame.StartGame
import com.mastergap.relicshunter.misc.CommandsTab
import com.mastergap.relicshunter.misc.CreateData
import com.mastergap.relicshunter.relics.AbilityListener
import com.mastergap.relicshunter.relics.Relics
import org.bukkit.plugin.java.JavaPlugin

class Relicshunter : JavaPlugin() {

    override fun onEnable() {
        logger.info("Plugin Activated :)")
        registerCommands()
        CreateData(this).createFolder()
        server.pluginManager.registerEvents(AbilityListener,this)
        server.pluginManager.registerEvents(SellNPC(),this)
        server.pluginManager.registerEvents(CoinsScoreboard(),this)
        server.pluginManager.registerEvents(BreadListener,this)
        Relics.init()
    }

    private fun registerCommands(){
        getCommand("structurescan")?.setExecutor(StructureScanCommand(this))
        getCommand("structurescan")?.tabCompleter = CommandsTab(this)
        getCommand("structureload")?.setExecutor(StructureLoadCommand(this))
        getCommand("structureload")?.tabCompleter = CommandsTab(this)
        getCommand("generatedungeon")?.setExecutor(GenerateDungeonCommand(this))
        getCommand("generatedungeon")?.tabCompleter = CommandsTab(this)
        getCommand("summonrelic")?.setExecutor(SummonRelic())
        getCommand("summonrelic")?.tabCompleter = CommandsTab(this)
        getCommand("spawnseller")?.setExecutor(SpawnSeller())
        getCommand("spawnreturn")?.setExecutor(SpawnReturn())
        getCommand("spawnsurface")?.setExecutor(SpawnSurface())
        getCommand("starthunt")?.setExecutor(StartGame())
        getCommand("endhunt")?.setExecutor(EndGame())
        getCommand("cleardungeon").setExecutor(ClearDungeonCommand())
    }

    override fun onDisable() {
        logger.info("Plugin Deactivated :(")
    }

}
