package com.mastergap.relicshunter.relics

import com.mastergap.relicshunter.Msg
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemFlag
import java.util.*

class MasterSword : Listener {

    @EventHandler
    fun genSword(event: PlayerInteractEvent){
        val player = event.player
        val world = player.world
        if(!player.isSneaking) return
        val itemOnHand = player.inventory.itemInMainHand
        val iohType = itemOnHand.type
        val iohMeta = itemOnHand.itemMeta
        val damageModifier = AttributeModifier(UUID.randomUUID(), "mastersword.attackDamage", 6.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND)
        val speedModifier = AttributeModifier(UUID.randomUUID(), "mastersword.attackSpeed", -2.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND)
        if(iohType == Material.DIAMOND_SWORD && !itemOnHand.hasItemFlag(ItemFlag.HIDE_ENCHANTS)){
            Msg.send(player,itemOnHand.itemMeta.toString())
            iohMeta.setDisplayName("Master Sword")
            iohMeta.addEnchant(Enchantment.OXYGEN,1,true)
            iohMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,damageModifier)
            iohMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,speedModifier)
            itemOnHand.itemMeta = iohMeta
            itemOnHand.durability = 1521
            itemOnHand.addItemFlags(ItemFlag.HIDE_ENCHANTS)
            itemOnHand.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            Msg.send(player,itemOnHand.itemMeta.toString())
            return
        }
        if(itemOnHand.hasItemFlag(ItemFlag.HIDE_ENCHANTS)){
            Msg.send(player,"Usaste la espada :)")
            val target = player.getTargetBlock(9)
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
                        victim.damage(4.0)
                        if(itemOnHand.durability >= 0.toShort()){
                            itemOnHand.durability = (itemOnHand.durability + 2).toShort()
                            return
                        }
                    }
                }
            }
        }
    }
}
