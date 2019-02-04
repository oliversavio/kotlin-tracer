package core

import java.util.*

data class Ray(val origin: Point, val direction: Vector) {
    fun position(time: Float) = Point(this.origin + this.direction * time)
}

interface WObject

data class Sphere(val id: String = UUID.randomUUID().toString()) : WObject

data class Intersection(val t: Float, val obj: WObject)

fun intersections(vararg intersection: Intersection): List<Intersection> {
    val intersections: MutableList<Intersection> = mutableListOf()
    intersection.forEach { intersections.add(it) }
    return intersections
}


fun intersect(sphere: Sphere, ray: Ray): List<Intersection> {

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

    return intersections(Intersection(t1.toFloat(), sphere), Intersection(t2.toFloat(), sphere))
}

private fun discriminant(sphereToRay: Vector, a: Float, b: Float, c: Float): Float = Math.pow(b.toDouble(), 2.0).toFloat() - (4 * a * c)
