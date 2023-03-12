package dev.shiron.shimmo.items

import dev.shiron.shimmo.items.custom_item.MenuStick
import dev.shiron.shimmo.items.custom_item.SupperAxe

class ItemManager {
    companion object {
        val menuStick: ItemClass
        val supperAxe = SupperAxe()

        init {
            menuStick = MenuStick()
        }
    }
}