package dev.gewooniraj.abundalacaca.recipes.crafting

import dev.gewooniraj.abundalacaca.AbundaLaCaca
import dev.gewooniraj.abundalacaca.namespacedkeys.Craftables
import org.bukkit.Material
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapelessRecipe
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

object GrassBlock : Listener {

    fun init() {
        grassBlock()
    }

    private val plugin: Plugin = JavaPlugin.getPlugin(AbundaLaCaca::class.java)
    private fun grassBlock() {
        val grassBlock = ItemStack(Material.GRASS_BLOCK, 1)
        val recipe = ShapelessRecipe(Craftables.GRASS_BLOCK.key, grassBlock)
        recipe.addIngredient(1, Material.DIRT)
        recipe.addIngredient(1, Material.WHEAT_SEEDS)
        plugin.server.addRecipe(recipe)
    }
}