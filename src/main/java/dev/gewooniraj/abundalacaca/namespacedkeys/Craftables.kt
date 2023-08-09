package dev.gewooniraj.abundalacaca.namespacedkeys

import org.bukkit.NamespacedKey

enum class Craftables {

    GRASS_BLOCK,
    ENCHANTED_GOLDEN_APPLE;

    companion object {
        fun keyType(type: Craftables): NamespacedKey {
            var key: NamespacedKey? = null
            key = when (type) {
                GRASS_BLOCK -> {
                    NamespacedKey("custom_crafting_recipe", "grass_block")
                }

                ENCHANTED_GOLDEN_APPLE -> {
                    NamespacedKey("custom_crafting_recipe", "enchanted_golden_apple")
                }
            }
            return key
        }
    }
}