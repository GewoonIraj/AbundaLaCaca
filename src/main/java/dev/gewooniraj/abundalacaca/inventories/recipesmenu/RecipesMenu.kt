package dev.gewooniraj.abundalacaca.inventories.recipesmenu

import dev.gewooniraj.abundalacaca.ChatUtil
import dev.gewooniraj.abundalacaca.namespacedkeys.Craftables
import dev.gewooniraj.abundalacaca.namespacedkeys.Smeltables
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*

class RecipesMenu : Listener {

    companion object {
        val recipesMenu: MutableMap<UUID, Inventory> = mutableMapOf()
    }

    fun opens(player: Player) {
        val inventory: Inventory = Bukkit.createInventory(player, 27, Component.text("Recipes Menu"))

        val crItem = ItemStack(Material.CRAFTING_TABLE, 1)
        val crMeta = crItem.itemMeta
        crMeta.displayName(ChatUtil.format("&aCrafting Recipes"))
        val crLore = ArrayList<TextComponent>()
        crLore.add(ChatUtil.format("&7Total Crafting Recipes: &b" + Craftables.entries.size))
        crMeta.lore(crLore)
        crItem.itemMeta = crMeta
        val srItem = ItemStack(Material.FURNACE, 1)
        val srMeta = srItem.itemMeta
        srMeta.displayName(ChatUtil.format("&6Smelting Recipes"))
        val srLore = ArrayList<TextComponent>()
        srLore.add(ChatUtil.format("&7Total Smeltable Recipes: &b" + Smeltables.entries.size))
        srMeta.lore(srLore)
        srItem.itemMeta = srMeta
        val cItem = ItemStack(Material.BARRIER, 1)
        val cMeta = cItem.itemMeta
        cMeta.displayName(ChatUtil.format("&cClose"))
        val cLore = ArrayList<TextComponent>()
        cLore.add(ChatUtil.format("&7To Exit"))
        cMeta.lore(cLore)
        cItem.itemMeta = cMeta
        val wvItem = ItemStack(Material.WEEPING_VINES, 1)
        val wvMeta = wvItem.itemMeta
        wvMeta.displayName(Component.empty())
        wvItem.itemMeta = wvMeta
        val svItem = ItemStack(Material.SCULK_VEIN, 1)
        val svMeta = svItem.itemMeta
        svMeta.displayName(Component.empty())
        svItem.itemMeta = svMeta

        for (i in 3..5) {
            inventory.setItem(i, wvItem)
        }
        for (i in 8..17) {
            inventory.setItem(i, svItem)
        }
        for (i in 18..20) {
            inventory.setItem(i, wvItem)
        }
        for (i in 24..26) {
            inventory.setItem(i, wvItem)
        }
        for (slot in arrayOf(0, 2, 6, 21, 23)) {
            inventory.setItem(slot, svItem)
        }
        inventory.setItem(1, crItem)
        inventory.setItem(7, srItem)
        inventory.setItem(22, cItem)

        recipesMenu[player.uniqueId] = inventory
        player.openInventory(inventory)
    }

    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        val player = event.whoClicked
        val playerUuid = event.whoClicked.uniqueId

        if (event.inventory == recipesMenu[playerUuid]) {
            event.isCancelled = true
            if (event.currentItem == null) return

            when (event.slot) {
                1 -> {
                    player.playSound(Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, Sound.Source.PLAYER, 1f, 2f))
                }

                7 -> {
                    player.playSound(Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, Sound.Source.PLAYER, 1f, 2f))
                }

                22 -> {
                    player.playSound(Sound.sound(org.bukkit.Sound.BLOCK_ENDER_CHEST_CLOSE, Sound.Source.PLAYER, 1f, 1f))
                    event.inventory.close()
                }

                else -> {}
            }
        }
    }

    @EventHandler
    fun onClose(event: InventoryCloseEvent) {
        val player = event.player
        val playerUuid = event.player.uniqueId

        if (recipesMenu.containsKey(playerUuid)) {
            player.playSound(Sound.sound(org.bukkit.Sound.BLOCK_ENDER_CHEST_CLOSE, Sound.Source.PLAYER, 1f, 1f))
            recipesMenu.remove(playerUuid)
        }
    }
}