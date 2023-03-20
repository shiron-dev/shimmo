package dev.shiron.shimmo.util

fun secToTick(sec: Float): Int {
    return (20 * sec).toInt()
}

fun toUnitString(num: Double): String {
    val units = arrayOf("", "K", "M", "G", "T", "P", "E", "Z", "Y")
    val base = 1000
    var exponent = 0
    var value = num
    while (value >= base && exponent < units.size - 1) {
        value /= base
        exponent++
    }
    return "%.1f%s".format(value, units[exponent])
}