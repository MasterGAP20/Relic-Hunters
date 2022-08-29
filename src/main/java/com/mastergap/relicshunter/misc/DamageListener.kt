package com.mastergap.relicshunter.misc

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

object DamageListener : Listener {
    @EventHandler
    fun damage(e: EntityDamageByEntityEvent) {
        if(e.damager is Player && e.entity is Player && e.entity.location.y > 1) e.isCancelled = true
    }
}