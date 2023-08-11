package dev.gewooniraj.abundalacaca.inventories.recipesmenu.crafting

import org.bukkit.event.Listener
import org.bukkit.inventory.Inventory
import java.util.*

class RecipeGrassBlock : Listener {

    companion object {
        val recipeGrassBlock: MutableMap<UUID, Inventory> = mutableMapOf()
    }
}