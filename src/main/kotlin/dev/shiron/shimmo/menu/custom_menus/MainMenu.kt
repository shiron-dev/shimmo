package dev.shiron.shimmo.menu.custom_menus

import dev.shiron.shimmo.menu.MenuManager
import dev.shiron.shimmo.menu.MenuTag
import dev.shiron.shimmo.menu.ShimmoGUI
import dev.shiron.shimmo.menu.buttons.MenuButton
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class MainMenu : ShimmoGUI() {
    override val menuTag = MenuTag.MAIN_MENU
    override val itemList = listOf(
        listOf(
            MenuButton(ItemStack(Material.CHEST), "Item Menuへ", "to_item_menu") {
                MenuManager.getMenu(MenuTag.ITEM_MENU)?.openMenu(it.whoClicked)
            },
            MenuButton(ItemStack(Material.CHICKEN_SPAWN_EGG), "Creature Menuへ", "to_creature_menu") {
                MenuManager.getMenu(MenuTag.CREATURE_MENU)?.openMenu(it.whoClicked)
            }
        )
    )
}