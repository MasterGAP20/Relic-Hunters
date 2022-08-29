package com.mastergap.relicshunter.minigame

import com.mastergap.relicshunter.Msg
import com.mastergap.relicshunter.dungeon.Generator
import org.bukkit.Bukkit
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
                }
                if(teamname == "team1"){
                    coinsTeam1 += CoinsScoreboard.map[player]!!
                }
                if(teamname == "team2"){
                    coinsTeam2 += CoinsScoreboard.map[player]!!
                }
            }
        }

        if(coinsTeam1 > coinsTeam2){

        }
        if(coinsTeam2 < coinsTeam2){

        }

        return true
    }
}