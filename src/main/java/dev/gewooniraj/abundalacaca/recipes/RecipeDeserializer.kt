package dev.gewooniraj.abundalacaca.recipes

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class RecipeDeserializer : JsonDeserializer<CustomRecipe> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): CustomRecipe {
        val jsonObject = json.asJsonObject
        if (jsonObject.has("type")) {
            return when (val type = jsonObject.get("type").asString) {
                "CustomFurnaceRecipe" -> context.deserialize(json, CustomFurnaceRecipe::class.java)
                "CustomShapedRecipe" -> context.deserialize(json, CustomShapedRecipe::class.java)
                "CustomShapelessRecipe" -> context.deserialize(json, CustomShapelessRecipe::class.java)
                else -> throw IllegalArgumentException("Unknown type: $type")
            }
        }
        throw IllegalArgumentException("Missing 'type' field in JSON")
    }
}
