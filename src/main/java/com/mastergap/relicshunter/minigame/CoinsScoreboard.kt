package com.mastergap.relicshunter.minigame

import org.bukkit.Bukkit
import org.bukkit.ChatColor
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
    }

    companion object{
        var totalcoins = 0

         fun createBoard(player: Player){
            val manager = Bukkit.getScoreboardManager()
            val board = manager.newScoreboard
            val obj = board.registerNewObjective("CoinScoreboard", "DUMMY","Coins")
            obj.displaySlot = DisplaySlot.SIDEBAR
            val score = obj.getScore("${ChatColor.YELLOW} Coins: $totalcoins")
            score.score = 1
            player.scoreboard = board
        }
    }

}