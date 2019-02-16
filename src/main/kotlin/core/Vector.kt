package core

class Vector(x: Float = 0f, y: Float = 0f, z: Float = 0f, w: Byte = 0) : Tuple(x, y, z, w) {
    constructor(tuple: Tuple) : this(tuple.x, tuple.y, tuple.z)
}