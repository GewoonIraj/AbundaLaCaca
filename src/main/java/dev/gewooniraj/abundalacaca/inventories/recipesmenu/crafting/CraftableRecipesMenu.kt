package dev.gewooniraj.abundalacaca.inventories.recipesmenu.crafting

import org.bukkit.event.Listener
import org.bukkit.inventory.Inventory
import java.util.*

class CraftableRecipesMenu : Listener {

    companion object {
        val craftableRecipesMenu: MutableMap<UUID, Inventory> = mutableMapOf()
    }
}