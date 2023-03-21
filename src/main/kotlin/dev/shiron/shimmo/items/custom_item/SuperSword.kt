package dev.shiron.shimmo.items.custom_item

import dev.shiron.shimmo.Shimmo
import dev.shiron.shimmo.items.CustomMaterial
import dev.shiron.shimmo.items.ItemClass
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable


class SuperSword : ItemClass() {
    override val item = ItemStack(Material.DIAMOND_SWORD)
    override val customMaterial = CustomMaterial.SuperSword

    init {
        setItem("${ChatColor.GOLD}Super sword", listOf("すごい剣"))
    }

    override fun onClick(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_BLOCK || event.action == Action.RIGHT_CLICK_AIR) {
            val player = event.player
            val range = 2.0
            player.sendMessage("回転斬り")

            PlayerRotation(player) {
                val entities = player.world.getNearbyEntities(player.location, range, range, range)
                    .filterIsInstance<LivingEntity>().filter { it !is Player }
                for (entity in entities) {
                    entity.damage(10.0)
                }
            }.runTaskTimer(
                JavaPlugin.getPlugin(Shimmo::class.java),
                0L,
                1L
            )
        }
    }

    override fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {
        val entity = event.entity
        if (entity is LivingEntity) {
            val location = entity.location
            val range = 1.0
            val limit = 5
            val targetEntities = listOf(entity) + location.world?.getNearbyEntities(location, range, range, range)
                ?.filterIsInstance(LivingEntity::class.java)
                ?.filterIndexed { i, _ -> i < limit } as List<LivingEntity>


            for (e in targetEntities) {
                if (e is Player) continue
                e.velocity = e.velocity.setY(1)
                e.damage(event.finalDamage)
            }
            event.isCancelled = true
        }
    }
}

class PlayerRotation(private val player: Player, private val onFinish: () -> Unit) : BukkitRunnable() {
    private var yaw = 0f
    private val initYaw = player.location.yaw
    private val speed = 30
    override fun run() {
        yaw += speed
        val location = player.location
        if (yaw > 360) {
            location.yaw = initYaw
            onFinish()
            cancel()
        } else {
            location.yaw = initYaw + yaw
        }
        player.teleport(location)
    }
}
