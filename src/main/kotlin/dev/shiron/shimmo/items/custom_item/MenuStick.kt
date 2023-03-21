package dev.shiron.shimmo.items.custom_item

import dev.shiron.shimmo.items.CustomMaterial
import dev.shiron.shimmo.items.ItemClass
import dev.shiron.shimmo.menu.MenuManager
import dev.shiron.shimmo.menu.MenuTag
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class MenuStick : ItemClass() {

    override val item: ItemStack = ItemStack(Material.STICK, 1)
    override val customItemTag = CustomMaterial.MenuStick

    init {
        setItem("${ChatColor.DARK_PURPLE}Menu stick", listOf("この棒を持ってe右クリックでメニューを開きます。"))
    }

    override fun onClick(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_BLOCK || event.action == Action.RIGHT_CLICK_AIR) {
            MenuManager.getMenu(MenuTag.MAIN_MENU)?.openMenu(event.player)
        }
    }

}