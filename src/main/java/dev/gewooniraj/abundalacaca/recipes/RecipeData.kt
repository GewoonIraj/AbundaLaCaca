package dev.gewooniraj.abundalacaca.recipes

import org.bukkit.NamespacedKey

interface RecipeData {
	val namespacedKey: NamespacedKey
	val result: Any
	val customMetaData: CustomMetaData
}

data class CustomShapedRecipe(
	override val type: String = "CustomShapedRecipe",
	override val namespacedKey: NamespacedKey,
	override val result: CraftingResult,
	val shape: List<String>,
	val ingredients: Map<String, String>,
	override val customMetaData: CustomMetaData
) : CustomRecipe(), RecipeData

data class CustomShapelessRecipe(
	override val type: String = "CustomShapelessRecipe",
	override val namespacedKey: NamespacedKey,
	override val result: CraftingResult,
	val ingredients: List<Ingredients>,
	override val customMetaData: CustomMetaData
) : CustomRecipe(), RecipeData

data class CustomFurnaceRecipe(
	override val type: String = "CustomFurnaceRecipe",
	override val namespacedKey: NamespacedKey,
	override val result: SmeltingResult,
	val source: String,
	val experience: Float,
	val cookingTime: Int,
	override val customMetaData: CustomMetaData
) : CustomRecipe(), RecipeData