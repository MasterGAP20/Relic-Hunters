package com.mastergap.relicshunter

import com.mastergap.relicshunter.misc.CommandsTab
import com.mastergap.relicshunter.misc.CreateData
import com.mastergap.relicshunter.misc.StructureLoader
import com.mastergap.relicshunter.misc.StructureScanner
import org.bukkit.plugin.java.JavaPlugin

class Relicshunter : JavaPlugin() {

    override fun onEnable() {
        logger.info("Plugin Activated :)")
        CreateData(this).createFolder()
        getCommand("structurescan")?.setExecutor(StructureScanner(this))
        getCommand("structurescan")?.tabCompleter = CommandsTab(this)
        getCommand("structureload")?.setExecutor(StructureLoader(this))
        getCommand("structureload")?.tabCompleter = CommandsTab(this)
    }

    override fun onDisable() {
        logger.info("Plugin Deactivated :(")
    }

}