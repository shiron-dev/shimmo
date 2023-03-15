package dev.shiron.shimmo.events

import dev.shiron.shimmo.items.CustomMaterial
import dev.shiron.shimmo.items.ItemManager
import dev.shiron.shimmo.menu.Menu
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.persistence.PersistentDataType

class ClickEvent : Listener {
    @EventHandler
    fun clickEvent(event: InventoryClickEvent) {

        val player = event.whoClicked

        if (event.view.title == Menu.menuTitle) {
            val customMaterial = event.currentItem?.itemMeta?.persistentDataContainer?.get(
                ItemManager.customKey,
                PersistentDataType.STRING
            )
                ?.let { CustomMaterial[it] }
            player.inventory.addItem(customMaterial?.let { ItemManager.getItem(it)?.item })

            event.isCancelled = true
        }
    }
}