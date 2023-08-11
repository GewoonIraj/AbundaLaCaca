package dev.gewooniraj.abundalacaca.events

import dev.gewooniraj.abundalacaca.AbundaLaCaca
import dev.gewooniraj.abundalacaca.recipes.RecipeUtil
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class PlayerJoin : Listener {

    private val plugin: Plugin = JavaPlugin.getPlugin(AbundaLaCaca::class.java)

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        val allCraftableKeys = RecipeUtil.getRecipeKeys(plugin, "crafting")
        val allSmeltableKeys = RecipeUtil.getRecipeKeys(plugin, "smelting")

        discoverRecipes(player, allCraftableKeys)
        discoverRecipes(player, allSmeltableKeys)
    }

    private fun discoverRecipes(player: Player, recipeKeys: List<NamespacedKey>) {
        for (recipeKey in recipeKeys) {
            player.discoverRecipe(recipeKey)
        }
    }
}