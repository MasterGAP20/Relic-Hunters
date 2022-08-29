package com.mastergap.relicshunter.commands

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Effect
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class SpawnSeller : CommandExecutor{

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = sender as Player
        val location = player.location

        val seller = player.world.spawnEntity(location,EntityType.VILLAGER) as LivingEntity
        seller.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,Int.MAX_VALUE,50))
        seller.customName(Component.text("Relic Expert"))
        return true
    }
}