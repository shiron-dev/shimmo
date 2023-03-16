package dev.shiron.shimmo.entities

import dev.shiron.shimmo.Shimmo
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

class EntityManager {
    companion object {
        val customKey by lazy {
            NamespacedKey(JavaPlugin.getPlugin(Shimmo::class.java), "shimmo_custom_entity_key")
        }
    }
}