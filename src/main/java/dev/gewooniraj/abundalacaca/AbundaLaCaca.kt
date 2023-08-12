package dev.gewooniraj.abundalacaca

import dev.gewooniraj.abundalacaca.commands.Jumpscare
import dev.gewooniraj.abundalacaca.commands.Recipes
import dev.gewooniraj.abundalacaca.events.PlayerArmSwing
import dev.gewooniraj.abundalacaca.events.PlayerDropItem
import dev.gewooniraj.abundalacaca.events.PlayerJoin
import dev.gewooniraj.abundalacaca.events.PlayerRecipeDiscover
import dev.gewooniraj.abundalacaca.inventories.recipesmenu.crafting.MainCraftingMenu
import dev.gewooniraj.abundalacaca.inventories.recipesmenu.main.MainRecipesMenu
import dev.gewooniraj.abundalacaca.inventories.recipesmenu.smelting.MainSmeltingMenu
import dev.gewooniraj.abundalacaca.recipes.RecipeManager
import org.bukkit.plugin.java.JavaPlugin

class AbundaLaCaca : JavaPlugin() {

	override fun onEnable() {
		server.consoleSender.sendMessage(Messages.messageType(Messages.PLUGIN_ENABLED))

		registerCommands()
		registerEvents()
		registerInventories()
		registerRecipes()
	}

	override fun onDisable() {
		server.consoleSender.sendMessage(Messages.messageType(Messages.PLUGIN_DISABLED))
	}

	private fun registerCommands() {
		getCommand("jumpscare")?.setExecutor(Jumpscare())
		getCommand("recipes")?.setExecutor(Recipes())
	}

	private fun registerEvents() {
		server.pluginManager.registerEvents(PlayerArmSwing(), this)
//        server.pluginManager.registerEvents(PlayerCommandPreprocess(), this)
		server.pluginManager.registerEvents(PlayerDropItem(), this)
		server.pluginManager.registerEvents(PlayerJoin(), this)
		server.pluginManager.registerEvents(PlayerRecipeDiscover(), this)
	}

	private fun registerInventories() {
		server.pluginManager.registerEvents(MainCraftingMenu(), this)
		server.pluginManager.registerEvents(MainRecipesMenu(), this)
		server.pluginManager.registerEvents(MainSmeltingMenu(), this)
	}

	private fun registerRecipes() {
		val recipesManager = RecipeManager()
		val recipesFolder = dataFolder.resolve("recipes")
		recipesManager.registerCustomRecipes(recipesFolder)

		server.consoleSender.sendMessage(Messages.messageType(Messages.TOTAL_CRAFTING_RECIPES))
		server.consoleSender.sendMessage(Messages.messageType(Messages.TOTAL_SMELTING_RECIPES))
	}
}