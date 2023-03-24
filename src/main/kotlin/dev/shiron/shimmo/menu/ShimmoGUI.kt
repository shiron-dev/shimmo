package dev.shiron.shimmo.menu

import dev.shiron.shimmo.menu.buttons.MenuButton
import dev.shiron.shimmo.menu.items.MenuItem
import dev.shiron.shimmo.menu.items.MenuItemManager
import dev.shiron.shimmo.menu.items.MenuItemTag
import org.bukkit.Bukkit
import org.bukkit.entity.HumanEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

abstract class ShimmoGUI : Listener {
    abstract val menuTag: MenuTag
    abstract val itemList: List<List<Any?>>

    fun openMenu(player: HumanEntity) {
        val gui = Bukkit.createInventory(player, 9 * itemList.size, menuTag.menuTitle)
        val flatList = itemList.flatMap { List(9) { it1 -> if (it1 < it.size) it[it1] else MenuItemTag.NONE } }
        gui.contents =
            flatList.mapNotNull {
                it?.let { it1 -> getMenuItemClass(it1)?.item }
            }.toTypedArray()
        player.openInventory(gui)
    }

    private fun getMenuItemClass(item: Any): MenuItem? {
        if (item is MenuItemTag) {
            return MenuItemManager.getItem(item)
        } else if (item is MenuButton) {
            return item
        }
        throw IllegalArgumentException()
    }

    @EventHandler
    fun clickEvent(event: InventoryClickEvent) {
        if (event.view.title == menuTag.menuTitle) {
            for (item in itemList.flatten()) {
                item?.let { getMenuItemClass(it)?.onClickItemEvent(event) }
            }
            event.isCancelled = true
        }
    }
}