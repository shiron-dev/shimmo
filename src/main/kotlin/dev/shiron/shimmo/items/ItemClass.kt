package dev.shiron.shimmo.items

import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

abstract class ItemClass : Listener {
    abstract val item: ItemStack
    abstract val customMaterial: CustomMaterial

    private var customItemTag: String?
        get() {
            return item.itemMeta?.persistentDataContainer?.get(ItemManager.customKey, PersistentDataType.STRING)
        }
        set(value) {
            val meta = item.itemMeta
            value?.let { meta?.persistentDataContainer?.set(ItemManager.customKey, PersistentDataType.STRING, it) }
            item.itemMeta = meta
        }

    fun setItem(name: String, lore: List<String>) {
        customItemTag = customMaterial.customItemTag
        val meta = item.itemMeta
        meta?.setDisplayName(name)
        meta?.lore = lore
        meta?.addEnchant(Enchantment.LUCK, 1, false)
        meta?.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        item.itemMeta = meta
    }

    private fun isThisItem(item: ItemStack?): Boolean {
        return item?.itemMeta?.persistentDataContainer?.get(
            ItemManager.customKey,
            PersistentDataType.STRING
        ) == customItemTag
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