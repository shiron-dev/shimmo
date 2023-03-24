package dev.shiron.shimmo.entities.creatures

enum class CreatureType(val creatureTag: String, val creatureName: String) {
    CORP_ZOMBIE("corp_zombie", "隊員ゾンビ");

    companion object {
        operator fun get(creatureTag: String): CreatureType? =
            CreatureType.values().find { it.creatureTag == creatureTag }
    }
}