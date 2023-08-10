package dev.gewooniraj.abundalacaca.events

import dev.gewooniraj.abundalacaca.namespacedkeys.Craftables
import dev.gewooniraj.abundalacaca.namespacedkeys.Smeltables
import net.kyori.adventure.sound.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRecipeDiscoverEvent

class PlayerRecipeDiscover : Listener {

    @EventHandler
    fun onDiscover(event: PlayerRecipeDiscoverEvent) {
        val player = event.player
        val allCraftables = Craftables.entries.toTypedArray()
        val allSmeltables = Smeltables.entries.toTypedArray()

        for (craftable in allCraftables) {
            if (player.hasDiscoveredRecipe(craftable.key)) {
                player.playSound(
                    Sound.sound(org.bukkit.Sound.UI_TOAST_CHALLENGE_COMPLETE, Sound.Source.PLAYER, 1f, 1f)
                )
            }
        }

        for (smeltable in allSmeltables) {
            if (player.hasDiscoveredRecipe(smeltable.key)) {
                player.playSound(
                    Sound.sound(org.bukkit.Sound.UI_TOAST_CHALLENGE_COMPLETE, Sound.Source.PLAYER, 1f, 1f)
                )
            }
        }
    }
}