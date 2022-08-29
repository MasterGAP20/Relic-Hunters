package com.mastergap.relicshunter.commands

import com.mastergap.relicshunter.Msg
import com.mastergap.relicshunter.relics.Relics
import org.bukkit.block.CommandBlock
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

class SummonRelic : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(!sender.hasPermission("admin")) {
            Msg.send(sender, "no perms")
            return true
        }
        val player = sender as Player
        val world = player.world

        if(args.isEmpty()){
            Msg.send(player,"You need to select a relic")
            return true
        }

        when(args[0]){
            "MasterSword" -> world.dropItem(player.location,Relics.masterSword)
            "Khopesh" -> world.dropItem(player.location,Relics.khopesh)
            "SharpStick" -> world.dropItem(player.location,Relics.sharpStick)
            "ArchangelBlade" -> world.dropItem(player.location,Relics.archangelSword)
            "CoolLookingRock" -> world.dropItem(player.location,Relics.coolLookingRock)
        }

        return true
    }
}