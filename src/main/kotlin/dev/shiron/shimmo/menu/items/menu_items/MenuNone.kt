package dev.shiron.shimmo.menu.items.menu_items

import dev.shiron.shimmo.menu.MenuItem
import dev.shiron.shimmo.menu.items.MenuItemTag
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class MenuNone : MenuItem() {
    override val item = ItemStack(Material.AIR)
    override val customItemTag = MenuItemTag.NONE

    init {
        setItem("")
    }
}