package dev.shiron.shimmo.items.custom_item

import dev.shiron.shimmo.items.ItemClass
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

class SupperSword : ItemClass() {
    override val item = ItemStack(Material.DIAMOND_SWORD)

    init {
        customItemTag = "supper_axe"
        val meta = itemMeta
        meta?.setDisplayName("${ChatColor.GOLD}Supper sword")
        meta?.lore = listOf("すごい剣")
        meta?.addEnchant(Enchantment.LUCK, 1, false)
        meta?.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        itemMeta = meta
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
                e.health -= event.finalDamage
                e.velocity = e.velocity.setY(1)
                e.damage(0.0)
            }
            event.isCancelled = true
        }
    }

}