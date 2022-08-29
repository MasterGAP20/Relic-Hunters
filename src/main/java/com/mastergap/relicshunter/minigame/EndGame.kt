package com.mastergap.relicshunter.minigame

import com.mastergap.relicshunter.Msg
import com.mastergap.relicshunter.dungeon.Generator
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class EndGame : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(!sender.hasPermission("admin")) {
            Msg.send(sender, "no perms")
            return true
        }
        val sb = Bukkit.getScoreboardManager().mainScoreboard
        var teamname = ""
        var coinsTeam1 = 0
        var coinsTeam2 = 0
        for(player in Bukkit.getOnlinePlayers()){
            for(team in sb.teams){
                if(team.hasEntry(player.name)){
                    teamname = team.name
                    player.teleport(Location(player.world,2.0,3.0,2.0))
                    player.inventory.clear()
                }
                if(teamname == "team1"){
                    coinsTeam1 += CoinsScoreboard.map[player]!!
                    CoinsScoreboard.map[player] = 0
                    CoinsScoreboard.createBoard(player)
                }
                if(teamname == "team2"){
                    coinsTeam2 += CoinsScoreboard.map[player]!!
                    CoinsScoreboard.map[player] = 0
                    CoinsScoreboard.createBoard(player)
                }
            }
        }
        for(player in Bukkit.getOnlinePlayers()){
            if(coinsTeam1 > coinsTeam2){
                player.sendTitle("${ChatColor.YELLOW}Team 1 wins","Total coins: $coinsTeam1")
            }
            if(coinsTeam2 > coinsTeam1){
                player.sendTitle("${ChatColor.YELLOW}Team 2 wins","Total coins: $coinsTeam2")
            }
            if(coinsTeam1 == coinsTeam2){
                player.sendTitle("${ChatColor.YELLOW}There's a ${ChatColor.RED} Draw","Total coins: $coinsTeam1")
            }
        }
        return true
    }
}