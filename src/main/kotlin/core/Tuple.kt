package core

data class Tuple(val x: Float, val y: Float, val z: Float, val w: Byte) {

    private val epsilon = 0.00001f

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Tuple

        val diffx = Math.abs(x - other.x)
        val diffy = Math.abs(y - other.y)
        val diffz = Math.abs(z - other.z)

        return diffx < epsilon && diffy < epsilon && diffz < epsilon

    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

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
