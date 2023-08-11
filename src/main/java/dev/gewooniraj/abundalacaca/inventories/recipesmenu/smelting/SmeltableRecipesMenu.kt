package dev.gewooniraj.abundalacaca.inventories.recipesmenu.smelting

import org.bukkit.event.Listener
import org.bukkit.inventory.Inventory
import java.util.*

class SmeltableRecipesMenu : Listener {

    companion object {
        val smeltableRecipesMenu: MutableMap<UUID, Inventory> = mutableMapOf()
    }
}