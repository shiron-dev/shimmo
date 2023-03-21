package dev.shiron.shimmo

import dev.shiron.shimmo.commands.MenuCommand
import dev.shiron.shimmo.entities.EntityManager
import dev.shiron.shimmo.events.ClickEvent
import dev.shiron.shimmo.items.ItemManager
import org.bukkit.plugin.java.JavaPlugin


@Suppress("unused")
class Shimmo : JavaPlugin() {

    override fun onEnable() {
        getCommand("menu")?.setExecutor(MenuCommand())

        server.pluginManager.registerEvents(ClickEvent(), this)

        for (item in ItemManager.items) {
            server.pluginManager.registerEvents(item, this)
        }
        server.pluginManager.registerEvents(EntityManager(), this)
    }

    override fun onDisable() {
    }

}