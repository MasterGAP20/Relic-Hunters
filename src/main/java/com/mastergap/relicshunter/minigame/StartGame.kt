package com.mastergap.relicshunter.minigame

import com.mastergap.relicshunter.Msg
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.scoreboard.Team

class StartGame : CommandExecutor {

    fun canStart(): Boolean{
        var team1Members = 0
        var team2Members = 0
        val sb = Bukkit.getScoreboardManager().mainScoreboard
        var teamname = ""
        for(player in Bukkit.getOnlinePlayers()){
            for(team in sb.teams){
                if(team.hasEntry(player.name)){
                    teamname = team.name
                }
                if(teamname == "team1"){
                    team1Members++
                    Bukkit.getLogger().info("Team 1 members: $team1Members")
                }
                if(teamname == "team2"){
                    team2Members++
                    Bukkit.getLogger().info("Team 2 members: $team2Members")
                }
                teamname = ""
            }
        }
        if(team1Members == team2Members && team1Members > 0){
            return true
        }
        return false
    }

    fun movePlayers(){
        val sb = Bukkit.getScoreboardManager().mainScoreboard
        var teamname = ""
        for(player in Bukkit.getOnlinePlayers()){
            var location: Location
            for(team in sb.teams){
                if(team.hasEntry(player.name)){
                    teamname = team.name
                }
                if(teamname == "team1"){
                    location = Location(player.world,-70.0, 3.0, 0.0,)
                    player.teleport(location)
                }
                if(teamname == "team2"){
                    location = Location(player.world,70.0, 3.0, 0.0,)
                    player.teleport(location)
                }
            }
        }
    }

    fun resetCoins(){
        for(player in Bukkit.getOnlinePlayers()){
            CoinsScoreboard.totalcoins = 0
            CoinsScoreboard.createBoard(player)
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(canStart()){
            resetCoins()
            movePlayers()
            return true
        }
        return true
    }
}