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
    val style2 = Style.style(TextColor.fromHexString("#6364a8"),TextDecoration.ITALIC.withState(false))

    val masterSword = ItemStack(Material.WOODEN_SWORD,1)
    val masterSwordLore = Component.text("Attacking will shoot a wave of energy.", style)
    val khopesh = ItemStack(Material.WOODEN_SWORD,1)
    val khopeshLore = Component.text("Just a regular golden sword. Yep, nothing special.", style)
    val sharpStick = ItemStack(Material.WOODEN_SWORD,1)
    val sharpStickLore = Component.text("Useless.", style)
    val archangelSword = ItemStack(Material.WOODEN_SWORD,1)
    val archangelSwordLore = Component.text("Shoots a jet of fire, like a flamethrower.", style)
    val coolLookingRock = ItemStack(Material.WOODEN_SWORD, 1)
    val coolLookingRockLore = Component.text("It definitely can hurt someone.", style)
    val neptuneTrident = ItemStack(Material.TRIDENT, 1)
    val neptuneTridentLore = Component.text("Holding it makes you faster in water. Very loyal to its user.", style)

    val relics = listOf(masterSword, khopesh, sharpStick, archangelSword, coolLookingRock, neptuneTrident)

    fun init(){
        createRelicSword("Master Sword", masterSwordLore, 10.0, 40, 1.6, "masterSword", masterSword, 1)
        createRelicSword("Khopesh", khopeshLore,4.0,15,1.6, "khopesh", khopesh, 2)
        createRelicSword("Sharp Stick", sharpStickLore,3.0,1,1.6, "sharpSitck", sharpStick, 3)
        createRelicSword("Archangel's Blade", archangelSwordLore,6.0,50,1.6, "archangelBlade", archangelSword, 4)
        createRelicSword("A cool lookin' rock", coolLookingRockLore, 9.0, 30, 0.8, "coolLookingRock", coolLookingRock, 5)
        createRelicSword("Neptune's Trident", neptuneTridentLore, 10.0, 60, 1.0, "neptuneTrident", neptuneTrident, 1)
    }

    private fun createRelicSword(name: String, lore: Component, dmg: Double, uses: Int, speed: Double, type: String, item: ItemStack, custommodeldata: Int){
        val relicMeta = item.itemMeta
        setDamage(dmg,relicMeta)
        setAtkSpeed(speed,relicMeta)
        relicMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES)
        relicMeta.displayName(Component.text(name, Style.style(TextColor.fromHexString("#888afc"), TextDecoration.ITALIC.withState(false))))
        relicMeta.lore(listOf(
            lore,
            Component.empty(),
            Component.text("Stats: ",style2),
            Component.text("  Attack Damage: $dmg",style2),
            Component.text("  Attack Speed: $speed",style2)
        ))
        relicMeta.persistentDataContainer.set(NamespacedKey("mastergap","weapons"), PersistentDataType.STRING, type)
        //relicMeta.addEnchant(Enchantment.OXYGEN,1,true)
        relicMeta.setCustomModelData(custommodeldata)
        if(name == "Archangel's Blade"){
            relicMeta.addEnchant(Enchantment.FIRE_ASPECT,1,true)
        }
        if(name == "Neptune's Trident"){
            relicMeta.addEnchant(Enchantment.LOYALTY,3,true)
        }
        (relicMeta as Damageable).damage = item.type.maxDurability-uses
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