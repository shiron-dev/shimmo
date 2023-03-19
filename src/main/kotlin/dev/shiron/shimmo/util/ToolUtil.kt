package dev.shiron.shimmo.util

import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack


fun decreaseDurability(item: ItemStack, cost: Int) {
    var durabilityCost = cost
    if (item.enchantments.containsKey(Enchantment.DURABILITY)) {
        val level = item.getEnchantmentLevel(Enchantment.DURABILITY)
        for (i in 0 until level) {
            if (Math.random() < 1.0 / (level + 1)) {
                durabilityCost--
            }
        }
        if (durabilityCost < 0) {
            durabilityCost = 0
        }
    }

    val meta = item.itemMeta
    if (meta is org.bukkit.inventory.meta.Damageable) {
        meta.damage = durabilityCost
        item.itemMeta = meta
    }
}