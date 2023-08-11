package dev.gewooniraj.abundalacaca.events

import dev.gewooniraj.abundalacaca.AbundaLaCaca
import dev.gewooniraj.abundalacaca.recipes.RecipeUtil
import net.kyori.adventure.sound.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRecipeDiscoverEvent
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class PlayerRecipeDiscover : Listener {

    private val plugin: Plugin = JavaPlugin.getPlugin(AbundaLaCaca::class.java)

    @EventHandler
    fun onDiscover(event: PlayerRecipeDiscoverEvent) {
        val player = event.player
        val discoveredRecipe = event.recipe

        val allCraftables = RecipeUtil.getRecipeKeys(plugin, "crafting")
        val allSmeltables = RecipeUtil.getRecipeKeys(plugin, "smelting")

        if (allCraftables.contains(discoveredRecipe) || allSmeltables.contains(discoveredRecipe)) {
            player.playSound(
                Sound.sound(org.bukkit.Sound.UI_TOAST_CHALLENGE_COMPLETE, Sound.Source.PLAYER, 1f, 1f)
            )
        }
    }
}