package dev.gewooniraj.abundalacaca.recipes

import org.bukkit.NamespacedKey

sealed class CustomRecipe {
    abstract val type: String
}

data class CustomFurnaceRecipe(
    override val type: String = "CustomFurnaceRecipe",
    val key: NamespacedKey,
    val result: String,
    val source: String,
    val experience: Float,
    val cookingTime: Int,
    val metadata: Metadata
) : CustomRecipe()

data class CustomShapedRecipe(
    override val type: String = "CustomShapedRecipe",
    val key: NamespacedKey,
    val result: ResultData,
    val shape: List<String>,
    val ingredients: Map<String, String>,
    val metadata: Metadata
) : CustomRecipe()

data class CustomShapelessRecipe(
    override val type: String = "CustomShapelessRecipe",
    val key: NamespacedKey,
    val result: ResultData,
    val ingredients: List<Ingredient>,
    val metadata: Metadata
) : CustomRecipe()

data class ResultData(
    val item: String,
    val amount: Int
)

data class Ingredient(
    val amount: Int,
    val item: String
)

data class Metadata(
    val lore: List<String>
)