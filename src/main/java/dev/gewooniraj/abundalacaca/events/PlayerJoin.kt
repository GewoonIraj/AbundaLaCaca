package dev.gewooniraj.abundalacaca.events

import dev.gewooniraj.abundalacaca.namespacedkeys.Craftables
import dev.gewooniraj.abundalacaca.namespacedkeys.Smeltables
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoin : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player

        // Crafting Recipes
        player.discoverRecipe(Craftables.keyType(Craftables.GRASS_BLOCK))
        player.discoverRecipe(Craftables.keyType(Craftables.ENCHANTED_GOLDEN_APPLE))

        // Smelting Recipes
        player.discoverRecipe(Smeltables.keyType(Smeltables.LEATHER))
    }
}