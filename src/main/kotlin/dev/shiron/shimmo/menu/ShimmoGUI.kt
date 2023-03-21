package dev.shiron.shimmo.menu

import dev.shiron.shimmo.menu.items.MenuItemManager
import dev.shiron.shimmo.menu.items.MenuItemTag
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

abstract class ShimmoGUI : Listener {
    abstract val menuTag: MenuTag
    abstract val itemList: List<List<MenuItemTag>>

    fun openMenu(player: Player) {
        val gui = Bukkit.createInventory(player, 9 * itemList.size, menuTag.menuTitle)
        val flatList = itemList.flatMap { List(9) { it1 -> if (it1 < it.size) it[it1] else MenuItemTag.NONE } }
        gui.contents =
            flatList.mapNotNull {
                MenuItemManager.getItem(it)?.item ?: MenuItemManager.getItem(MenuItemTag.NONE)?.item
            }.toTypedArray()
        player.openInventory(gui)
    }

    @EventHandler
    fun clickEvent(event: InventoryClickEvent) {
        if (event.view.title == menuTag.menuTitle) {
            for (itemMenuTag in itemList.flatten()) {
                MenuItemManager.getItem(itemMenuTag)?.onClickItemEvent(event)
            }
        }
    }
}