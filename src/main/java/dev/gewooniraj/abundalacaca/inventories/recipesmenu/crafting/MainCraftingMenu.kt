package dev.gewooniraj.abundalacaca.inventories.recipesmenu.crafting

import org.bukkit.event.Listener
import org.bukkit.inventory.Inventory
import java.util.*

class MainCraftingMenu : Listener {

    companion object {
        val mainCraftingMenu: MutableMap<UUID, Inventory> = mutableMapOf()
    }
}