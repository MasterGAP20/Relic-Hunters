package com.mastergap.relicshunter.minigame

import com.mastergap.relicshunter.Msg
import com.mastergap.relicshunter.commands.SpawnSeller
import com.mastergap.relicshunter.dungeon.Generator
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.meta.Damageable
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot

class SellNPC : Listener {

    @EventHandler
    fun sell(event: PlayerInteractEntityEvent){
        val player = event.player
        val entity = event.rightClicked

        if(entity.scoreboardTags.contains("seller")){
            val item = player.inventory.itemInMainHand
            if(item.itemMeta?.persistentDataContainer?.has(NamespacedKey("mastergap","weapons")) == true){
                val value = (item.type.maxDurability-item.durability) * 10
                CoinsScoreboard.map[player] = CoinsScoreboard.map[player]!! + value
                CoinsScoreboard.createBoard(player)
                item.subtract()
                player.world.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP,0.5f,1.0f)
                Msg.send(player,"${ChatColor.GREEN}Sold for $value coins")
            }
        }
        if(entity.scoreboardTags.contains("return")){
            for(team in Bukkit.getScoreboardManager().mainScoreboard.teams) {
                val c = Generator.initialCoords
                var teamname = ""
                var location: Location
                if (team.hasEntry(player.name) and Generator.dungeonGenerated) {
                    teamname = team.name
                    if(teamname == "team1"){
                        location = Location(player.world,
                            c?.x?.plus(Generator.team1Room.x) ?: 0.0,
                            c?.y?.plus(3.5) ?: 0.0,
                            c?.z?.plus(Generator.team1Room.z) ?: 0.0
                        )
                        player.teleport(location)
                    }
                    if(teamname == "team2"){
                        location = Location(player.world,
                            c?.x?.plus(Generator.team2Room.x) ?: 0.0,
                            c?.y?.plus(3.5) ?: 0.0,
                            c?.z?.plus(Generator.team2Room.z) ?: 0.0
                        )
                        player.teleport(location)
                    }
                }
            }
        }
        if(entity.scoreboardTags.contains("surface")) { player.teleport(Location(player.world,0.5,3.0,0.5,180.0f,0.0f)) }
    }

}