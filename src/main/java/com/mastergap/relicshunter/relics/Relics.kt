package com.mastergap.relicshunter.relics

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
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
    val style = Style.style(TextColor.fromHexString("#8182d0"),TextDecoration.ITALIC.withState(false))

    val masterSword = ItemStack(Material.DIAMOND_SWORD,1)
    val masterSwordLore = listOf<Component>(Component.text("Attacking will shoot a wave of energy.", style))
    val khopesh = ItemStack(Material.GOLDEN_SWORD,1)
    val khopeshLore = listOf<Component>(Component.text("Just a regular golden sword. Yep, nothing special.", style))
    val sharpStick = ItemStack(Material.WOODEN_SWORD,1)
    val sharpStickLore = listOf<Component>(Component.text("Useless.", style))
    val archangelSword = ItemStack(Material.NETHERITE_SWORD,1)
    val archangelSwordLore = listOf<Component>(Component.text("Shoots a jet of fire, like a flamethrower.", style))

    fun init(){
        createRelicSword("Master Sword", masterSwordLore, 10.0, 1521, 2.6, "masterSword", masterSword)
        createRelicSword("Khopesh", khopeshLore,.0,17,2.6, "khopesh", khopesh)
        createRelicSword("Sharp Stick", sharpStickLore,.0,58,2.6, "sharpSitck", sharpStick)
        createRelicSword("Archangel's Blade", archangelSwordLore,6.0,1981,2.6, "archangelBlade", archangelSword)
    }

    private fun createRelicSword(name: String, lore: List<Component>, dmg: Double, dura: Int, speed: Double, type: String, item: ItemStack){
        val relicMeta = item.itemMeta
        setDamage(dmg,relicMeta)
        setAtkSpeed(speed,relicMeta)
        relicMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES)
        relicMeta.displayName(Component.text(name, Style.style(TextColor.fromHexString("#6fa7f7"), TextDecoration.ITALIC.withState(false))))
        relicMeta.lore(lore)
        relicMeta.persistentDataContainer.set(NamespacedKey("mastergap","weapons"), PersistentDataType.STRING, type)
        relicMeta.addEnchant(Enchantment.OXYGEN,1,true)
        if(name == "Archangel's Blade"){
            relicMeta.addEnchant(Enchantment.FIRE_ASPECT,1,true)
        }
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