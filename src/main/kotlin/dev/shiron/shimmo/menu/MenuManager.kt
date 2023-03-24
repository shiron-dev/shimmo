package dev.shiron.shimmo.menu

import dev.shiron.shimmo.menu.custom_menus.CreatureMenu
import dev.shiron.shimmo.menu.custom_menus.ItemMenu
import dev.shiron.shimmo.menu.custom_menus.MainMenu
import org.bukkit.ChatColor

class MenuManager {
    companion object {
        val menus by lazy { listOf(MainMenu(), ItemMenu(), CreatureMenu()) }

        fun getMenu(menuTag: MenuTag): ShimmoGUI? {
            return menus.find { it.menuTag == menuTag }
        }
    }
}

enum class MenuTag(val menuTitle: String) {
    MAIN_MENU("${ChatColor.AQUA}Shimmo Menu"),
    ITEM_MENU("${ChatColor.AQUA}Shimmo Item Menu"),
    CREATURE_MENU("${ChatColor.RED}Shimmo Creature Menu");

    companion object {
        operator fun get(menuTitle: String): MenuTag? =
            MenuTag.values().find { it.menuTitle == menuTitle }
    }
}