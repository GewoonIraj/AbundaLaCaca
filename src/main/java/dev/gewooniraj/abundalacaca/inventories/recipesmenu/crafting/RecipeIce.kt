package dev.gewooniraj.abundalacaca.inventories.recipesmenu.crafting

import org.bukkit.event.Listener
import org.bukkit.inventory.Inventory
import java.util.*

class RecipeIce : Listener {

    companion object {
        val recipeIce: MutableMap<UUID, Inventory> = mutableMapOf()
    }
}