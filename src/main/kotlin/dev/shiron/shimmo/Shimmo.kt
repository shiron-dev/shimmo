package dev.shiron.shimmo

import dev.shiron.shimmo.commands.MenuCommand
import dev.shiron.shimmo.events.ClickEvent
import dev.shiron.shimmo.events.CreatureEvent
import dev.shiron.shimmo.items.ItemManager
import org.bukkit.plugin.java.JavaPlugin


@Suppress("unused")
class Shimmo : JavaPlugin() {

    override fun onEnable() {
        getCommand("menu")?.setExecutor(MenuCommand())

        server.pluginManager.registerEvents(ClickEvent(), this)
        server.pluginManager.registerEvents(CreatureEvent(), this)

        for (item in ItemManager.items) {
            server.pluginManager.registerEvents(item, this)
        }
    }

    override fun onDisable() {
    }

}