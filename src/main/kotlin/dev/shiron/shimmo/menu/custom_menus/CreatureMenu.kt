package dev.shiron.shimmo.menu.custom_menus

import dev.shiron.shimmo.entities.creatures.CreatureType
import dev.shiron.shimmo.items.CustomMaterial
import dev.shiron.shimmo.items.ItemManager
import dev.shiron.shimmo.items.custom_item.CustomCreatureSpawnEgg
import dev.shiron.shimmo.menu.MenuTag
import dev.shiron.shimmo.menu.ShimmoGUI
import dev.shiron.shimmo.menu.buttons.MenuButton
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class CreatureMenu : ShimmoGUI() {
    override val menuTag = MenuTag.CREATURE_MENU
    override val itemList = listOf(CreatureType.values().map {
        MenuButton(ItemStack(Material.EGG), it.creatureName, "spawn_${it.creatureTag}") { event ->
            val egg = ItemManager.getItem(CustomMaterial.CustomCreatureSpawnEgg)?.item?.clone() ?: return@MenuButton
            CustomCreatureSpawnEgg.setCreature(egg, it)
            event.whoClicked.inventory.addItem(egg)
        }
    })
}