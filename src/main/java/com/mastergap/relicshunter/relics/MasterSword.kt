package com.mastergap.relicshunter.relics

import org.bukkit.Particle
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.meta.Damageable

class MasterSword{
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
            temploc.world.spawnParticle(Particle.CRIT_MAGIC,temploc,5,0.0,0.0,0.0)
            if(!temploc.getNearbyLivingEntities(2.0).isEmpty()){
                val entities = temploc.getNearbyLivingEntities(2.0)
                entities.remove(player)
                if(!entities.isEmpty()){
                    val victim = entities.elementAt(0)
                    victim.damage(8.0)
                    if((itemMeta as Damageable).damage <= 1559){
                        itemMeta.damage += 2
                        item.itemMeta = itemMeta
                        return
                    }
                    if(itemMeta.damage >= 1559){
                        item.subtract()
                    }
                }
            }
        }
    }

}
