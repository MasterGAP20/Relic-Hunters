package com.mastergap.relicshunter.relics

import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.ItemDespawnEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.persistence.PersistentDataType

object AbilityListener : Listener {
    @EventHandler
    fun rightClick(event: PlayerInteractEvent){
        if((event.action == Action.RIGHT_CLICK_AIR || event.action == Action.RIGHT_CLICK_BLOCK) && event.hand == EquipmentSlot.HAND){
            when(event.item?.itemMeta?.persistentDataContainer?.get(NamespacedKey("mastergap","weapons"),
                PersistentDataType.STRING)){
                "masterSword"  -> MasterSword().rightClickAction(event)
                "archangelBlade" -> ArchangelBlade().rightClickAction(event)
            }
        }
    }
    @EventHandler
    fun move(event: PlayerMoveEvent) {
        val item = event.player.inventory.itemInMainHand
        when(item.itemMeta?.persistentDataContainer?.get(NamespacedKey("mastergap","weapons"),
            PersistentDataType.STRING)){
            "neptuneTrident"  -> NeptuneTrident().tickAction(event)
        }
    }

    @EventHandler
    fun noDespawn(event: ItemDespawnEvent) {
        if(event.entity.itemStack.itemMeta?.persistentDataContainer?.has(NamespacedKey("mastergap","weapons"))==true) {
            event.isCancelled = true
        }
    }
}