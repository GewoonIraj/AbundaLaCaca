package dev.gewooniraj.abundalacaca.inventories

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
        val testGui: MutableMap<UUID, Inventory> = mutableMapOf()
    }

    fun opens(player: Player) {
        val inventory: Inventory = Bukkit.createInventory(player, 54, Component.text("Recipes Menu"))

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
        val vItem = ItemStack(Material.VINE, 1)
        val vMeta = vItem.itemMeta
        vMeta.displayName(Component.empty())
        vItem.itemMeta = vMeta
        val svItem = ItemStack(Material.SCULK_VEIN, 1)
        val svMeta = svItem.itemMeta
        svMeta.displayName(Component.empty())
        svItem.itemMeta = svMeta

        for(i in 8..9) {
            inventory.setItem(i, wvItem)
        }
        for(i in 17..18) {
            inventory.setItem(i, wvItem)
        }
        for(i in 26..29) {
            inventory.setItem(i, wvItem)
        }
        for(i in 33..35) {
            inventory.setItem(i, wvItem)
        }
        for(i in 45..47) {
            inventory.setItem(i, wvItem)
        }
        for(i in 51..53) {
            inventory.setItem(i, wvItem)
        }
        for(i in 1..3) {
            inventory.setItem(i, vItem)
        }
        for(i in 5..7) {
            inventory.setItem(i, vItem)
        }
        for(i in 19..21) {
            inventory.setItem(i, vItem)
        }
        for(i in 23..25) {
            inventory.setItem(i, vItem)
        }
        for(i in 30..32) {
            inventory.setItem(i, svItem)
        }
        for(i in 36..39) {
            inventory.setItem(i, svItem)
        }
        for(i in 41..44) {
            inventory.setItem(i, svItem)
        }
        for(i in 48..50) {
            inventory.setItem(i, svItem)
        }
        for(slot in arrayOf(0, 4, 13, 22)) {
            inventory.setItem(slot, wvItem)
        }
        for(slot in arrayOf(10, 12, 14, 16)) {
            inventory.setItem(slot, vItem)
        }
        inventory.setItem(11, crItem)
        inventory.setItem(15, srItem)
        inventory.setItem(40, cItem)

        testGui[player.uniqueId] = inventory
        player.openInventory(inventory)
    }

    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        val player = event.whoClicked
        val playerUuid = event.whoClicked.uniqueId

        if(event.inventory == testGui[playerUuid]) {
            event.isCancelled = true
            if(event.currentItem == null) return

            when(event.slot) {
                11 -> {
                    player.playSound(
                        Sound.sound(
                            org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, Sound.Source.PLAYER, 1f, 2f))
                }
                15 -> {
                    player.playSound(
                        Sound.sound(
                            org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, Sound.Source.PLAYER, 1f, 2f))
                }
                40 -> {
                    player.playSound(
                        Sound.sound(
                        org.bukkit.Sound.BLOCK_ENDER_CHEST_CLOSE, Sound.Source.PLAYER, 1f, 1f))
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

        if(testGui.containsKey(playerUuid)) {
            player.playSound(
                Sound.sound(
                    org.bukkit.Sound.BLOCK_ENDER_CHEST_CLOSE, Sound.Source.PLAYER, 1f, 1f))
            testGui.remove(playerUuid)
        }
    }
}