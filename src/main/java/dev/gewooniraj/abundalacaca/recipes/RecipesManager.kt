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

class RecipeManager {
    private val plugin: Plugin = JavaPlugin.getPlugin(AbundaLaCaca::class.java)
    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(CustomRecipe::class.java, RecipeDeserializer())
        .create()

    fun registerCustomRecipes(recipesFolder: File) {
        val recipeTypes = listOf("smelting", "crafting")
        for (recipeType in recipeTypes) {
            val recipeTypeFolder = recipesFolder.resolve(recipeType)
            if (!recipeTypeFolder.exists()) {
                recipeTypeFolder.mkdirs()
            }

            val defaultRecipeListResource = getResourceStream("recipes/$recipeType/recipes.txt")
            val defaultRecipeFilenames =
                defaultRecipeListResource.use { it?.bufferedReader()?.readLines() ?: emptyList() }

            for (recipeFileName in defaultRecipeFilenames) {
                val customFilePath = recipeTypeFolder.resolve("$recipeFileName.json")

                val inputStream = getResourceStream("recipes/$recipeType/$recipeFileName.json")
                val content = inputStream?.reader(StandardCharsets.UTF_8)?.readText()
                inputStream?.close()

                if (content != null) {
                    File(customFilePath.toString()).writeText(content, StandardCharsets.UTF_8)
                }

                val recipeReader = InputStreamReader(customFilePath.inputStream(), StandardCharsets.UTF_8)
                val recipeData = gson.fromJson(recipeReader, CustomRecipe::class.java)

                if (recipeData != null) {
                    when (recipeData) {
                        is CustomFurnaceRecipe -> createCustomFurnaceRecipe(recipeData)
                        is CustomShapedRecipe -> createCustomShapedRecipe(recipeData)
                        is CustomShapelessRecipe -> createCustomShapelessRecipe(recipeData)
                    }
                }
            }
        }
    }

    private fun getResourceStream(path: String) = AbundaLaCaca::class.java.classLoader.getResourceAsStream(path)

    private fun createCustomFurnaceRecipe(recipeData: CustomFurnaceRecipe) {
        val resultMaterial = Material.valueOf(recipeData.result.uppercase())
        val sourceMaterial = Material.valueOf(recipeData.source.uppercase())

        val resultItem = ItemStack(resultMaterial)
        val resultMeta = resultItem.itemMeta
        val resultLore = recipeData.metadata.lore.map { ChatUtil.format(it) }

        resultMeta.lore(resultLore)
        resultItem.itemMeta = resultMeta

        val recipe = FurnaceRecipe(
            recipeData.key,
            resultItem,
            sourceMaterial,
            recipeData.experience,
            recipeData.cookingTime
        )
        plugin.server.addRecipe(recipe)
    }

    private fun createCustomShapedRecipe(recipeData: CustomShapedRecipe) {
        val resultMaterial = Material.valueOf(recipeData.result.item.uppercase())
        val resultAmount = recipeData.result.amount

        val resultItem = ItemStack(resultMaterial, resultAmount)
        val resultMeta = resultItem.itemMeta
        val resultLore = recipeData.metadata.lore.map { ChatUtil.format(it) }

        resultMeta.lore(resultLore)
        resultItem.itemMeta = resultMeta

        val recipe = ShapedRecipe(recipeData.key, resultItem)
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
        val resultLore = recipeData.metadata.lore.map { ChatUtil.format(it) }

        resultMeta.lore(resultLore)
        resultItem.itemMeta = resultMeta

        val recipe = ShapelessRecipe(recipeData.key, resultItem)

        for (ingredientData in recipeData.ingredients) {
            val ingredientMaterial = Material.valueOf(ingredientData.item.uppercase())
            val ingredientAmount = ingredientData.amount
            recipe.addIngredient(ingredientAmount, ingredientMaterial)
        }

        plugin.server.addRecipe(recipe)
    }
}
