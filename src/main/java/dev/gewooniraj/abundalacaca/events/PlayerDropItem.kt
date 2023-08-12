package dev.gewooniraj.abundalacaca.events

import net.kyori.adventure.sound.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerDropItemEvent

class PlayerDropItem : Listener {

	@EventHandler
	fun onDrop(event: PlayerDropItemEvent) {
		val randomPitches = listOf(
			Sound.sound(org.bukkit.Sound.ENTITY_ITEM_PICKUP, Sound.Source.PLAYER, 1f, 0.55f),
			Sound.sound(org.bukkit.Sound.ENTITY_ITEM_PICKUP, Sound.Source.PLAYER, 1f, 0.6f),
			Sound.sound(org.bukkit.Sound.ENTITY_ITEM_PICKUP, Sound.Source.PLAYER, 1f, 0.65f),
			Sound.sound(org.bukkit.Sound.ENTITY_ITEM_PICKUP, Sound.Source.PLAYER, 1f, 0.7f)
		)
		val player = event.player
		player.playSound(randomPitches.random())
	}
}