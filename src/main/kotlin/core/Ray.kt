package core

import java.util.*

data class Ray(val origin: Point, val direction: Vector) {
    fun position(time: Float) = Point(this.origin + this.direction * time)

    fun transform(transform: Matrix): Ray {
        val originTrans = transform * this.origin
        val directionTrans = transform * this.direction
        return Ray(Point(originTrans), Vector(directionTrans))
    }


}

interface WObject

data class Sphere(val id: String = UUID.randomUUID().toString(), var transform: Matrix = Matrix.identity()) : WObject {


}

data class Intersection(val t: Float, val obj: WObject)

fun intersections(vararg intersection: Intersection): List<Intersection> {
    val intersections: MutableList<Intersection> = mutableListOf()
    intersection.forEach { intersections.add(it) }
    return intersections
}


fun intersect(sphere: Sphere, r: Ray): List<Intersection> {

    val ray = r.transform(sphere.transform.inverse())

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

fun hit(intersections: List<Intersection>) =
        intersections
                .filter { it.t > -1 }
                .sortedWith(compareBy(Intersection::t))
                .firstOrNull()


fun normalAt(sphere: Sphere, worldPoint: Point): Vector {
    val objectPoint = sphere.transform.inverse() * worldPoint
    val objectNormal = objectPoint - Point()
    val worldNormal = sphere.transform.inverse().transpose() * objectNormal
    return normalize(Vector(worldNormal))
}


private fun discriminant(sphereToRay: Vector, a: Float, b: Float, c: Float): Float =
        Math.pow(b.toDouble(), 2.0).toFloat() - (4 * a * c)
