package dev.shiron.shimmo.menu.items.menu_items

import dev.shiron.shimmo.items.CustomMaterial
import dev.shiron.shimmo.items.ItemManager
import dev.shiron.shimmo.menu.MenuItem
import dev.shiron.shimmo.menu.items.MenuItemTag
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class GiveMenuStick : MenuItem() {
    override val item = ItemStack(Material.STICK)
    override val customItemTag = MenuItemTag.GIVE_MENU_STICK

    init {
        setItem("MenuStickを取得")
    }

    override fun onClickItem(event: InventoryClickEvent) {
        event.whoClicked.closeInventory()
        event.whoClicked.inventory.addItem(ItemManager.getItem(CustomMaterial.MenuStick)?.item)
    }
}