package com.mastergap.relicshunter

import com.mastergap.relicshunter.commands.GenerateDungeonCommand
import com.mastergap.relicshunter.misc.CommandsTab
import com.mastergap.relicshunter.misc.CreateData
import com.mastergap.relicshunter.commands.StructureLoadCommand
import com.mastergap.relicshunter.commands.StructureScanCommand
import org.bukkit.plugin.java.JavaPlugin

class Relicshunter : JavaPlugin() {

    override fun onEnable() {
        logger.info("Plugin Activated :)")
        CreateData(this).createFolder()
        getCommand("structurescan")?.setExecutor(StructureScanCommand(this))
        getCommand("structurescan")?.tabCompleter = CommandsTab(this)
        getCommand("structureload")?.setExecutor(StructureLoadCommand(this))
        getCommand("structureload")?.tabCompleter = CommandsTab(this)
        getCommand("generatedungeon")?.setExecutor(GenerateDungeonCommand(this))
        getCommand("generatedungeon")?.tabCompleter = CommandsTab(this)
    }

    override fun onDisable() {
        logger.info("Plugin Deactivated :(")
    }

}