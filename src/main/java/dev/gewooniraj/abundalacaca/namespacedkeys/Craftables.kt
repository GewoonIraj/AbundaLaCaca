package dev.gewooniraj.abundalacaca.namespacedkeys

import org.bukkit.NamespacedKey

enum class Craftables(val key: NamespacedKey) {

    ENCHANTED_GOLDEN_APPLE(NamespacedKey("custom_crafting_recipe", "enchanted_golden_apple")),
    GRASS_BLOCK(NamespacedKey("custom_crafting_recipe", "grass_block"));

}