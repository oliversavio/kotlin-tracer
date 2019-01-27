package core

data class Ray(val origin: Point, val direction: Vector) {

    fun position(time: Float) = Point(this.origin + this.direction * time)


}