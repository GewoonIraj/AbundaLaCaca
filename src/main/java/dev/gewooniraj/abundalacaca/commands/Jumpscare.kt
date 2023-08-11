package dev.gewooniraj.abundalacaca.commands

import dev.gewooniraj.abundalacaca.ChatUtil
import dev.gewooniraj.abundalacaca.Messages
import net.kyori.adventure.sound.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Jumpscare : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) {
            sender.sendMessage(Messages.messageType(Messages.NO_PLAYER))
        } else {
            sender.sendMessage(ChatUtil.textFormat("&5I mean... you kinda asked for it, right?"))
            sender.playSound(Sound.sound(org.bukkit.Sound.ENTITY_ENDER_DRAGON_DEATH, Sound.Source.PLAYER, 2f, 1f))
        }
        return true
    }
}