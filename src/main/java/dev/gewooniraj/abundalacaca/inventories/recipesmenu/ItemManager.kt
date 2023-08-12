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
import org.bukkit.attribute.Attribute
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

	fun jsonItem(recipeType: String, recipeData: CustomFurnaceRecipe): ItemStack {
		val recipeFilePath = File(plugin.dataFolder, "recipes/$recipeType/${recipeData.namespacedKey.key}.json")
		val recipeReader = InputStreamReader(recipeFilePath.inputStream(), StandardCharsets.UTF_8)
		val recipe = gson.fromJson(recipeReader, CustomFurnaceRecipe::class.java)

		val resultMaterial = Material.valueOf(recipe.result.uppercase())

		val resultItem = ItemStack(resultMaterial)
		val resultMeta = resultItem.itemMeta

		val translatableComponent: TranslatableComponent.Builder =
			Component.translatable().key(resultItem.translationKey()).args(Component.text())
				.color(TextColor.color(0x55FFFF)).decoration(TextDecoration.ITALIC, false)

		if (recipeData.customMetaData.displayName != null) {
			resultMeta.displayName(ChatUtil.textFormat("&b" + recipeData.customMetaData.displayName))
		} else {
			resultMeta.displayName(translatableComponent.build())
		}

		if (recipeData.customMetaData.lore.isNotEmpty() || recipeData.customMetaData.lore != null) {
			val resultLore = recipeData.customMetaData.lore.map { ChatUtil.textFormat(it) }
			resultMeta.lore(resultLore)
		}
		resultItem.itemMeta = resultMeta
		return resultItem
	}

	fun getAvailableRecipes(recipeType: String): List<CustomFurnaceRecipe> {
		val recipesFolder = File(plugin.dataFolder, "recipes/$recipeType")
		val availableRecipes = mutableListOf<CustomFurnaceRecipe>()

		val files = recipesFolder.listFiles { _, name -> name.endsWith(".json") }
		if (files != null) {
			for (file in files) {
				val recipeReader = InputStreamReader(file.inputStream(), StandardCharsets.UTF_8)
				val recipeData = gson.fromJson(recipeReader, CustomFurnaceRecipe::class.java)
				availableRecipes.add(recipeData)
			}
		}

		return availableRecipes
	}
}