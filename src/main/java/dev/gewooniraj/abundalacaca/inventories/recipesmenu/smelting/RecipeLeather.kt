package dev.gewooniraj.abundalacaca.inventories.recipesmenu.smelting

import org.bukkit.event.Listener
import org.bukkit.inventory.Inventory
import java.util.*

class RecipeLeather : Listener {

	companion object {
		val mainSmeltingMenu: MutableMap<UUID, Inventory> = mutableMapOf()
	}
}