package core

class Vector(x: Float, y: Float, z: Float, w: Byte = 0) : Tuple(x, y, z, w) {
    constructor(tuple: Tuple) : this(tuple.x, tuple.y, tuple.z)
}