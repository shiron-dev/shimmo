package dev.shiron.shimmo.util

import dev.shiron.shimmo.entities.EntityManager
import org.bukkit.ChatColor
import org.bukkit.attribute.Attribute
import org.bukkit.entity.LivingEntity
import org.bukkit.persistence.PersistentDataType
import kotlin.math.max

fun setEntityName(entity: LivingEntity, health: Double? = null) {
    val healthStr = toUnitString(max(0.0, health ?: entity.health))
    val maxHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value?.let { toUnitString(it) }
    val name = entity.persistentDataContainer.get(EntityManager.customNameKey, PersistentDataType.STRING) ?: entity.name
    entity.customName =
        "${ChatColor.WHITE}$name ${ChatColor.GREEN}$healthStr/$maxHealth"
    entity.isCustomNameVisible = true
}