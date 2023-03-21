package dev.shiron.shimmo.items

import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

abstract class ItemCommonClass {
    abstract val item: ItemStack
    abstract val customMaterial: CustomItemTags

    private var customItemTag: String?
        get() {
            return item.itemMeta?.persistentDataContainer?.get(ItemManager.customKey, PersistentDataType.STRING)
        }
        set(value) {
            val meta = item.itemMeta
            value?.let { meta?.persistentDataContainer?.set(ItemManager.customKey, PersistentDataType.STRING, it) }
            item.itemMeta = meta
        }

    fun setItem(name: String, lore: List<String>) {
        customItemTag = customMaterial.customTag
        val meta = item.itemMeta
        meta?.setDisplayName(name)
        meta?.lore = lore
        meta?.addEnchant(Enchantment.LUCK, 1, false)
        meta?.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        item.itemMeta = meta
    }

    protected fun isThisItem(item: ItemStack?): Boolean {
        return item?.itemMeta?.persistentDataContainer?.get(
            ItemManager.customKey,
            PersistentDataType.STRING
        ) == customItemTag
    }
}