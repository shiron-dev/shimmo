package dev.shiron.shimmo.items

import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

abstract class ItemCommonClass {
    abstract val item: ItemStack
    abstract val customItemTag: CustomItemTags

    protected abstract fun getCustomItemTagString(): String?

    protected abstract fun setCustomItemTagString(value: String)

    protected fun setItem(name: String, lore: List<String>? = null) {
        setCustomItemTagString(customItemTag.customTag)
        val meta = item.itemMeta
        meta?.setDisplayName(name)
        meta?.lore = lore
        meta?.addEnchant(Enchantment.LUCK, 1, false)
        meta?.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        item.itemMeta = meta
    }

    abstract fun isThisItem(item: ItemStack?): Boolean
}