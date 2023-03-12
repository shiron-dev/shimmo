package dev.shiron.shimmo.events

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class ClickEvent : Listener {
    @EventHandler
    fun clickEvent(event: InventoryClickEvent) {

        val player = event.whoClicked

        if (event.view.title == "${ChatColor.AQUA}Menu") {
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

                Material.DIAMOND_SWORD -> {
                    player.closeInventory()
                    player.inventory.addItem(ItemStack(Material.DIAMOND_SWORD))
                    player.sendMessage("Give you")
                }

                else -> {

                }
            }

            event.isCancelled = true
        }
    }
}