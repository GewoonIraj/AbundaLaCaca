package dev.gewooniraj.abundalacaca

import net.kyori.adventure.text.TextComponent

enum class Messages {

    PLUGIN_ENABLED,
    PLUGIN_DISABLED,
    OPEN_RECIPES_MENU,
//    CLEAR_INVENTORY,
    UNKNOWN_COMMAND,
    NO_PERMISSION,
    NO_PLAYER,
    TOO_MANY_ARGUMENTS,
    TOO_LESS_ARGUMENTS;

    companion object {
        fun messageType(type: Messages): TextComponent {
            var msg: TextComponent = ChatUtil.format("")
            when (type) {
                PLUGIN_ENABLED -> {
                    msg = ChatUtil.format("&e[AbundaLaCaca] This plugin is now enabled!")
                }

                PLUGIN_DISABLED -> {
                    msg = ChatUtil.format("&e[AbundaLaCaca] This plugin is now disabled!")
                }

                OPEN_RECIPES_MENU -> {
                    msg = ChatUtil.format("&eOpening the &7Recipes Menu&e...")
                }
                /* CLEAR_INVENTORY -> {
                    msg = ChatUtil.format("&aInventory cleared!")
                } */
                UNKNOWN_COMMAND -> {
                    msg = ChatUtil.format("Unknown command. Type \"/help\" for help.")
                }

                NO_PERMISSION -> {
                    msg = ChatUtil.format("&cYou don't have permission to use this command!")
                }

                NO_PLAYER -> {
                    msg = ChatUtil.format("&cYou must be a player to perform this command!")
                }

                TOO_MANY_ARGUMENTS -> {
                    msg = ChatUtil.format("&cToo many arguments!")
                }

                TOO_LESS_ARGUMENTS -> {
                    msg = ChatUtil.format("&cToo less arguments!")
                }
            }
            return msg
        }
    }
}