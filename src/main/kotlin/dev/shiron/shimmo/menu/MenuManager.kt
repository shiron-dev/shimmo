package dev.shiron.shimmo.menu

import org.bukkit.ChatColor

class MenuManager {
    companion object {
        val menus by lazy { listOf(MainMenu()) }

        fun getMenu(menuTag: MenuTag): ShimmoGUI? {
            return menus.find { it.menuTag == menuTag }
        }
    }
}

enum class MenuTag(val menuTitle: String) {
    MAIN_MENU("${ChatColor.AQUA}Shimmo Menu");

    companion object {
        operator fun get(menuTitle: String): MenuTag? =
            MenuTag.values().find { it.menuTitle == menuTitle }
    }
}