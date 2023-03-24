package dev.shiron.shimmo.entities.creatures

import org.bukkit.Location

fun spawnCreature(loc: Location, creatureType: CreatureType): CustomCreature {
    return when (creatureType) {
        CreatureType.CORP_ZOMBIE -> CorpZombie(loc)
    }
}