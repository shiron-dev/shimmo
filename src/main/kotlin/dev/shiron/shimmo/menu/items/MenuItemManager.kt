package dev.shiron.shimmo.menu.items

import dev.shiron.shimmo.Shimmo
import dev.shiron.shimmo.items.CustomItemTags
import dev.shiron.shimmo.menu.items.menu_items.MenuNone
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

class MenuItemManager {
    companion object {
        val items by lazy { listOf(MenuNone()) }

        val customKey by lazy {
            NamespacedKey(JavaPlugin.getPlugin(Shimmo::class.java), "shimmo_custom_menu_item_key")
        }

        fun getItem(menuItemTag: MenuItemTag): MenuItem? {
            return items.find { it.customItemTag == menuItemTag }
        }
    }
}

enum class MenuItemTag(override val customTag: String) : CustomItemTags {
    NONE("none");

    companion object {
        operator fun get(customTag: String): MenuItemTag? =
            MenuItemTag.values().find { it.customTag == customTag }
    }
}