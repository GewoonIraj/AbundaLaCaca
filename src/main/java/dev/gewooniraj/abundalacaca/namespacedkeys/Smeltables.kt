package dev.gewooniraj.abundalacaca.namespacedkeys

import org.bukkit.NamespacedKey

enum class Smeltables {

    LEATHER;

    companion object {
        fun keyType(type: Smeltables): NamespacedKey {
            var key: NamespacedKey? = null
            key = when(type) {
                LEATHER -> {
                    NamespacedKey("custom_smelting_recipe", "leather")
                }
            }
            return key
        }
    }
}