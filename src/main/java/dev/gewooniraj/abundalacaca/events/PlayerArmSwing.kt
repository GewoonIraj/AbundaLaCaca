package dev.gewooniraj.abundalacaca.events

import io.papermc.paper.event.player.PlayerArmSwingEvent
import net.kyori.adventure.sound.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class PlayerArmSwing : Listener {

	@EventHandler
	fun onHit(event: PlayerArmSwingEvent) {
		val player = event.player
		val randomPitches = listOf(
			Sound.sound(org.bukkit.Sound.ENTITY_PLAYER_ATTACK_NODAMAGE, Sound.Source.PLAYER, 0.5f, 0.95f),
			Sound.sound(org.bukkit.Sound.ENTITY_PLAYER_ATTACK_NODAMAGE, Sound.Source.PLAYER, 0.5f, 1f),
			Sound.sound(org.bukkit.Sound.ENTITY_PLAYER_ATTACK_NODAMAGE, Sound.Source.PLAYER, 0.5f, 1.05f),
			Sound.sound(org.bukkit.Sound.ENTITY_PLAYER_ATTACK_NODAMAGE, Sound.Source.PLAYER, 0.5f, 1.1f)
		)
		if (player.getTargetBlock(null, 5).type.isAir) {
			player.playSound(randomPitches.random())
		}
	}
}