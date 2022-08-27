package com.mastergap.relicshunter.relics

import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.persistence.PersistentDataType

object AbilityListener : org.bukkit.event.Listener{
    @EventHandler
    fun rightClick(event: PlayerInteractEvent){
        if((event.action == Action.RIGHT_CLICK_AIR || event.action == Action.RIGHT_CLICK_BLOCK) && event.hand == EquipmentSlot.HAND){
            when(event.item?.itemMeta?.persistentDataContainer?.get(NamespacedKey("mastergap","weapons"),
                PersistentDataType.STRING)){
                "masterSword"  -> MasterSword().rightClickAction(event)
            }
        }
    }
}