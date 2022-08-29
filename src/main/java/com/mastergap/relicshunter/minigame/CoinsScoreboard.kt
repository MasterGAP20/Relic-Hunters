package com.mastergap.relicshunter.minigame

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.scoreboard.DisplaySlot

class CoinsScoreboard : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        map[event.player] = 0
        createBoard(event.player)
    }

    companion object {
        var map = HashMap<Player, Int>()

        fun createBoard(player: Player) {
            val manager = Bukkit.getScoreboardManager()
            val board = manager.newScoreboard
            val obj = board.registerNewObjective(
                "CoinScoreboard", "DUMMY", Component.text(
                    "Coins", Style.style(
                        TextColor.fromHexString("#ffbf2b"), TextDecoration.ITALIC.withState(false)
                    )
                )
            )
            obj.displaySlot = DisplaySlot.SIDEBAR
            val score = obj.getScore("${ChatColor.YELLOW} Coins: ${map[player]}")
            score.score = 1
            player.scoreboard = board
        }
    }
}