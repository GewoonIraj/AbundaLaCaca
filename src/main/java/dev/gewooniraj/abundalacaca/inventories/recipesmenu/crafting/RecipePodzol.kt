package dev.gewooniraj.abundalacaca.inventories.recipesmenu.crafting

import org.bukkit.event.Listener
import org.bukkit.inventory.Inventory
import java.util.*

class RecipePodzol : Listener {

	companion object {
		val recipePodzol: MutableMap<UUID, Inventory> = mutableMapOf()
	}
}