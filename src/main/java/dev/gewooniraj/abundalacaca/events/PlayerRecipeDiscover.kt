package dev.gewooniraj.abundalacaca.events

import dev.gewooniraj.abundalacaca.recipes.RecipeUtil
import net.kyori.adventure.sound.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRecipeDiscoverEvent

class PlayerRecipeDiscover : Listener {

    @EventHandler
    fun onDiscover(event: PlayerRecipeDiscoverEvent) {
        val player = event.player
        val discoveredRecipe = event.recipe
        val recipeTypes = listOf("crafting", "smelting")

        for (recipeType in recipeTypes) {
            val recipeKeys = RecipeUtil.getRecipeKeys(recipeType)
            if (recipeKeys.contains(discoveredRecipe)) {
                player.playSound(
                    Sound.sound(org.bukkit.Sound.UI_TOAST_CHALLENGE_COMPLETE, Sound.Source.PLAYER, 1f, 1f)
                )
            }
        }
    }
}