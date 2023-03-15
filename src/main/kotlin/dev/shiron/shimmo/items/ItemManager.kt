package dev.shiron.shimmo.items

import dev.shiron.shimmo.Shimmo
import dev.shiron.shimmo.items.custom_item.MenuStick
import dev.shiron.shimmo.items.custom_item.SuperAxe
import dev.shiron.shimmo.items.custom_item.SuperSword
import dev.shiron.shimmo.items.custom_item.WandOfThunder
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

class ItemManager {
    companion object {
        val items by lazy { listOf(MenuStick(), SuperAxe(), SuperSword(), WandOfThunder()) }

        val customKey by lazy {
            NamespacedKey(JavaPlugin.getPlugin(Shimmo::class.java), "shimmo_custom_key")
        }

        fun getItem(customMaterial: CustomMaterial): ItemClass? {
            return items.find { it.customMaterial == customMaterial }
        }
    }
}

enum class CustomMaterial(val customItemTag: String) {
    MenuStick("menu_stick"),
    SuperAxe("super_axe"),
    SuperSword("super_sword"),
    WandOfThunder("wand_of_thunder");

    companion object {
        operator fun get(customItemTag: String): CustomMaterial? =
            values().find { it.customItemTag == customItemTag }
    }
}