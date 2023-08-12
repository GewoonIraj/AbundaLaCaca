package dev.gewooniraj.abundalacaca.events

import dev.gewooniraj.abundalacaca.Messages
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class PlayerCommandPreprocess : Listener {

	@EventHandler
	fun onPlayerCommandPreprocess(event: PlayerCommandPreprocessEvent) {
		val player: Player = event.player
		val message: String = event.message
		val command: String = message.substring(1)

		if (command == "plugins" || command == "pl") {
			event.isCancelled = true
			player.sendMessage(Messages.messageType(Messages.UNKNOWN_COMMAND))
		}
		if (player.hasPermission("abundalacaca.admin") || player.isOp) return
	}
}