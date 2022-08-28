package com.mastergap.relicshunter.relics

import org.bukkit.Particle
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.meta.Damageable

class ArchangelBlade {
    fun rightClickAction(event: PlayerInteractEvent){
        val player = event.player
        val item = player.inventory.itemInMainHand
        val itemMeta = item.itemMeta
        val origin = player.eyeLocation
        val direction = origin.direction
        direction.multiply(10)
        direction.normalize()
        for(i in 0 .. 10){
            val temploc = origin.add(direction)
            temploc.world.spawnParticle(Particle.LAVA,temploc,1,0.1,0.0,0.1)
            if(!temploc.getNearbyLivingEntities(0.5).isEmpty()){
                val entities = temploc.getNearbyLivingEntities(2.0)
                entities.remove(player)
                if(!entities.isEmpty()){
                    val victim = entities.elementAt(0)
                    victim.fireTicks += 15
                    if((itemMeta as Damageable).damage <= 2031){
                        itemMeta.damage += 1
                        item.itemMeta = itemMeta
                        return
                    }
                    if(itemMeta.damage >= 2031){
                        item.subtract()
                    }
                }
            }
        }
    }
}