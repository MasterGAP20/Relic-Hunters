package com.mastergap.relicshunter.relics

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
import java.util.*

object Relics {
    var masterSword = ItemStack(Material.DIAMOND_SWORD,1)

    fun init(){
        createRelicSword("Master Sword", 10.0, 1521, 2.6, masterSword)
    }

    private fun createRelicSword(name: String, dmg: Double, dura: Int, speed: Double, item: ItemStack){
        val relicMeta = item.itemMeta
        setDamage(dmg,relicMeta)
        setAtkSpeed(speed,relicMeta)
        relicMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES)
        relicMeta.displayName(Component.text(name))
        relicMeta.persistentDataContainer.set(NamespacedKey("mastergap","weapons"), PersistentDataType.STRING, "masterSword")
        relicMeta.addEnchant(Enchantment.OXYGEN,1,true)
        (relicMeta as Damageable).damage = dura
        item.itemMeta = relicMeta
    }

    private fun setDamage(d: Double, meta: ItemMeta) {
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, AttributeModifier(UUID.randomUUID(), "damage", d-1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND))
    }

    private fun setAtkSpeed(d: Double, meta: ItemMeta) {
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, AttributeModifier(UUID.randomUUID(), "attackspeed", d-4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND))
    }

    private fun setMvSpeed(d: Double, meta: ItemMeta) {
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, AttributeModifier(UUID.randomUUID(), "movementspeed", d-0.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND))
    }

    private fun setDef(d: Double, meta: ItemMeta) {
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, AttributeModifier(UUID.randomUUID(), "defense", d, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND))
        if(d>0)meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, AttributeModifier(UUID.randomUUID(), "toughness", 2.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND))
    }

}