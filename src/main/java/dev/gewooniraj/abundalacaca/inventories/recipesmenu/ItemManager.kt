package dev.gewooniraj.abundalacaca.inventories.recipesmenu

import dev.gewooniraj.abundalacaca.ChatUtil
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object ItemManager {

    fun createItem(material: Material, displayName: String, lore: List<String>): ItemStack {
        val item = ItemStack(material, 1)
        val itemMeta = item.itemMeta
        itemMeta.displayName(ChatUtil.textFormat(displayName))
        val loreComponents = lore.map { ChatUtil.textFormat(it) }
        itemMeta.lore(loreComponents)
        item.itemMeta = itemMeta
        return item
    }
}