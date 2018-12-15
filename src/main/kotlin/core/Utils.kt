package core

import java.util.Arrays

const val EPSILON = 0.00001f

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

fun normalize(vector: Vector): Vector {
    if (!vector.isVector()) {
        throw IllegalArgumentException("Can only normalize Vectors")
    }
    val magnitude = magnitude(vector)

    return Vector(vector.x / magnitude.toFloat(),
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

fun tupleBuilder(x: Float, y: Float, z: Float, w: Byte, op1: Tuple, op2: Tuple = op1): Tuple {
    /*
        P = 1, V = 0, C = -128
        ADD:
            P - P = V

            P + V = P
            P - V = P

            V + P = P

            V + V = V
            V - V = V
        SUB:

     */


    when {
        op1 is Color && op2 is Color -> return Color(x, y, z)
        op1 is Point && op2 is Point -> return Vector(x, y, z, w)
        op1 is Point && op2 is Vector -> return Point(x, y, z, w)
        op1 is Vector && op2 is Point -> return Point(x, y, z, w)
        op1 is Vector && op2 is Vector -> return Vector(x, y, z, w)
        else -> return Tuple(x, y, z, w)

    }

}


fun scaleLinear(range: List<Float>, sMin: Float, sMax: Float): List<Float> {

    val sorted = range.sorted()
    val min = sorted[0]
    val max = sorted[sorted.size - 1]

    return range.map { num -> (sMax - sMin) * (num - min) / (max - min) + sMin }

}

fun eq(l: Float, r: Float) = Math.abs(l - r) < EPSILON

fun eq(l: Byte, r: Byte) = Math.abs(l - r) < EPSILON


fun main(args: Array<String>) {
    println(scaleLinear(listOf(-4f, 0f, 5f, 6f, 9f), 0f, 13f))
    println(scaleLinear(listOf(0f, 4f, 5f, 6f, 9f), 0f, 9f))
}