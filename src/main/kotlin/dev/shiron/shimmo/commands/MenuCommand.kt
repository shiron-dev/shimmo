package dev.shiron.shimmo.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class MenuCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            val gui = Bukkit.createInventory(sender, 9, "${ChatColor.AQUA}Menu")

            val suicide = ItemStack(Material.TNT)
            val feed = ItemStack(Material.BREAD)
            val sword = ItemStack(Material.DIAMOND_SWORD)

            val suicideMeta = suicide.itemMeta
            suicideMeta?.setDisplayName("${ChatColor.RED}Suicide")
            val suicideLore = listOf("${ChatColor.GOLD}Kill yourself.")
            suicideMeta?.lore = suicideLore
            suicide.itemMeta = suicideMeta

            val menuItems = listOf(suicide, feed, sword)
            gui.contents = menuItems.toTypedArray()
            sender.openInventory(gui)
        }

        return true
    }
}