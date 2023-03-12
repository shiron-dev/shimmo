package dev.shiron.shimmo.events

import dev.shiron.shimmo.items.ItemManager
import dev.shiron.shimmo.menu.Menu
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class ClickEvent : Listener {
    @EventHandler
    fun clickEvent(event: InventoryClickEvent) {

        val player = event.whoClicked

        if (event.view.title == Menu.menuTitle) {
            when (event.currentItem?.type) {
                Material.TNT -> {
                    player.closeInventory()
                    player.health = 0.0
                    player.sendMessage("You just killed yourself")
                }

                Material.BREAD -> {
                    player.closeInventory()
                    player.foodLevel = 20
                    player.sendMessage("Yum!")
                }

                Material.STICK -> {
                    player.closeInventory()
                    player.inventory.addItem(ItemManager.menuStick.item)
                    player.sendMessage("Give you")
                }

                else -> {

                }
            }

            event.isCancelled = true
        }
    }
}