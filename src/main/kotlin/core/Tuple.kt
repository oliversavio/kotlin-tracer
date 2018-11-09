package core

data class Tuple(val x: Float, val y: Float, val z: Float, val w: Byte) {

    fun isPoint(): Boolean {
        return w == 1.toByte()
    }

    fun isVector(): Boolean {
        return w == 0.toByte()
    }
}

fun point(x: Float, y: Float, z: Float): Tuple {
    return Tuple(x, y, z, 1)
}

fun vector(x: Float, y: Float, z: Float): Tuple {
    return Tuple(x, y, z, 0)
}
