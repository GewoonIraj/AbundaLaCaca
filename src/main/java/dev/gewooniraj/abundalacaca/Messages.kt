package dev.gewooniraj.abundalacaca

import dev.gewooniraj.abundalacaca.recipes.RecipeUtil
import net.kyori.adventure.text.TextComponent

enum class Messages {

    PLUGIN_ENABLED,
    PLUGIN_DISABLED,
    TOTAL_CRAFTABLES,
    TOTAL_SMELTABLES,
    NO_PLAYER,
    NO_PERMISSION,
    TOO_MANY_ARGUMENTS,
    TOO_LESS_ARGUMENTS,
    UNKNOWN_COMMAND,
    OPEN_RECIPES_MENU;

    companion object {
        fun messageType(type: Messages): TextComponent {
            var msg: TextComponent = ChatUtil.textFormat("")
            when (type) {
                PLUGIN_ENABLED -> {
                    msg = ChatUtil.consoleFormat("&aThis plugin is now enabled!")
                }

                PLUGIN_DISABLED -> {
                    msg = ChatUtil.consoleFormat("&cThis plugin is now disabled!")
                }

                TOTAL_CRAFTABLES -> {
                    msg = ChatUtil.consoleFormat(
                        "&3There are &b${
                            RecipeUtil.getRecipeCount("crafting")
                        }&3 Custom Craftable Recipes!"
                    )
                }

                TOTAL_SMELTABLES -> {
                    msg = ChatUtil.consoleFormat(
                        "&3There are &b${
                            RecipeUtil.getRecipeCount("smelting")
                        }&3 Custom Smeltable Recipe!"
                    )
                }

                NO_PLAYER -> {
                    msg = ChatUtil.consoleFormat("&cYou must be a player to perform this command!")
                }

                NO_PERMISSION -> {
                    msg = ChatUtil.consoleFormat("&cYou don't have permission to use this command!")
                }

                TOO_MANY_ARGUMENTS -> {
                    msg = ChatUtil.consoleFormat("&cToo many arguments!")
                }

                TOO_LESS_ARGUMENTS -> {
                    msg = ChatUtil.consoleFormat("&cToo less arguments!")
                }

                UNKNOWN_COMMAND -> {
                    msg = ChatUtil.textFormat("Unknown command. Type \"/help\" for help.")
                }

                OPEN_RECIPES_MENU -> {
                    msg = ChatUtil.textFormat("&eOpening the &7Recipes Menu&e...")
                }
            }
            return msg
        }
    }
}