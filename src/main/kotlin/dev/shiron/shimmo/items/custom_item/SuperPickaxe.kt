package dev.shiron.shimmo.items.custom_item

import dev.shiron.shimmo.entities.EntityManager
import dev.shiron.shimmo.items.CustomMaterial
import dev.shiron.shimmo.items.ItemClass
import dev.shiron.shimmo.util.secToTick
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.block.Block
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.hanging.HangingBreakByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import kotlin.math.sqrt


class SuperPickaxe : ItemClass() {
    override val item = ItemStack(Material.DIAMOND_PICKAXE, 1)
    override val customMaterial = CustomMaterial.SupperPickaxe

    init {
        setItem("Super pickaxe", listOf("すごいツルハシ"))
    }

    override fun onClick(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_BLOCK) {
            val location = event.clickedBlock?.location?.add(event.blockFace.direction) ?: return
            val world = event.player.world
            if (event.clickedBlock?.type != Material.STONE) {
                event.player.sendMessage("石に右クリックで発動します")
            } else if (world.getBlockAt(location).type == Material.AIR) {
                event.player.sendMessage("TNT採掘！")
                val entity = world.spawnEntity(location, EntityType.PRIMED_TNT)
                entity.persistentDataContainer.set(EntityManager.customKey, PersistentDataType.STRING, "mining_tnt")
                if (entity is TNTPrimed) {
                    entity.customName = event.player.name
                }
            }
        }
    }

    @EventHandler
    fun onTNTExplode(event: EntityExplodeEvent) {
        if (event.entityType == EntityType.PRIMED_TNT) {
            val entity = event.entity
            if (entity.persistentDataContainer.get(
                    EntityManager.customKey,
                    PersistentDataType.STRING
                ) == "mining_tnt"
            ) {
                val world = entity.world

                world.spawnParticle(Particle.EXPLOSION_LARGE, entity.location, secToTick(1f))
                world.playSound(entity.location, Sound.ENTITY_GENERIC_EXPLODE, 1f, 1f)

                val radius = 3
                val breakBlock = mutableListOf<Block>()

                for (e in world.getNearbyEntities(entity.location, radius * 1.5, radius * 1.5, radius * 1.5)) {
                    if (e.type == EntityType.PLAYER && e is Player) {
                        e.damage(20.0, entity)
                    }
                    if (e.type == EntityType.DROPPED_ITEM) {
                        e.remove()
                    }
                }

                for (x in -radius..radius) {
                    for (y in -radius..radius) {
                        for (z in -radius..radius) {
                            if (sqrt((x * x + y * y + z * z).toDouble()) <= radius) {
                                val location = entity.location.clone().add(x.toDouble(), y.toDouble(), z.toDouble())
                                val block = world.getBlockAt(location)
                                if (block.type == Material.STONE) {
                                    breakBlock.add(block)
                                }
                            }
                        }
                    }
                }
                for (block in breakBlock) {
                    block.breakNaturally()
                }

                event.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onHangingBreakByTNT(event: HangingBreakByEntityEvent) {
        if (event.remover?.type == EntityType.PRIMED_TNT && event.remover?.persistentDataContainer?.get(
                EntityManager.customKey,
                PersistentDataType.STRING
            ) == "mining_tnt"
        ) {
            event.isCancelled = true
        }
    }
}