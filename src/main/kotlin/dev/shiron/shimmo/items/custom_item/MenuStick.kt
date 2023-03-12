package dev.shiron.shimmo.items.custom_item

import dev.shiron.shimmo.items.ItemClass
import dev.shiron.shimmo.menu.Menu
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

class MenuStick : ItemClass() {

    override val item: ItemStack = ItemStack(Material.STICK, 1)

    init {
        val meta = item.itemMeta
        meta?.setDisplayName("Menu stick")
        meta?.lore = listOf("この棒を持って右クリックでメニューを開きます。")
        meta?.addEnchant(Enchantment.LUCK, 1, false)
        meta?.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        item.itemMeta = meta
    }

    override fun onClick(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_BLOCK || event.action == Action.RIGHT_CLICK_AIR) {
            Menu.openMenu(event.player)
        }
    }
}