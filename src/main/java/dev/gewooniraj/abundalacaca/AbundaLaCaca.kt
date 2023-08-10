package dev.gewooniraj.abundalacaca

import dev.gewooniraj.abundalacaca.commands.Jumpscare
import dev.gewooniraj.abundalacaca.commands.Recipes
import dev.gewooniraj.abundalacaca.events.PlayerRecipeDiscover
import dev.gewooniraj.abundalacaca.events.PlayerDropItem
import dev.gewooniraj.abundalacaca.events.PlayerJoin
import dev.gewooniraj.abundalacaca.inventories.recipesmenu.RecipesMenu
import dev.gewooniraj.abundalacaca.recipes.crafting.EnchantedGoldenApple
import dev.gewooniraj.abundalacaca.recipes.crafting.GrassBlock
import dev.gewooniraj.abundalacaca.recipes.smelting.Leather
import org.bukkit.plugin.java.JavaPlugin

class AbundaLaCaca : JavaPlugin() {

    override fun onEnable() {
        server.consoleSender.sendMessage(Messages.messageType(Messages.PLUGIN_ENABLED))

        registerCommands()
        registerEvents()
        registerInventories()
        registerCraftingRecipes()
        registerSmeltingRecipes()
    }

    private fun registerCommands() {
        getCommand("jumpscare")?.setExecutor(Jumpscare())
        getCommand("recipes")?.setExecutor(Recipes())
    }

    private fun registerEvents() {
//        server.pluginManager.registerEvents(PlayerArmSwing(), this)
        server.pluginManager.registerEvents(PlayerDropItem(), this)
        server.pluginManager.registerEvents(PlayerJoin(), this)
        server.pluginManager.registerEvents(PlayerRecipeDiscover(), this)
    }

    private fun registerInventories() {
        server.pluginManager.registerEvents(RecipesMenu(), this)
    }

    private fun registerCraftingRecipes() {
        EnchantedGoldenApple.init()
        GrassBlock.init()
    }

    private fun registerSmeltingRecipes() {
        Leather.init()
    }

    override fun onDisable() {
        server.consoleSender.sendMessage(Messages.messageType(Messages.PLUGIN_DISABLED))
    }
}