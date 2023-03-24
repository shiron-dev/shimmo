package dev.shiron.shimmo.items.custom_item

import dev.shiron.shimmo.Shimmo
import dev.shiron.shimmo.entities.creatures.CreatureType
import dev.shiron.shimmo.entities.creatures.spawnCreature
import dev.shiron.shimmo.items.CustomItemTags
import dev.shiron.shimmo.items.CustomMaterial
import dev.shiron.shimmo.items.ItemClass
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin

class CustomCreatureSpawnEgg() : ItemClass() {
    override val item: ItemStack = ItemStack(Material.CHICKEN_SPAWN_EGG)
    override val customItemTag: CustomItemTags = CustomMaterial.CustomCreatureSpawnEgg

    init {
        setItem(
            "NONE",
            listOf("ブロックに右クリックでモンスターを召喚する")
        )
    }

    override fun onClick(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_BLOCK) {
            event.clickedBlock?.location?.let {
                val creatureType =
                    event.item?.itemMeta?.persistentDataContainer?.get(
                        creatureTypeKey,
                        PersistentDataType.STRING
                    )?.let { it1 ->
                        CreatureType[it1]
                    } ?: return
                spawnCreature(it.add(0.0, 3.0, 0.0), creatureType)
                event.isCancelled = true
            }
        }
    }

    companion object {
        private val creatureTypeKey by lazy {
            NamespacedKey(
                JavaPlugin.getPlugin(Shimmo::class.java),
                "shimmo_item_${CustomMaterial.CustomCreatureSpawnEgg}_creature_type_key"
            )
        }

        fun setCreature(item: ItemStack, creature: CreatureType) {
            val meta = item.itemMeta
            meta?.persistentDataContainer?.set(creatureTypeKey, PersistentDataType.STRING, creature.creatureTag)
            meta?.setDisplayName("${ChatColor.GOLD}${creature.creatureName}${ChatColor.RESET}を召喚する")
            meta?.lore = listOf("ブロックに右クリックで${ChatColor.GOLD}${creature.creatureName}${ChatColor.RESET}を召喚する")
            item.itemMeta = meta
        }
    }
}