package dev.gewooniraj.abundalacaca.recipes

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dev.gewooniraj.abundalacaca.AbundaLaCaca
import dev.gewooniraj.abundalacaca.ChatUtil
import org.bukkit.Material
import org.bukkit.inventory.FurnaceRecipe
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.jar.JarFile

@Suppress("SENSELESS_COMPARISON")
class RecipeManager {
	private val plugin: Plugin = JavaPlugin.getPlugin(AbundaLaCaca::class.java)
	private val gson: Gson = GsonBuilder().registerTypeAdapter(CustomRecipe::class.java, RecipeDeserializer()).create()

	fun registerCustomRecipes(recipesFolder: File) {
		val recipeTypes = listOf("crafting", "smelting")

		recipeTypes.forEach { generateMissingRecipes(it) }
		recipeTypes.forEach { recipeType ->
			val recipeTypeFolder = recipesFolder.resolve(recipeType)
			recipeTypeFolder.mkdirs()

			RecipeUtil.getRecipeKeys(recipeType).forEach { recipeKey ->
				val customFilePath = recipeTypeFolder.resolve("${recipeKey.key}.json")
				val recipeReader = InputStreamReader(customFilePath.inputStream(), StandardCharsets.UTF_8)
				when (val recipeData = gson.fromJson(recipeReader, CustomRecipe::class.java)) {
					is CustomShapedRecipe -> createCustomShapedRecipe(recipeData)
					is CustomShapelessRecipe -> createCustomShapelessRecipe(recipeData)
					is CustomFurnaceRecipe -> createCustomFurnaceRecipe(recipeData)
				}
			}
		}
	}

	private fun generateMissingRecipes(recipeType: String) {
		val internalRecipeFolder = "recipes/$recipeType"
		val externalRecipeFolder = File(plugin.dataFolder, "recipes/$recipeType").apply { mkdirs() }

		getResourceFiles(internalRecipeFolder).forEach { internalFileName ->
			val externalFile = File(externalRecipeFolder, internalFileName)
			if (!externalFile.exists()) {
				getResourceStream("$internalRecipeFolder/$internalFileName")?.use { inputStream ->
					Files.copy(inputStream, externalFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
				} ?: plugin.logger.warning("Could not find internal resource: $internalRecipeFolder/$internalFileName")
			}
		}
	}

	private fun getResourceFiles(path: String): List<String> {
		val files = mutableListOf<String>()
		AbundaLaCaca::class.java.classLoader.getResources(path).toList().forEach { url ->
			val uri = url.toURI()
			if (uri.scheme == "jar") {
				val jarFile = uri.schemeSpecificPart.substring(5, uri.schemeSpecificPart.indexOf('!'))
				val entries = JarFile(jarFile).entries()
				entries.toList().forEach { entry ->
					if (!entry.isDirectory && entry.name.startsWith(path)) {
						files.add(entry.name.substringAfterLast("/"))
					}
				}
			}
		}
		return files
	}

	private fun getResourceStream(path: String) = AbundaLaCaca::class.java.classLoader.getResourceAsStream(path)

	private fun createCustomShapedRecipe(recipeData: CustomShapedRecipe) {
		val resultMaterial = Material.valueOf(recipeData.result.item.uppercase())
		val resultAmount = recipeData.result.amount

		val resultItem = ItemStack(resultMaterial, resultAmount)
		val resultMeta = resultItem.itemMeta

		if (recipeData.customMetaData.displayName != null) {
			resultMeta.displayName(ChatUtil.textFormat(recipeData.customMetaData.displayName))
		}

		if (recipeData.customMetaData.lore.isNotEmpty()) {
			val resultLore = recipeData.customMetaData.lore.map { ChatUtil.textFormat(it) }
			resultMeta.lore(resultLore)
		}
		resultItem.itemMeta = resultMeta

		val recipe = ShapedRecipe(recipeData.namespacedKey, resultItem)
		recipe.shape(*recipeData.shape.toTypedArray())

		for ((symbol, ingredientMaterial) in recipeData.ingredients) {
			val ingredient = Material.valueOf(ingredientMaterial.uppercase())
			recipe.setIngredient(symbol[0], ingredient)
		}
		plugin.server.addRecipe(recipe)
	}

	private fun createCustomShapelessRecipe(recipeData: CustomShapelessRecipe) {
		val resultMaterial = Material.valueOf(recipeData.result.item.uppercase())
		val resultAmount = recipeData.result.amount

		val resultItem = ItemStack(resultMaterial, resultAmount)
		val resultMeta = resultItem.itemMeta

		if (recipeData.customMetaData.displayName != null) {
			resultMeta.displayName(ChatUtil.textFormat(recipeData.customMetaData.displayName))
		}

		if (recipeData.customMetaData.lore.isNotEmpty()) {
			val resultLore = recipeData.customMetaData.lore.map { ChatUtil.textFormat(it) }
			resultMeta.lore(resultLore)
		}

		resultItem.itemMeta = resultMeta

		val recipe = ShapelessRecipe(recipeData.namespacedKey, resultItem)
		for (ingredientData in recipeData.ingredients) {
			val ingredientMaterial = Material.valueOf(ingredientData.item.uppercase())
			val ingredientAmount = ingredientData.amount
			recipe.addIngredient(ingredientAmount, ingredientMaterial)
		}
		plugin.server.addRecipe(recipe)
	}

	private fun createCustomFurnaceRecipe(recipeData: CustomFurnaceRecipe) {
		val resultMaterial = Material.valueOf(recipeData.result.item.uppercase())
		val sourceMaterial = Material.valueOf(recipeData.source.uppercase())

		val resultItem = ItemStack(resultMaterial)
		val resultMeta = resultItem.itemMeta

		if (recipeData.customMetaData.displayName != null) {
			resultMeta.displayName(ChatUtil.textFormat(recipeData.customMetaData.displayName))
		}

		if (recipeData.customMetaData.lore.isNotEmpty() || recipeData.customMetaData.lore != null) {
			val resultLore = recipeData.customMetaData.lore.map { ChatUtil.textFormat(it) }
			resultMeta.lore(resultLore)
		}
		resultItem.itemMeta = resultMeta

		val recipe = FurnaceRecipe(
			recipeData.namespacedKey, resultItem, sourceMaterial, recipeData.experience, recipeData.cookingTime
		)
		plugin.server.addRecipe(recipe)
	}
}