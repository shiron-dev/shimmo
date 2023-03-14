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

                Material.DIAMOND_AXE -> {
                    player.closeInventory()
                    player.inventory.addItem(ItemManager.supperAxe.item)
                }

                Material.STICK -> {
                    player.closeInventory()
                    player.inventory.addItem(ItemManager.menuStick.item)
                }

                Material.DIAMOND_SWORD -> {
                    player.closeInventory()
                    player.inventory.addItem(ItemManager.supperSword.item)
                }

                Material.CARROT_ON_A_STICK->{
                    player.closeInventory()
                    player.inventory.addItem(ItemManager.wandOfThunder.item)
                }

                else -> {

                }
            }

            event.isCancelled = true
        }
    }
}