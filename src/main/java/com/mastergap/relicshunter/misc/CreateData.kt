package com.mastergap.relicshunter.misc

import org.bukkit.plugin.Plugin
import java.io.File

class CreateData(
    private var plugin: Plugin
) {
    fun createFolder(){
        val folder = File(plugin.dataFolder.toPath().toString())

        if(folder.exists()){
            plugin.logger.info("Data Folder Already Created")
        }else{
            if(folder.mkdir()){
                plugin.logger.info("Data Folder Created Succesfully")
            }else{
                plugin.logger.info("Failed to Create Data Folder")
            }
        }

    }
}