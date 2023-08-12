package dev.gewooniraj.abundalacaca.recipes

sealed class CustomRecipe {
	abstract val type: String
}

data class CraftingResult(
	val item: String,
	val amount: Int
)

data class SmeltingResult(
	val item: String
)

data class Ingredients(
	val amount: Int,
	val item: String
)

data class CustomMetaData(
	val displayName: String,
	val lore: List<String>
)