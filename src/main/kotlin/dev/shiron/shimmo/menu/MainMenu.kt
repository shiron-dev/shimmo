package dev.shiron.shimmo.menu

import dev.shiron.shimmo.menu.items.MenuItemTag

class MainMenu : ShimmoGUI() {
    override val menuTag = MenuTag.MAIN_MENU
    override val itemList = listOf(
        listOf(
            MenuItemTag.GIVE_MENU_STICK
        )
    )
}