package com.mastergap.relicshunter.commands

import com.mastergap.relicshunter.Msg
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
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

class SpawnSurface : CommandExecutor{

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(!sender.hasPermission("admin")) {
            Msg.send(sender, "no perms")
            return true
        }
        val player = sender as Player
        val location = player.location

        val seller = player.world.spawnEntity(location,EntityType.VILLAGER) as LivingEntity
        seller.isInvulnerable = true
        seller.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,20000000,255))
        seller.customName(Component.text("To the Surface", Style.style(TextColor.fromHexString("#ffbf2b"))))
        seller.isCustomNameVisible = false
        seller.addScoreboardTag("surface")
        seller.setAI(false)
        seller.isSilent = true
        return true
    }
}