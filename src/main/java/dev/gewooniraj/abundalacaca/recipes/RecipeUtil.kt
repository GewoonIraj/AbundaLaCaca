package dev.gewooniraj.abundalacaca.recipes

import com.google.gson.GsonBuilder
import dev.gewooniraj.abundalacaca.AbundaLaCaca
import org.bukkit.NamespacedKey
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

object RecipeUtil {

    private val plugin: Plugin = JavaPlugin.getPlugin(AbundaLaCaca::class.java)

    fun getRecipeCount(recipeType: String): Int {
        val recipeTypeFolder = File(plugin.dataFolder, "recipes/$recipeType")
        return recipeTypeFolder.listFiles()?.count { it.isFile && it.extension == "json" } ?: 0
    }

    fun getRecipeKeys(recipeType: String): List<NamespacedKey> {
        val gson = GsonBuilder().registerTypeAdapter(CustomRecipe::class.java, RecipeDeserializer()).create()
        val recipeTypeFolder = File(plugin.dataFolder, "recipes/$recipeType")
        if (!recipeTypeFolder.exists()) {
            return emptyList()
        }

        val recipeKeys = mutableListOf<NamespacedKey>()
        val recipeFiles = recipeTypeFolder.listFiles { _, name -> name.endsWith(".json") }
        recipeFiles?.forEach { recipeFile ->
            val recipeReader = InputStreamReader(recipeFile.inputStream(), StandardCharsets.UTF_8)
            when (val recipeData = gson.fromJson(recipeReader, CustomRecipe::class.java)) {
                is CustomShapedRecipe -> recipeKeys.add(recipeData.namespacedKey)
                is CustomShapelessRecipe -> recipeKeys.add(recipeData.namespacedKey)
                is CustomFurnaceRecipe -> recipeKeys.add(recipeData.namespacedKey)
            }
        }

        return recipeKeys
    }
}