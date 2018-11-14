package core

open class Tuple(val x: Float, val y: Float, val z: Float, val w: Byte = Byte.MIN_VALUE) {

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
        return (31 + x.hashCode() + y.hashCode() + z.hashCode() + w.hashCode()).hashCode()
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

        return tupleBuilder(x + o.x, y + o.y, z + o.z, ((w + o.w)).toByte(), this, o)
    }

    operator fun minus(o: Tuple): Tuple {
        if (w - o.w < 0) {
            throw IllegalStateException("Cannot subtract point from vector")
        }
        return tupleBuilder(x - o.x, y - o.y, z - o.z, ((w - o.w)).toByte(), this, o)
    }

    operator fun unaryMinus() = Tuple(-x, -y, -z, (-w).toByte())

    operator fun times(scalar: Float): Tuple {
        return tupleBuilder(x * scalar, y * scalar, z * scalar, (w * scalar).toByte(), this)
    }



    operator fun div(scalar: Float): Tuple {
        return times(1f / scalar)
    }

    override fun toString(): String {
        return "${this.javaClass.name}(x=$x,y=$y,z=$z,w=$w)"
    }

}



