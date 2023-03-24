package dev.shiron.shimmo.entities.creatures

import org.bukkit.Location
import org.bukkit.attribute.Attribute
import org.bukkit.entity.EntityType

class CorpZombie(loc: Location) : CustomCreature(CreatureType.CORP_ZOMBIE) {
    init {
        setCreature(this, loc, EntityType.ZOMBIE) {
            it.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue = 40.0
        }
    }

}