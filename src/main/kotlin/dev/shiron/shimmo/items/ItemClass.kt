package dev.shiron.shimmo.items

import dev.shiron.shimmo.Shimmo
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin

abstract class ItemClass : Listener {
    abstract val item: ItemStack
    var customItemTag: String?
        get() {
            return item.itemMeta?.persistentDataContainer?.get(customKey, PersistentDataType.STRING)
        }
        set(value) {
            val meta = item.itemMeta
            value?.let { meta?.persistentDataContainer?.set(customKey, PersistentDataType.STRING, it) }
            item.itemMeta = meta
        }
    private val customKey = NamespacedKey(JavaPlugin.getPlugin(Shimmo::class.java), "shimmo_custom_key")

    var itemMeta: ItemMeta?
        get() = item.itemMeta
        set(value) {
            item.itemMeta = value
        }

    private fun isThisItem(item: ItemStack?): Boolean {
        return item?.itemMeta?.persistentDataContainer?.get(customKey, PersistentDataType.STRING) == customItemTag
    }

    open fun onClick(event: PlayerInteractEvent) {}
    open fun onBreak(event: BlockBreakEvent) {}

    open fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {}

    @EventHandler
    fun onClickEvent(event: PlayerInteractEvent) {
        if (isThisItem(event.item)) {
            onClick(event)
        }
    }

    @EventHandler
    fun onBreakEvent(event: BlockBreakEvent) {
        if (isThisItem(event.player.equipment?.itemInMainHand)) {
            onBreak(event)
        }
    }

    @EventHandler
    fun onEntityDamageByEntityEvent(event: EntityDamageByEntityEvent) {
        if (event.damager is Player && isThisItem((event.damager as Player).equipment?.itemInMainHand)) {
            onEntityDamageByEntity(event)
        }
    }

}