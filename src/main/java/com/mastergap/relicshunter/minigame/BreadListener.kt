package com.mastergap.relicshunter.minigame

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.inventory.ItemStack

object BreadListener : Listener {
    @EventHandler
    fun playerDeath(e: PlayerDeathEvent) {
        val iterator: MutableIterator<ItemStack> = e.drops.iterator()
        while (iterator.hasNext()) {
            val drop = iterator.next()
            val type = drop.type
            if (type == Material.BREAD) {
                iterator.remove()
                e.itemsToKeep.add(drop)
            }
        }
    }
}