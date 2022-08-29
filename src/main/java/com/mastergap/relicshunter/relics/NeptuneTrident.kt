package com.mastergap.relicshunter.relics

import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class NeptuneTrident {
    fun tickAction(event: PlayerMoveEvent){
        if(event.player.world.getBlockAt(event.player.location).type == Material.WATER) {
            event.player.addPotionEffects(listOf(PotionEffectType.DOLPHINS_GRACE.createEffect(2,2),PotionEffectType.SPEED.createEffect(20,2)))
        }
    }
}