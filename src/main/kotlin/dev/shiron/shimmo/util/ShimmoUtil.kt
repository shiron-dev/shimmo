package dev.shiron.shimmo.util

import org.bukkit.attribute.Attribute
import org.bukkit.entity.LivingEntity
import kotlin.math.max

fun setEntityName(entity: LivingEntity, health: Double? = null) {
    val healthStr = toUnitString(max(0.0, health ?: entity.health))
    val maxHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value?.let { toUnitString(it) }
    entity.customName =
        "$healthStr/$maxHealth"
    entity.isCustomNameVisible = true
}