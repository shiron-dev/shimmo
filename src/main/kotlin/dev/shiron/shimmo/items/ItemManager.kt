package dev.shiron.shimmo.items

import dev.shiron.shimmo.Shimmo
import dev.shiron.shimmo.items.custom_item.*
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

class ItemManager {
    companion object {
        val items by lazy { listOf(MenuStick(), SuperAxe(), SuperSword(), SuperPickaxe(), WandOfThunder()) }

        val customKey by lazy {
            NamespacedKey(JavaPlugin.getPlugin(Shimmo::class.java), "shimmo_custom_item_key")
        }

        fun getItem(customMaterial: CustomMaterial): ItemClass? {
            return items.find { it.customMaterial == customMaterial }
        }
    }
}

enum class CustomMaterial(override val customTag: String) : CustomItemTags {
    MenuStick("menu_stick"),
    SuperAxe("super_axe"),
    SuperSword("super_sword"),
    SupperPickaxe("super_pickaxe"),
    WandOfThunder("wand_of_thunder");

    companion object {
        operator fun get(customItemTag: String): CustomMaterial? =
            values().find { it.customTag == customItemTag }
    }
}