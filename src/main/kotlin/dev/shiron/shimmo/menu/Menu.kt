package dev.shiron.shimmo.menu

import dev.shiron.shimmo.items.ItemManager
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class Menu {
    companion object {

        val menuTitle = "${ChatColor.AQUA}Simmo menu"
        fun openMenu(player: Player) {
            val gui = Bukkit.createInventory(player, 9, menuTitle)

            val menuStick = ItemManager.menuStick.item
            val axe = ItemManager.supperAxe.item
            val suicide = ItemStack(Material.TNT)

            val suicideMeta = suicide.itemMeta
            suicideMeta?.setDisplayName("${ChatColor.RED}Suicide")
            val suicideLore = listOf("${ChatColor.GOLD}Kill yourself.")
            suicideMeta?.lore = suicideLore
            suicide.itemMeta = suicideMeta

            val menuItems = listOf(menuStick, axe, suicide, ItemManager.supperSword.item)
            gui.contents = menuItems.toTypedArray()

            player.openInventory(gui)
        }
    }
}