package dev.shiron.shimmo.menu

import dev.shiron.shimmo.items.CustomMaterial
import dev.shiron.shimmo.items.ItemManager
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class Menu {
    companion object {

        val menuTitle = "${ChatColor.AQUA}Simmo menu"
        fun openMenu(player: Player) {
            val gui = Bukkit.createInventory(player, 9, menuTitle)

            val menuItems =
                listOf(
                    ItemManager.getItem(CustomMaterial.MenuStick)?.item,
                    ItemManager.getItem(CustomMaterial.SuperAxe)?.item,
                    ItemManager.getItem(CustomMaterial.SuperSword)?.item,
                    ItemManager.getItem(CustomMaterial.SupperPickaxe)?.item,
                    ItemManager.getItem(CustomMaterial.WandOfThunder)?.item
                )
            gui.contents = menuItems.toTypedArray()

            player.openInventory(gui)
        }
    }
}