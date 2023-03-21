package dev.shiron.shimmo.items.custom_item

import dev.shiron.shimmo.items.CustomMaterial
import dev.shiron.shimmo.items.ItemClass
import org.bukkit.ChatColor
import org.bukkit.FluidCollisionMode
import org.bukkit.Material
import org.bukkit.entity.LivingEntity
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack


class WandOfThunder : ItemClass() {
    override val item = ItemStack(Material.CARROT_ON_A_STICK)
    override val customItemTag = CustomMaterial.WandOfThunder

    init {
        setItem("${ChatColor.GOLD}Wand of thunder", listOf("雷の杖"))
    }

    override fun onClick(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_AIR || event.action == Action.RIGHT_CLICK_BLOCK || event.action == Action.LEFT_CLICK_AIR || event.action == Action.LEFT_CLICK_BLOCK) {
            val player = event.player
            val maxDistance = 20.0
            val range = 2.0
            val result =
                player.world.rayTrace(
                    player.location.add(player.location.direction.setY(0).multiply(2)).add(0.0, 1.5, 0.0),
                    player.location.direction,
                    maxDistance,
                    FluidCollisionMode.NEVER,
                    true,
                    0.5,
                    null
                ) ?: return

            player.sendMessage("雷を召喚！")
            val rayLocation = result.hitPosition.toLocation(player.world)

            // ブロックが燃えないようにエフェクトだけ
            player.world.strikeLightningEffect(rayLocation)

            // 雷によるダメージを実装
            val entities =
                player.world.getNearbyEntities(rayLocation, range, range, range).filterIsInstance<LivingEntity>()
            for (entity in entities) {
                entity.fireTicks = 20 * 5
                entity.damage(4.0)
            }

            if (event.action == Action.LEFT_CLICK_AIR || event.action == Action.LEFT_CLICK_BLOCK) {
                player.world.createExplosion(rayLocation, 2.0f, false, false)
                event.isCancelled = true
            }
        }
    }
}