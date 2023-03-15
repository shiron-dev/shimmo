package dev.shiron.shimmo.items.custom_item

import dev.shiron.shimmo.items.CustomMaterial
import dev.shiron.shimmo.items.ItemClass
import org.bukkit.*
import org.bukkit.block.data.type.Leaves
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack
import kotlin.math.abs

class SupperAxe : ItemClass() {
    override val item: ItemStack = ItemStack(Material.DIAMOND_AXE, 1)
    override val customMaterial = CustomMaterial.SuperAxe

    init {
        setItem("${ChatColor.GOLD}Supper axe", listOf("木を一括破壊できます。"))
    }

    override fun onBreak(event: BlockBreakEvent) {
        val block = event.block
        if (Tag.LOGS.isTagged(block.type)) {
            val world = block.world
            val location = block.location
            listUpTree(location, world, runOnlyNatualTree = false)?.let {
                for (treeBlock in it) {
                    treeBlock.block.breakNaturally()
                }
            }
        }
    }

    private fun listUpTree(
        originLocation: Location,
        world: World,
        limit: Int = 10,
        runOnlyNatualTree: Boolean = true
    ): List<Location>? {
        var isNatualTree = !runOnlyNatualTree

        val originBlock = world.getBlockAt(originLocation)
        val ret = mutableListOf(originLocation)

        // 同じy座標の全ブロック
        val layerLocations = mutableListOf(originLocation)

        for (y in 0..limit) {

            // 同じy座標の探索で見つかった新規ブロック
            val layerNewLocations = layerLocations.toMutableList()
            layerLocations.clear()

            var newLayerFlag = true

            while (layerNewLocations.isNotEmpty()) {
                // 未探索の同じy座標のブロック
                val exploreLayerLocations = layerNewLocations.toMutableList()
                layerNewLocations.clear()
                for (location in exploreLayerLocations) {
                    for (x in -1..1) {
                        for (z in -1..1) {
                            val targetLocate =
                                Location(world, location.x + x, location.y + if (newLayerFlag) 1 else 0, location.z + z)
                            if (abs(targetLocate.x - originLocation.x) > limit || abs(targetLocate.z - originLocation.z) > limit) continue

                            // 探索済みはスキップ
                            if (layerLocations.contains(targetLocate)) continue

                            // 同じブロックなら
                            if (world.getBlockAt(targetLocate).type == originBlock.type) {
                                layerNewLocations.add(targetLocate)
                            }
                            // 葉っぱブロックなら
                            val leavesBlock = world.getBlockAt(targetLocate).blockData
                            if (leavesBlock is Leaves) {
                                // 自然生成なら
                                if (!leavesBlock.isPersistent) {
                                    isNatualTree = true
                                }
                            }
                        }
                    }
                }
                layerLocations += layerNewLocations
                newLayerFlag = false
            }
            ret += layerLocations
        }

        return if (isNatualTree) ret.toList() else null
    }
}