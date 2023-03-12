package dev.shiron.shimmo.items

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

abstract class ItemClass : Listener {
    abstract val item: ItemStack
    var itemMeta: ItemMeta?
        get() = item.itemMeta
        set(value) {
            item.itemMeta = value
        }

    @EventHandler
    fun onClickEvent(event: PlayerInteractEvent) {
        if (event.item?.itemMeta == itemMeta) {
            onClick(event)
        }
    }

    open fun onClick(event: PlayerInteractEvent) {
    }
}