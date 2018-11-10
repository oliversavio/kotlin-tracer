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
        val diffw = Math.abs(w - other.w)

        return diffx < epsilon && diffy < epsilon && diffz < epsilon && diffw < epsilon

    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    fun isPoint(): Boolean {
        return w > 0.toByte()
    }

    fun isVector(): Boolean {
        return w < 1.toByte()
    }

    operator fun plus(o: Tuple): Tuple {
        if (w + o.w > 1) {
            throw IllegalStateException("Cannot add two Points")
        }
        return Tuple(x + o.x, y + o.y, z + o.z, ((w + o.w)).toByte())
    }

    operator fun minus(o: Tuple): Tuple {
        if (w - o.w < 0) {
            throw IllegalStateException("Cannot subtract point from vector")
        }
        return Tuple(x - o.x, y - o.y, z - o.z, ((w - o.w)).toByte())
    }

    operator fun unaryMinus() = Tuple(-x, -y, -z, (-w).toByte())

    operator fun times(scalar: Float): Tuple {
        return Tuple(x * scalar, y * scalar, z * scalar, (w * scalar).toByte())
    }

    operator fun div(scalar: Float): Tuple {
        return times(1f / scalar)
    }


}

fun point(x: Float, y: Float, z: Float): Tuple {
    return Tuple(x, y, z, 1)
}

fun vector(x: Float, y: Float, z: Float): Tuple {
    return Tuple(x, y, z, 0)
}

fun magnitude(vector: Tuple): Double {
    if (!vector.isVector()) {
        throw IllegalArgumentException("Can only calculate magnitude of Vectors")
    }

    val xSqd = vector.x * vector.x
    val ySqd = vector.y * vector.y
    val zSqd = vector.z * vector.z

    return Math.sqrt(xSqd.toDouble() + ySqd.toDouble() + zSqd.toDouble())
}