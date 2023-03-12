package dev.shiron.shimmo

import dev.shiron.shimmo.commands.MenuCommand
import dev.shiron.shimmo.events.ClickEvent
import dev.shiron.shimmo.items.ItemManager
import org.bukkit.plugin.java.JavaPlugin


@Suppress("unused")
class Shimmo : JavaPlugin() {

    override fun onEnable() {
        getCommand("menu")?.setExecutor(MenuCommand())

        server.pluginManager.registerEvents(ClickEvent(), this)
        server.pluginManager.registerEvents(ItemManager.menuStick, this)
        server.pluginManager.registerEvents(ItemManager.supperAxe, this)
    }

    override fun onDisable() {
    }

}