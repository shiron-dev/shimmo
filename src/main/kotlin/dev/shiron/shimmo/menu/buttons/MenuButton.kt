package dev.shiron.shimmo.menu.buttons

import dev.shiron.shimmo.items.CustomItemTags
import dev.shiron.shimmo.menu.items.MenuItem
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class MenuButton(
    override val item: ItemStack,
    text: String,
    buttonId: String,
    description: List<String>? = null,
    val onClick: (event: InventoryClickEvent) -> Unit
) : MenuItem() {
    override val customItemTag = MenuButtonTag(buttonId)

    init {
        setItem(text, description)
    }

    override fun onClickItem(event: InventoryClickEvent) {
        onClick(event)
    }
}

class MenuButtonTag(override val customTag: String) : CustomItemTags