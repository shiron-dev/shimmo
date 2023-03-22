package dev.shiron.shimmo.menu.custom_menus

import dev.shiron.shimmo.items.ItemManager
import dev.shiron.shimmo.menu.MenuTag
import dev.shiron.shimmo.menu.ShimmoGUI
import dev.shiron.shimmo.menu.buttons.MenuButton

class ItemMenu : ShimmoGUI() {
    override val menuTag = MenuTag.ITEM_MENU
    override val itemList = listOf(
        ItemManager.items.map { item ->
            MenuButton(
                item.item.clone(),
                item.item.itemMeta?.displayName ?: "",
                "menu_button_${item.customItemTag}",
                item.item.itemMeta?.lore
            ) {
                it.whoClicked.inventory.addItem(item.item)
                it.whoClicked.closeInventory()
            }
        }
    )
}