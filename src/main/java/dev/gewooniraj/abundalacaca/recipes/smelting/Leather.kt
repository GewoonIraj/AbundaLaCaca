package dev.gewooniraj.abundalacaca.recipes.smelting

import dev.gewooniraj.abundalacaca.AbundaLaCaca
import dev.gewooniraj.abundalacaca.namespacedkeys.Smeltables
import org.bukkit.Material
import org.bukkit.event.Listener
import org.bukkit.inventory.FurnaceRecipe
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

object Leather : Listener {

    fun init() {
        leather()
    }

    private val plugin: Plugin = JavaPlugin.getPlugin(AbundaLaCaca::class.java)
    private fun leather() {
        val recipe = FurnaceRecipe(
                Smeltables.keyType(Smeltables.LEATHER),
                ItemStack(Material.LEATHER),
                Material.ROTTEN_FLESH,
                0.35f,
                200)
        plugin.server.addRecipe(recipe)
    }
}