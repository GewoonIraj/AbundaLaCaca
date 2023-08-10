package dev.gewooniraj.abundalacaca.events

import dev.gewooniraj.abundalacaca.namespacedkeys.Craftables
import dev.gewooniraj.abundalacaca.namespacedkeys.Smeltables
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoin : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        val allCraftables = Craftables.values()
        val allSmeltables = Smeltables.values()

        discoverRecipes(player, allCraftables)
        discoverRecipes(player, allSmeltables)
    }
    private fun discoverRecipes(player: Player, recipes: Array<out Enum<*>>) {
        for (recipe in recipes) {
            when (recipe) {
                is Craftables -> player.discoverRecipe(recipe.key)
                is Smeltables -> player.discoverRecipe(recipe.key)
            }
        }
    }
}