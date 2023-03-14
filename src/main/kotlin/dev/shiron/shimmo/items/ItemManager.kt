package dev.shiron.shimmo.items

import dev.shiron.shimmo.items.custom_item.MenuStick
import dev.shiron.shimmo.items.custom_item.SupperAxe
import dev.shiron.shimmo.items.custom_item.SupperSword
import dev.shiron.shimmo.items.custom_item.WandOfThunder

class ItemManager {
    companion object {
        val menuStick: ItemClass
        val supperAxe = SupperAxe()
        val supperSword = SupperSword()
        val wandOfThunder = WandOfThunder()

        init {
            menuStick = MenuStick()
        }
    }
}