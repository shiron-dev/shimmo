package dev.shiron.shimmo.menu

import dev.shiron.shimmo.items.ItemCommonClass
import dev.shiron.shimmo.menu.items.MenuItemManager
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

abstract class MenuItem : ItemCommonClass() {

    fun onClickItemEvent(event: InventoryClickEvent) {
        if (isThisItem(event.currentItem)) {
            onClickItem(event)
            event.isCancelled = true
        }
    }

    protected open fun onClickItem(event: InventoryClickEvent) {}

    override fun getCustomItemTagString(): String? {
        return item.itemMeta?.persistentDataContainer?.get(MenuItemManager.customKey, PersistentDataType.STRING)
    }

    override fun setCustomItemTagString(value: String) {
        val meta = item.itemMeta
        meta?.persistentDataContainer?.set(MenuItemManager.customKey, PersistentDataType.STRING, value)
        item.itemMeta = meta
    }

    override fun isThisItem(item: ItemStack?): Boolean {
        return item?.itemMeta?.persistentDataContainer?.get(
            MenuItemManager.customKey,
            PersistentDataType.STRING
        ) == getCustomItemTagString()
    }
}
