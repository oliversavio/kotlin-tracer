package core

class Point(x: Float, y: Float, z: Float, w: Byte = 1) : Tuple(x, y, z, w) {
    constructor(tuple: Tuple) : this(tuple.x, tuple.y, tuple.z)
}