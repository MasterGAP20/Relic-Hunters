package com.mastergap.relicshunter.minigame

import com.mastergap.relicshunter.Msg
import com.mastergap.relicshunter.dungeon.Generator
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.inventory.ItemStack
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
        if(team1Members == team2Members && team1Members > 0 && Generator.dungeonGenerated){
            return true
        }
        return false
    }

    fun movePlayers(){
        val c = Generator.initialCoords
        val sb = Bukkit.getScoreboardManager().mainScoreboard
        var teamname = ""
        for(player in Bukkit.getOnlinePlayers()){
            player.sendMessage(c.toString())
            var location: Location
            for(team in sb.teams){
                if(team.hasEntry(player.name)){
                    teamname = team.name
                }
                if(teamname == "team1"){
                    location = Location(player.world,
                        c?.x?.plus(Generator.team1Room.x) ?: 0.0,
                        c?.y?.plus(3.5) ?: 0.0,
                        c?.z?.plus(Generator.team1Room.z) ?: 0.0
                    )
                    player.teleport(location)
                    player.inventory.addItem(ItemStack(Material.BREAD, 32))
                }
                if(teamname == "team2"){
                    location = Location(player.world,
                        c?.x?.plus(Generator.team2Room.x) ?: 0.0,
                        c?.y?.plus(3.5) ?: 0.0,
                        c?.z?.plus(Generator.team2Room.z) ?: 0.0
                    )
                    player.teleport(location)
                    player.inventory.addItem(ItemStack(Material.BREAD, 32))
                }
            }
        }
    }

    fun resetCoins(){
        for(player in Bukkit.getOnlinePlayers()){
            CoinsScoreboard.map[player] = 0
            CoinsScoreboard.createBoard(player)
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(!sender.hasPermission("admin")) {
            Msg.send(sender, "no perms")
            return true
        }
        if(canStart()){
            resetCoins()
            movePlayers()
            return true
        }
        return true
    }
}