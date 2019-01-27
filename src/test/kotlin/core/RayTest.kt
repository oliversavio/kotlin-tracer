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

}