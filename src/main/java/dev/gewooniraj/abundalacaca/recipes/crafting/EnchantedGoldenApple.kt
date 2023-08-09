package dev.gewooniraj.abundalacaca.recipes.crafting

import dev.gewooniraj.abundalacaca.AbundaLaCaca
import dev.gewooniraj.abundalacaca.ChatUtil
import dev.gewooniraj.abundalacaca.namespacedkeys.Craftables
import net.kyori.adventure.text.TextComponent
import org.bukkit.Material
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

object EnchantedGoldenApple : Listener {

    fun init() {
        enchantedGoldenApple()
    }

    private val plugin: Plugin = JavaPlugin.getPlugin(AbundaLaCaca::class.java)
    private fun enchantedGoldenApple() {
        val enchantedGoldenApple = ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1)
        val meta = enchantedGoldenApple.itemMeta
        val lore = ArrayList<TextComponent>()
        lore.add(ChatUtil.format("&7Crafted Item"))
        meta.lore(lore)
        enchantedGoldenApple.itemMeta = meta

        val recipe = ShapedRecipe(Craftables.keyType(Craftables.ENCHANTED_GOLDEN_APPLE), enchantedGoldenApple)
        recipe.shape("ABA", "ACA", "ADA")
        recipe.setIngredient('A', Material.GOLD_BLOCK)
        recipe.setIngredient('B', Material.TOTEM_OF_UNDYING)
        recipe.setIngredient('C', Material.GOLDEN_APPLE)
        recipe.setIngredient('D', Material.DRAGON_BREATH)
        plugin.server.addRecipe(recipe)
    }
}