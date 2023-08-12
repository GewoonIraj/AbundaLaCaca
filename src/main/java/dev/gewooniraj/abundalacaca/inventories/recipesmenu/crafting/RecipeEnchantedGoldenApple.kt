package dev.gewooniraj.abundalacaca.inventories.recipesmenu.crafting

import org.bukkit.event.Listener
import org.bukkit.inventory.Inventory
import java.util.*

class RecipeEnchantedGoldenApple : Listener {

	companion object {
		val recipeEnchantedGoldenApple: MutableMap<UUID, Inventory> = mutableMapOf()
	}
}