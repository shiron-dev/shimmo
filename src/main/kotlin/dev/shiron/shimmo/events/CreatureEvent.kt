package dev.shiron.shimmo.events

import dev.shiron.shimmo.util.setEntityName
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent

class CreatureEvent : Listener {
    @EventHandler
    fun onCreatureSpawnEvent(event: CreatureSpawnEvent) {
        setEntityName(event.entity)
    }

    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent) {
        val entity = event.entity
        if (entity is LivingEntity) {
            setEntityName(entity, entity.health - event.finalDamage)
        }
    }

    @EventHandler
    fun onEntityDeathEvent(event: EntityDeathEvent) {
        setEntityName(event.entity)
    }
}