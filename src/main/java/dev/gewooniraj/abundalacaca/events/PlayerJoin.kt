package dev.gewooniraj.abundalacaca.events

import dev.gewooniraj.abundalacaca.recipes.RecipeUtil
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoin : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        val recipeTypes = listOf("crafting", "smelting")

        for (recipeType in recipeTypes) {
            val recipeKeys = RecipeUtil.getRecipeKeys(recipeType)
            discoverRecipes(player, recipeKeys)
        }
    }

    private fun discoverRecipes(player: Player, recipeKeys: List<NamespacedKey>) {
        for (recipeKey in recipeKeys) {
            player.discoverRecipe(recipeKey)
        }
    }
}