package core

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class RayTest {


    @Test
    fun test_create_and_query_ray() {
        val point = Point(1f, 2f, 3f)
        val direction = Vector(4f, 5f, 6f)

        val ray = Ray(point, direction)

        assertEquals(point, ray.origin)
        assertEquals(direction, ray.direction)
    }


    @Test
    fun test_compute_point_from_distance() {
        val ray = Ray(Point(2f, 3f, 4f), Vector(1f, 0f, 0f))

        assertEquals(Point(2f, 3f, 4f), ray.position(0f))
        assertEquals(Point(3f, 3f, 4f), ray.position(1f))
        assertEquals(Point(1f, 3f, 4f), ray.position(-1f))
        assertEquals(Point(4.5f, 3f, 4f), ray.position(2.5f))

    }


    @Test
    fun test_a_ray_intersects_with_sphere_on_tangent() {
        val ray = Ray(Point(0f, 1f, -5f), Vector(0f, 0f, 1f))
        val sphere = Sphere()
        val xs = sphere.intersect(ray)

        assertEquals(2, xs.size)
        assertEquals(5f, xs[0])
        assertEquals(5f, xs[1])

    }


    @Test
    fun test_a_ray_misses_sphere() {
        val ray = Ray(Point(0f, 2f, -5f), Vector(0f, 0f, 1f))
        val sphere = Sphere()
        val xs = sphere.intersect(ray)

        assertEquals(0, xs.size)
    }


    @Test
    fun test_a_ray_originates_inside_sphere() {
        val ray = Ray(Point(0f, 0f, 0f), Vector(0f, 0f, 1f))
        val sphere = Sphere()
        val xs = sphere.intersect(ray)

        assertEquals(2, xs.size)
        assertEquals(-1f, xs[0])
        assertEquals(1f, xs[1])

    }

}