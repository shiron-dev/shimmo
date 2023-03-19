package dev.shiron.shimmo.items

import org.bukkit.Material

@Suppress("unused")
object MaterialTypes {
    val COMMON_STONE_BLOCKS: List<Material> = listOf(
        Material.STONE,
        Material.COBBLESTONE,
        Material.GRANITE,
        Material.DIORITE,
        Material.ANDESITE,
        Material.SANDSTONE, Material.RED_SANDSTONE, Material.CALCITE, Material.TUFF
    )
    val COMMON_DEEP_STONE_BLOCKS: List<Material> =
        listOf(
            Material.DEEPSLATE,
            Material.COBBLED_DEEPSLATE,
            Material.BASALT,
            Material.BLACKSTONE,
            Material.SMOOTH_BASALT
        )
}