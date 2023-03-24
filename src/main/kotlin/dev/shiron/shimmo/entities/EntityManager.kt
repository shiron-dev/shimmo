package dev.shiron.shimmo.entities

import dev.shiron.shimmo.Shimmo
import dev.shiron.shimmo.util.secToTick
import dev.shiron.shimmo.util.setEntityName
import me.filoghost.holographicdisplays.api.HolographicDisplaysAPI
import me.filoghost.holographicdisplays.api.hologram.Hologram
import org.bukkit.ChatColor
import org.bukkit.NamespacedKey
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import kotlin.math.round

class EntityManager : Listener {
    companion object {
        val customKey by lazy {
            NamespacedKey(JavaPlugin.getPlugin(Shimmo::class.java), "shimmo_custom_entity_key")
        }
        val customNameKey by lazy {
            NamespacedKey(JavaPlugin.getPlugin(Shimmo::class.java), "shimmo_custom_entity_name_key")
        }
    }

    @EventHandler
    fun onCreatureSpawnEvent(event: CreatureSpawnEvent) {
        setEntityName(event.entity)
    }

    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent) {
        if (event.isCancelled) return
        if (event.finalDamage == 0.0) return
        if (event.cause == EntityDamageEvent.DamageCause.VOID) return

        val entity = event.entity

        if (entity is LivingEntity) {
            setEntityName(entity, entity.health - event.finalDamage)
        }

        if (entity is LivingEntity) {
            val plugin = JavaPlugin.getPlugin(Shimmo::class.java)
            val hologramManager = HolographicDisplaysAPI.get(plugin)
            val location = entity.getLocation().add(0.0, 1.5, 0.0)
            val hologram = hologramManager.createHologram(location)
            hologram.lines.appendText("${ChatColor.RED}${round(event.finalDamage * 10) / 10}")

            HologramDeleter(hologram).runTaskLater(plugin, secToTick(1f).toLong())
        }
    }

    @EventHandler
    fun onEntityDeathEvent(event: EntityDeathEvent) {
        setEntityName(event.entity)
    }
}

class HologramDeleter(private val hologram: Hologram) : BukkitRunnable() {
    override fun run() {
        hologram.delete()
    }
}