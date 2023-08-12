package dev.gewooniraj.abundalacaca.inventories.recipesmenu

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dev.gewooniraj.abundalacaca.AbundaLaCaca
import dev.gewooniraj.abundalacaca.ChatUtil
import dev.gewooniraj.abundalacaca.recipes.CustomFurnaceRecipe
import dev.gewooniraj.abundalacaca.recipes.CustomRecipe
import dev.gewooniraj.abundalacaca.recipes.RecipeDeserializer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TranslatableComponent
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

@Suppress("SENSELESS_COMPARISON")
object ItemManager {

	private val plugin: Plugin = JavaPlugin.getPlugin(AbundaLaCaca::class.java)
	private val gson: Gson = GsonBuilder().registerTypeAdapter(CustomRecipe::class.java, RecipeDeserializer()).create()

	fun createItem(material: Material, displayName: String, lore: List<String>): ItemStack {
		val item = ItemStack(material, 1)
		val itemMeta = item.itemMeta
		itemMeta.displayName(ChatUtil.textFormat(displayName))
		val loreComponents = lore.map { ChatUtil.textFormat(it) }
		itemMeta.lore(loreComponents)
		item.itemMeta = itemMeta
		return item
	}

	fun manualJsonLeatherItem(): ItemStack {
		val leatherRecipeFilePath = File(plugin.dataFolder, "recipes/smelting/leather.json")
		val leatherRecipeReader = InputStreamReader(leatherRecipeFilePath.inputStream(), StandardCharsets.UTF_8)
		val leatherRecipeData = gson.fromJson(leatherRecipeReader, CustomFurnaceRecipe::class.java)

		val leatherMaterial = Material.valueOf(leatherRecipeData.result.uppercase())

		val leatherItem = ItemStack(leatherMaterial)
		val leatherMeta = leatherItem.itemMeta

		val translatableComponent: TranslatableComponent.Builder =
			Component.translatable().key(leatherItem.translationKey()).args(Component.text())
				.color(TextColor.color(0x55FFFF)).decoration(TextDecoration.ITALIC, false)

		if (leatherRecipeData.customMetaData.displayName != null) {
			leatherMeta.displayName(ChatUtil.textFormat("&b" + leatherRecipeData.customMetaData.displayName))
		} else {
			leatherMeta.displayName(
				translatableComponent.build()
			)
		}

		if (leatherRecipeData.customMetaData.lore.isNotEmpty() || leatherRecipeData.customMetaData.lore != null) {
			val resultLore = leatherRecipeData.customMetaData.lore.map { ChatUtil.textFormat(it) }
			leatherMeta.lore(resultLore)
		}
		leatherItem.itemMeta = leatherMeta
		return leatherItem
	}
}