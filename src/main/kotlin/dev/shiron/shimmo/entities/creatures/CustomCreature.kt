package dev.shiron.shimmo.entities.creatures

import dev.shiron.shimmo.Shimmo
import dev.shiron.shimmo.entities.EntityManager
import dev.shiron.shimmo.util.setEntityName
import org.bukkit.Location
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Creature
import org.bukkit.entity.EntityType
import org.bukkit.event.Listener
import org.bukkit.persistence.PersistentDataType

abstract class CustomCreature(val creatureType: CreatureType) : Listener {
    lateinit var creature: Creature

    fun setCreature(
        listener: Listener,
        loc: Location,
        entityType: EntityType,
        setCreature: ((it: Creature) -> Unit)? = null
    ) {
        val world = loc.world ?: throw NullPointerException()
        val entity = world.spawnEntity(loc, entityType)
        if (entity is Creature) {
            creature = entity
        } else {
            throw IllegalArgumentException()
        }
        entity.persistentDataContainer.set(
            EntityManager.customNameKey,
            PersistentDataType.STRING,
            creatureType.creatureName
        )
        entity.persistentDataContainer.set(
            EntityManager.customKey,
            PersistentDataType.STRING,
            "creature_${creatureType.creatureTag}"
        )

        setCreature?.let { it(creature) }
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue?.let { entity.health = it }

        setEntityName(entity)


        Shimmo.addListener(listener)
    }
}