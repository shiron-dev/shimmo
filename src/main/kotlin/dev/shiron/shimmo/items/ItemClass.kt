package dev.shiron.shimmo.items

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

abstract class ItemClass : ItemCommonClass(), Listener {

    override fun getCustomItemTagString(): String? {
        return item.itemMeta?.persistentDataContainer?.get(ItemManager.customKey, PersistentDataType.STRING)
    }

    override fun setCustomItemTagString(value: String) {
        val meta = item.itemMeta
        meta?.persistentDataContainer?.set(ItemManager.customKey, PersistentDataType.STRING, value)
        item.itemMeta = meta
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

    override fun isThisItem(item: ItemStack?): Boolean {
        return item?.itemMeta?.persistentDataContainer?.get(
            ItemManager.customKey,
            PersistentDataType.STRING
        ) == getCustomItemTagString()
    }
}