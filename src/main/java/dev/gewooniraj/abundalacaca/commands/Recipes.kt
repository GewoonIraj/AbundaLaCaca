package dev.gewooniraj.abundalacaca.commands

import dev.gewooniraj.abundalacaca.Messages
import dev.gewooniraj.abundalacaca.inventories.recipesmenu.main.MainRecipesMenu
import net.kyori.adventure.sound.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Recipes : CommandExecutor {

	/*    fun isInt(s: String): Boolean {
			return try {
				s.toInt()
				true
			} catch(e: NumberFormatException) {
				false
			}
		}*/

	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
		if (sender !is Player) {
			sender.sendMessage(Messages.messageType(Messages.NO_PLAYER))
		} else {
			sender.sendMessage(Messages.messageType(Messages.OPEN_RECIPES_MENU))
			sender.playSound(
				Sound.sound(
					org.bukkit.Sound.BLOCK_ENDER_CHEST_OPEN, Sound.Source.PLAYER, 1f, 1f
				)
			)
			MainRecipesMenu().open(sender)
		}
		return true
	}
}