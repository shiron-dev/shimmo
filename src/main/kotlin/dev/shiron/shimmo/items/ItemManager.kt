package dev.shiron.shimmo.items

import dev.shiron.shimmo.items.custom_item.MenuStick

class ItemManager {
    companion object {
        val menuStick: ItemClass

        init {
            menuStick = MenuStick()
        }
    }
}