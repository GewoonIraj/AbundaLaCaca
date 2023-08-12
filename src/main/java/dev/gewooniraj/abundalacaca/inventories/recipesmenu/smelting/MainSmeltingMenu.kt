package dev.gewooniraj.abundalacaca.inventories.recipesmenu.smelting

import dev.gewooniraj.abundalacaca.inventories.recipesmenu.ItemManager
import dev.gewooniraj.abundalacaca.inventories.recipesmenu.main.MainRecipesMenu
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

class MainSmeltingMenu : Listener {

	companion object {
		val mainSmeltingMenu: MutableMap<UUID, Inventory> = mutableMapOf()
	}

	private enum class SlotAction {
		SMELTING_RECIPES, KNOWN_RECIPE, UNKNOWN_RECIPE, PREVIOUS_PAGE, NEXT_PAGE, CLOSE, NONE
	}

	private val actionSlotMappings = mutableMapOf<Int, SlotAction>().apply {
		(0 until 9).forEach { i ->
			val index = (i * 2) + 18
			val action =
				if (i < ItemManager.getAvailableRecipes("smelting").size) SlotAction.KNOWN_RECIPE else SlotAction.UNKNOWN_RECIPE
			this[index] = action
			if (i < 8) {
				this[index + 1] = SlotAction.NONE
			}
		}
		this[4] = SlotAction.SMELTING_RECIPES
		this[46] = SlotAction.PREVIOUS_PAGE
		this[49] = SlotAction.CLOSE
		this[52] = SlotAction.NEXT_PAGE
	}

	fun open(player: Player) {
		val inventory: Inventory = Bukkit.createInventory(player, 54, Component.text("Menu: Smelting Recipes"))

		val srItem = ItemManager.createItem(
			Material.FURNACE,
			"&6Smelting Recipes",
			listOf("&7Total Smelting Recipes: &b${RecipeUtil.getRecipeCount("smelting")}")
		)
		val wvItem = ItemManager.createItem(Material.WEEPING_VINES, "", emptyList())
		val svItem = ItemManager.createItem(Material.SCULK_VEIN, "", emptyList())
		val uItem = ItemManager.createItem(Material.PAPER, "&3???", listOf("&3★ TBD"))
		val ppItem = ItemManager.createItem(Material.SPECTRAL_ARROW, "&aPrevious Page", listOf("&e➤ Page 1"))
		val rItem = ItemManager.createItem(Material.BARRIER, "&cReturn", listOf("&7➤ To Go Back"))
		val npItem = ItemManager.createItem(Material.SPECTRAL_ARROW, "&aNext Page", listOf("&e➤ Page 1"))

		val wvSlots = listOf(0..2, 6..8)
		val svSlots = listOf(9..17, 36..44)
		val otherSlots = listOf(3, 5, 48, 50)

		for (i in 0 until 9) {
			val index = (i * 2) + 18
			if (i < ItemManager.getAvailableRecipes("smelting").size) {
				val recipeData = ItemManager.getAvailableRecipes("smelting")
				val customItem = ItemManager.createJsonItem("smelting", recipeData[i])
				inventory.setItem(index, customItem)
			} else {
				inventory.setItem(index, uItem)
			}
		}

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

		inventory.setItem(4, srItem)
		inventory.setItem(46, ppItem)
		inventory.setItem(49, rItem)
		inventory.setItem(52, npItem)

		mainSmeltingMenu[player.uniqueId] = inventory
		player.openInventory(inventory)
	}

	@EventHandler
	private fun onClick(event: InventoryClickEvent) {
		val player = event.whoClicked as? Player ?: return
		val playerUuid = player.uniqueId
		val clickedSlot = event.slot

		if (event.inventory == mainSmeltingMenu[playerUuid]) {
			event.isCancelled = true
			val action = actionSlotMappings[clickedSlot] ?: SlotAction.NONE

			when (action) {
				SlotAction.SMELTING_RECIPES -> {
					player.playSound(
						Sound.sound(
							org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, Sound.Source.PLAYER, 1f, 2f
						)
					)
//					event.inventory.close()
				}

				SlotAction.KNOWN_RECIPE -> {
					player.playSound(
						Sound.sound(
							org.bukkit.Sound.ENTITY_ENDERMAN_TELEPORT, Sound.Source.PLAYER, 1f, 1f
						)
					)
//					event.inventory.close()
//                  RecipeLeather().open(player)
				}

				SlotAction.UNKNOWN_RECIPE -> {
					player.playSound(
						Sound.sound(
							org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, Sound.Source.PLAYER, 1f, 2f
						)
					)
				}

				SlotAction.PREVIOUS_PAGE -> {
					player.playSound(
						Sound.sound(
							org.bukkit.Sound.ENTITY_ARROW_SHOOT, Sound.Source.PLAYER, 1f, 1f
						)
					)
//					event.inventory.close()
				}

				SlotAction.CLOSE -> {
					player.playSound(
						Sound.sound(
							org.bukkit.Sound.BLOCK_ENDER_CHEST_CLOSE, Sound.Source.PLAYER, 1f, 1f
						)
					)
					event.inventory.close()
					MainRecipesMenu().open(player)
				}

				SlotAction.NEXT_PAGE -> {
					player.playSound(
						Sound.sound(
							org.bukkit.Sound.ENTITY_ARROW_SHOOT, Sound.Source.PLAYER, 1f, 1f
						)
					)
//					event.inventory.close()
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
			if (mainSmeltingMenu.containsKey(playerUuid)) {
				player.playSound(
					Sound.sound(
						org.bukkit.Sound.BLOCK_ENDER_CHEST_CLOSE, Sound.Source.PLAYER, 1f, 1f
					)
				)
				mainSmeltingMenu.remove(playerUuid)
			}
		}
	}
}