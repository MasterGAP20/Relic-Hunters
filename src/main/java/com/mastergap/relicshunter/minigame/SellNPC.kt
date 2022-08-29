package com.mastergap.relicshunter.minigame

import com.mastergap.relicshunter.Msg
import com.mastergap.relicshunter.commands.SpawnSeller
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.meta.Damageable
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot

class SellNPC : Listener {

    @EventHandler
    fun sell(event: PlayerInteractEntityEvent){
        val player = event.player
        val entity = event.rightClicked

        if(entity.name == "Relic Expert"){
            val item = player.inventory.itemInMainHand
            if(item.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES)){
                val value = (item.type.maxDurability-item.durability) * 100
                CoinsScoreboard.totalcoins += value
                CoinsScoreboard.createBoard(player)
                item.subtract()
                Msg.send(player,"${ChatColor.GREEN}Sold for $value coins")
            }
        }

    }

}