package dev.shiron.shimmo.commands

import dev.shiron.shimmo.menu.MenuManager
import dev.shiron.shimmo.menu.MenuTag
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MenuCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            MenuManager.getMenu(MenuTag.MAIN_MENU)?.openMenu(sender)
        }

        return true
    }
}