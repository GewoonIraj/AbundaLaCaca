package dev.gewooniraj.abundalacaca.inventories.recipesmenu.smelting

import dev.gewooniraj.abundalacaca.inventories.recipesmenu.ItemManager
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

class RecipeLeather : Listener {

	companion object {
		val recipeLeather: MutableMap<UUID, Inventory> = mutableMapOf()
	}

	private enum class SlotAction {
		ITEM, PREVIOUS_PAGE, NEXT_PAGE, CLOSE, NONE
	}

	private val actionSlotMappings = mutableMapOf<Int, SlotAction>().apply {
		this[20] = SlotAction.ITEM
		this[24] = SlotAction.ITEM
		this[46] = SlotAction.PREVIOUS_PAGE
		this[49] = SlotAction.CLOSE
		this[52] = SlotAction.NEXT_PAGE
	}

	fun open(player: Player) {
		val inventory: Inventory = Bukkit.createInventory(player, 54, Component.text("Recipe: Leather"))

		val rfItem = ItemManager.createItem(Material.ROTTEN_FLESH, null, null)
		val lItem = ItemManager.createItem(Material.LEATHER, null, null)
		val wvItem = ItemManager.createItem(Material.WEEPING_VINES, "", null)
		val svItem = ItemManager.createItem(Material.SCULK_VEIN, "", null)
		val ppItem = ItemManager.createItem(Material.SPECTRAL_ARROW, "&aPrevious Page", listOf("&e➤ Page 1"))
		val rItem = ItemManager.createItem(Material.BARRIER, "&cReturn", listOf("&7➤ To Go Back"))
		val npItem = ItemManager.createItem(Material.SPECTRAL_ARROW, "&aNext Page", listOf("&e➤ Page 1"))

		/*      xxxxxxxxx
				xABCxxxxx
				xDEFxxJxx
				xGHIxxxxx
				xxxxxxxxx
				xxxxxxxxx

				A = 10
				B = 11
				C = 12
				D = 19
				E = 20
				F = 21
				G = 28
				H = 29
				I = 30

				J = 24      */

		val svSlots = listOf(0..9, 17..18, 26..27, 35..44)
		val svSlotsOther = listOf(13, 22, 31, 48, 50)
		val wvSlots = listOf(14..16, 32..34)
		val wvSlotsOther = listOf(23, 25)

		svSlots.forEach { range ->
			range.forEach { slot ->
				inventory.setItem(slot, svItem)
			}
		}

		svSlotsOther.forEach { slot ->
			inventory.setItem(slot, svItem)
		}

		wvSlots.forEach { range ->
			range.forEach { slot ->
				inventory.setItem(slot, wvItem)
			}
		}

		wvSlotsOther.forEach { slot ->
			inventory.setItem(slot, wvItem)
		}

		inventory.setItem(20, rfItem)
		inventory.setItem(24, lItem)

		inventory.setItem(10, wvItem)
		inventory.setItem(11, wvItem)
		inventory.setItem(12, wvItem)
		inventory.setItem(19, wvItem)
		inventory.setItem(21, wvItem)
		inventory.setItem(28, wvItem)
		inventory.setItem(29, wvItem)
		inventory.setItem(30, wvItem)

		inventory.setItem(46, ppItem)
		inventory.setItem(49, rItem)
		inventory.setItem(52, npItem)

		recipeLeather[player.uniqueId] = inventory
		player.openInventory(inventory)
	}

	@EventHandler
	private fun onClick(event: InventoryClickEvent) {
		val player = event.whoClicked as? Player ?: return
		val playerUuid = player.uniqueId
		val clickedSlot = event.slot

		if (event.inventory == recipeLeather[playerUuid]) {
			event.isCancelled = true
			val action = actionSlotMappings[clickedSlot] ?: SlotAction.NONE

			when (action) {
				SlotAction.ITEM -> {
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
					MainSmeltingMenu().open(player)
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
			if (recipeLeather.containsKey(playerUuid)) {
				player.playSound(
					Sound.sound(
						org.bukkit.Sound.BLOCK_ENDER_CHEST_CLOSE, Sound.Source.PLAYER, 1f, 1f
					)
				)
				recipeLeather.remove(playerUuid)
			}
		}
	}
}