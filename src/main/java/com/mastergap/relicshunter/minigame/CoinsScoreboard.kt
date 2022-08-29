package com.mastergap.relicshunter.minigame

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot

class CoinsScoreboard : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent){
        createBoard(event.player)
        event.player.bedSpawnLocation = Location(event.player.world, 0.0, 2.0, 0.0)
    }

    companion object{
        var totalcoins = 0

         fun createBoard(player: Player){
            val manager = Bukkit.getScoreboardManager()
            val board = manager.newScoreboard
            val obj = board.registerNewObjective("CoinScoreboard", "DUMMY", Component.text("Coins", Style.style(
                TextColor.fromHexString("#ffbf2b"), TextDecoration.ITALIC.withState(false))))
            obj.displaySlot = DisplaySlot.SIDEBAR
            val score = obj.getScore("${ChatColor.YELLOW} Coins: $totalcoins")
            score.score = 1
            player.scoreboard = board
        }
    }

}