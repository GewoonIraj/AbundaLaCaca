package dev.gewooniraj.abundalacaca.events

import io.papermc.paper.event.player.PlayerArmSwingEvent
import net.kyori.adventure.sound.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class PlayerArmSwing : Listener {
    @EventHandler
    fun onHit(event: PlayerArmSwingEvent) {
        val randomPitches = listOf(
            Sound.sound(org.bukkit.Sound.ENTITY_PLAYER_ATTACK_NODAMAGE, Sound.Source.PLAYER, 1f, 0.95f),
            Sound.sound(org.bukkit.Sound.ENTITY_PLAYER_ATTACK_NODAMAGE, Sound.Source.PLAYER, 1f, 1f),
            Sound.sound(org.bukkit.Sound.ENTITY_PLAYER_ATTACK_NODAMAGE, Sound.Source.PLAYER, 1f, 1.05f),
            Sound.sound(org.bukkit.Sound.ENTITY_PLAYER_ATTACK_NODAMAGE, Sound.Source.PLAYER, 1f, 1.1f)
        )
        val player = event.player
        player.playSound(randomPitches.random())

        // TODO (Unfinished):
        //  - Set some sort of cooldown, feels way too spammy currently.
        //  - Must only be triggered when punching the air.
    }
}