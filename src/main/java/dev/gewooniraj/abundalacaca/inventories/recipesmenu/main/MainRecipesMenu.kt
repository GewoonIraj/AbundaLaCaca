package dev.gewooniraj.abundalacaca.inventories.recipesmenu.main

import dev.gewooniraj.abundalacaca.inventories.recipesmenu.ItemManager
import dev.gewooniraj.abundalacaca.inventories.recipesmenu.crafting.MainCraftingMenu
import dev.gewooniraj.abundalacaca.inventories.recipesmenu.smelting.MainSmeltingMenu
import dev.gewooniraj.abundalacaca.recipes.RecipeUtil
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import java.util.*

class MainRecipesMenu : Listener {

	companion object {
		val mainRecipesMenu: MutableMap<UUID, Inventory> = mutableMapOf()
	}

	private enum class SlotAction { CRAFTING_RECIPES, SMELTING_RECIPES, CLOSE, NONE }

	private val slotActions = mapOf(
		1 to SlotAction.CRAFTING_RECIPES, 7 to SlotAction.SMELTING_RECIPES, 22 to SlotAction.CLOSE
	)

	fun open(player: Player) {
		val inventory: Inventory = Bukkit.createInventory(player, 27, Component.text("Menu: Recipes"))

		val crItem = ItemManager.createItem(
			Material.CRAFTING_TABLE,
			"&aCrafting Recipes",
			listOf("&7Total Crafting Recipes: &b${RecipeUtil.getRecipeCount("crafting")}", "", "&e➤ Click to view!")
		)
		val srItem = ItemManager.createItem(
			Material.FURNACE,
			"&6Smelting Recipes",
			listOf("&7Total Smelting Recipes: &b${RecipeUtil.getRecipeCount("smelting")}", "", "&e➤ Click to view!")
		)
		val wvItem = ItemManager.createItem(Material.WEEPING_VINES, "", null)
		val svItem = ItemManager.createItem(Material.SCULK_VEIN, "", null)
		val cItem = ItemManager.createItem(Material.BARRIER, "&cClose", listOf("&7➤ To Exit"))

		val wvSlots = listOf(3..5, 18..20, 24..26)
		val svSlots = listOf(8..17)
		val otherSlots = listOf(0, 2, 6, 21, 23)

		wvSlots.forEach { range ->
			range.forEach { slot ->
				inventory.setItem(slot, wvItem)
			}
		}

		svSlots.forEach { range ->
			range.forEach { slot ->
				inventory.setItem(slot, svItem)
			}
		}

		otherSlots.forEach { slot ->
			inventory.setItem(slot, svItem)
		}

		inventory.setItem(1, crItem)
		inventory.setItem(7, srItem)
		inventory.setItem(22, cItem)

		mainRecipesMenu[player.uniqueId] = inventory
		player.openInventory(inventory)
	}

	@EventHandler
	private fun onClick(event: InventoryClickEvent) {
		val player = event.whoClicked as? Player ?: return
		val playerUuid = player.uniqueId
		val clickedSlot = event.slot

		if (event.inventory == mainRecipesMenu[playerUuid]) {
			event.isCancelled = true
			val action = slotActions[clickedSlot] ?: SlotAction.NONE

			when (action) {
				SlotAction.CRAFTING_RECIPES -> {
					player.playSound(
						Sound.sound(
							org.bukkit.Sound.ENTITY_ENDERMAN_TELEPORT, Sound.Source.PLAYER, 1f, 1f
						)
					)
					event.inventory.close()
					MainCraftingMenu().open(player)
				}

				SlotAction.SMELTING_RECIPES -> {
					player.playSound(
						Sound.sound(
							org.bukkit.Sound.ENTITY_ENDERMAN_TELEPORT, Sound.Source.PLAYER, 1f, 1f
						)
					)
					event.inventory.close()
					MainSmeltingMenu().open(player)
				}

				SlotAction.CLOSE -> {
					player.playSound(
						Sound.sound(
							org.bukkit.Sound.BLOCK_ENDER_CHEST_CLOSE, Sound.Source.PLAYER, 1f, 1f
						)
					)
					event.inventory.close()
				}

				SlotAction.NONE -> {
				}
			}
		}
	}

	@EventHandler
	private fun onClose(event: InventoryCloseEvent) {
		val player = event.player as? Player ?: return
		val playerUuid = player.uniqueId

		if (event.reason != InventoryCloseEvent.Reason.PLUGIN) {
			if (mainRecipesMenu.containsKey(playerUuid)) {
				player.playSound(
					Sound.sound(
						org.bukkit.Sound.BLOCK_ENDER_CHEST_CLOSE, Sound.Source.PLAYER, 1f, 1f
					)
				)
				mainRecipesMenu.remove(playerUuid)
			}
		}
	}
}