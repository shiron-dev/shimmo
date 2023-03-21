package dev.shiron.shimmo.items

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent

abstract class ItemClass : ItemCommonClass(), Listener {

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