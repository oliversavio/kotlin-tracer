package core

import java.util.*

data class Ray(val origin: Point, val direction: Vector) {

    fun position(time: Float) = Point(this.origin + this.direction * time)


}

data class Sphere(val id: String = UUID.randomUUID().toString()) {
    private val xs = mutableListOf<Float>()

    fun intersect(ray: Ray): List<Float> {

        val sphereToRay = Vector(ray.origin - Point())
        val a = dot(ray.direction, ray.direction)
        val b = 2 * dot(ray.direction, sphereToRay)
        val c = dot(sphereToRay, sphereToRay) - 1


        val discriminant = discriminant(sphereToRay, a, b, c)
        if (discriminant < 0) {
            return emptyList()
        }

        val srqtOfDiscriminant = Math.sqrt(discriminant.toDouble())
        val t1 = (-b - srqtOfDiscriminant) / (2 * a)
        val t2 = (-b + srqtOfDiscriminant) / (2 * a)

        return listOf(t1.toFloat(), t2.toFloat())
    }

    private fun discriminant(sphereToRay: Vector, a: Float, b: Float, c: Float): Float = Math.pow(b.toDouble(), 2.0).toFloat() - (4 * a * c)


}


