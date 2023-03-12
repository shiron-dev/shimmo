package dev.shiron.shimmo

import dev.shiron.shimmo.commands.MenuCommand
import dev.shiron.shimmo.events.ClickEvent
import org.bukkit.plugin.java.JavaPlugin


@Suppress("unused")
class Shimmo : JavaPlugin() {
    override fun onEnable() {
        getCommand("menu")?.setExecutor(MenuCommand())

        server.pluginManager.registerEvents(ClickEvent(), this)
    }

    override fun onDisable() {
    }

}