package dev.gewooniraj.abundalacaca.recipes

import org.bukkit.NamespacedKey

sealed class CustomRecipe {
    abstract val type: String
}

data class CustomShapedRecipe(
    override val type: String = "CustomShapedRecipe",
    val namespacedKey: NamespacedKey,
    val result: Result,
    val shape: List<String>,
    val ingredients: Map<String, String>,
    val customMetaData: CustomMetaData
) : CustomRecipe()

data class CustomShapelessRecipe(
    override val type: String = "CustomShapelessRecipe",
    val namespacedKey: NamespacedKey,
    val result: Result,
    val ingredients: List<Ingredients>,
    val customMetaData: CustomMetaData
) : CustomRecipe()

data class CustomFurnaceRecipe(
    override val type: String = "CustomFurnaceRecipe",
    val namespacedKey: NamespacedKey,
    val result: String,
    val source: String,
    val experience: Float,
    val cookingTime: Int,
    val customMetaData: CustomMetaData
) : CustomRecipe()

data class Result(
    val item: String,
    val amount: Int
)

data class Ingredients(
    val amount: Int,
    val item: String
)

data class CustomMetaData(
    val displayName: String,
    val lore: List<String>
)