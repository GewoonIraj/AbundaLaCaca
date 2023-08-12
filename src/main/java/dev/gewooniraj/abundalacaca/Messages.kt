package dev.gewooniraj.abundalacaca

import dev.gewooniraj.abundalacaca.recipes.RecipeUtil
import net.kyori.adventure.text.TextComponent

enum class Messages {

    PLUGIN_ENABLED,
    PLUGIN_DISABLED,
    TOTAL_CRAFTING_RECIPES,
    TOTAL_SMELTING_RECIPES,
    NO_PLAYER,
    NO_PERMISSION,
    TOO_MANY_ARGUMENTS,
    TOO_LESS_ARGUMENTS,
    OPEN_RECIPES_MENU,
    UNKNOWN_COMMAND;

    companion object {
        fun messageType(type: Messages): TextComponent {
            var msg: TextComponent = ChatUtil.textFormat("")
            when (type) {
                PLUGIN_ENABLED -> {
                    msg = ChatUtil.prefixFormat("&aThis plugin is now enabled!")
                }

                PLUGIN_DISABLED -> {
                    msg = ChatUtil.prefixFormat("&cThis plugin is now disabled!")
                }

                TOTAL_CRAFTING_RECIPES -> {
                    msg = ChatUtil.prefixFormat(
                        "&3There are &b${
                            RecipeUtil.getRecipeCount("crafting")
                        }&3 Custom Crafting Recipes!"
                    )
                }

                TOTAL_SMELTING_RECIPES -> {
                    msg = ChatUtil.prefixFormat(
                        "&3There are &b${
                            RecipeUtil.getRecipeCount("smelting")
                        }&3 Custom Smelting Recipes!"
                    )
                }

                NO_PLAYER -> {
                    msg = ChatUtil.prefixFormat("&cYou must be a player to perform this command!")
                }

                NO_PERMISSION -> {
                    msg = ChatUtil.prefixFormat("&cYou don't have permission to use this command!")
                }

                TOO_MANY_ARGUMENTS -> {
                    msg = ChatUtil.prefixFormat("&cToo many arguments!")
                }

                TOO_LESS_ARGUMENTS -> {
                    msg = ChatUtil.prefixFormat("&cToo less arguments!")
                }

                OPEN_RECIPES_MENU -> {
                    msg = ChatUtil.prefixFormat("&3Opening the &bRecipes Menu&3...")
                }

                UNKNOWN_COMMAND -> {
                    msg = ChatUtil.textFormat("Unknown command. Type \"/help\" for help.")
                }
            }
            return msg
        }
    }
}