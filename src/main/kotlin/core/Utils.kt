package core

fun point(x: Float, y: Float, z: Float): Point {
    return Point(x, y, z)
}

fun vector(x: Float, y: Float, z: Float): Vector {
    return Vector(x, y, z)
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

fun normalize(vector: Vector): Tuple {
    if (!vector.isVector()) {
        throw IllegalArgumentException("Can only normalize Vectors")
    }
    val magnitude = magnitude(vector)

    return tupleBuilder(vector.x / magnitude.toFloat(),
            vector.y / magnitude.toFloat(),
            vector.z / magnitude.toFloat(),
            (vector.w / magnitude).toByte())
}

fun dot(a: Tuple, b: Tuple): Float {
    if (!a.isVector() || !b.isVector()) {
        throw IllegalArgumentException("Can only calculate dot product of Vectors")
    }

    return a.x * b.x + a.y * b.y + a.z * b.z + a.w * b.w
}

fun cross(a: Tuple, b: Tuple): Tuple {
    if (!a.isVector() || !b.isVector()) {
        throw IllegalArgumentException("Can only calculate cross product of Vectors")
    }

    return vector(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x)
}

fun tupleBuilder(x: Float, y: Float, z: Float, w: Byte): Tuple {
    lateinit var concreteTuple: Tuple
    when {
        w > 0.toByte() -> concreteTuple = Point(x, y, z, w)
        w < 1.toByte() && w > Byte.MIN_VALUE -> concreteTuple = Vector(x, y, z, w)
        w == Byte.MIN_VALUE -> concreteTuple = Color(x, y, z)
        else -> throw IllegalStateException("Unable to determine tuple type")
    }

    return concreteTuple
}