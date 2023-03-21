package dev.shiron.shimmo

import dev.shiron.shimmo.commands.MenuCommand
import dev.shiron.shimmo.entities.EntityManager
import dev.shiron.shimmo.items.ItemManager
import dev.shiron.shimmo.menu.MenuManager
import org.bukkit.plugin.java.JavaPlugin


@Suppress("unused")
class Shimmo : JavaPlugin() {

    override fun onEnable() {
        getCommand("menu")?.setExecutor(MenuCommand())

        for (menu in MenuManager.menus) {
            server.pluginManager.registerEvents(menu, this)
        }

        for (item in ItemManager.items) {
            server.pluginManager.registerEvents(item, this)
        }
        server.pluginManager.registerEvents(EntityManager(), this)
    }

    override fun onDisable() {
    }

}