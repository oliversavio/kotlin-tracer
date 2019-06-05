package core

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
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
        val xs = intersect(sphere, ray)

        assertEquals(2, xs.size)
        assertEquals(5f, xs[0].t)
        assertEquals(5f, xs[1].t)

    }


    @Test
    fun test_a_ray_misses_sphere() {
        val ray = Ray(Point(0f, 2f, -5f), Vector(0f, 0f, 1f))
        val sphere = Sphere()
        val xs = intersect(sphere, ray)

        assertEquals(0, xs.size)
    }


    @Test
    fun test_a_ray_originates_inside_sphere() {
        val ray = Ray(Point(0f, 0f, 0f), Vector(0f, 0f, 1f))
        val sphere = Sphere()
        val xs = intersect(sphere, ray)

        assertEquals(2, xs.size)
        assertEquals(-1f, xs[0].t)
        assertEquals(1f, xs[1].t)

    }


    @Test
    fun test_an_intersection_encapsulates_t_and_object() {
        val s = Sphere()
        val i = Intersection(3.5f, s)

        assertEquals(3.5f, i.t)
        assertEquals(s, i.obj)
    }

    @Test
    fun test_aggregate_intersections() {
        val s = Sphere()
        val i1 = Intersection(1f, s)
        val i2 = Intersection(2f, s)

        val xs = intersections(i1, i2)

        assertEquals(2, xs.size)
        assertEquals(1f, xs[0].t)
        assertEquals(2f, xs[1].t)

    }

    @Test
    fun test_intersect_sets_object_on_intersection() {
        val r = Ray(Point(z = -5f), Vector(0f, 0f, z = 1f))
        val s = Sphere()
        val xs = intersect(s, r)

        assertEquals(2, xs.size)
        assertEquals(s, xs[0].obj)
        assertEquals(s, xs[1].obj)
    }

    @Test
    fun test_hit_when_all_positive_t() {
        val s = Sphere()
        val i1 = Intersection(1f, s)
        val i2 = Intersection(2f, s)
        val xs = intersections(i1, i2)

        assertEquals(i1, hit(xs))

    }

    @Test
    fun test_hit_when_intersections_have_negative_value() {
        val s = Sphere()
        val i1 = Intersection(-1f, s)
        val i2 = Intersection(1f, s)
        val xs = intersections(i1, i2)

        assertEquals(i2, hit(xs))

    }

    @Test
    fun test_hit_when_intersections_have_all_negative_value() {
        val s = Sphere()
        val i1 = Intersection(-1f, s)
        val i2 = Intersection(-2f, s)
        val xs = intersections(i1, i2)

        assertNull(hit(xs))

    }

    @Test
    fun test_hit_is_always_lowest_intersection_value() {
        val s = Sphere()
        val i1 = Intersection(5f, s)
        val i2 = Intersection(7f, s)
        val i3 = Intersection(-3f, s)
        val i4 = Intersection(2f, s)
        val xs = intersections(i1, i2, i3, i4)

        assertEquals(i4, hit(xs))
    }

    @Test
    fun test_translating_a_ray() {
        val r = Ray(Point(1f, 2f, 3f), Vector(0f, 1f, 0f))
        val m = Matrix.translation(3f, 4f, 5f)
        val rt = r.transform(m)
        assertEquals(Point(4f, 6f, 8f), rt.origin)
        assertEquals(Vector(0f, 1f, 0f), rt.direction)
    }

    @Test
    fun test_scaling_a_ray() {
        val r = Ray(Point(1f, 2f, 3f), Vector(0f, 1f, 0f))
        val m = Matrix.scaling(2f, 3f, 4f)
        val rt = r.transform(m)
        assertEquals(Point(2f, 6f, 12f), rt.origin)
        assertEquals(Vector(0f, 3f, 0f), rt.direction)
    }

    @Test
    fun test_default_sphere_transform() {
        val s = Sphere()
        assertEquals(Matrix.identity(), s.transform)
    }

    @Test
    fun test_modify_sphere_transform() {
        val s = Sphere()
        val t = Matrix.translation(2f, 3f, 4f)
        s.transform = t
        assertEquals(t, s.transform)
    }

    @Test
    fun test_intersect_scaled_sphere_with_ray() {
        val r = Ray(Point(z = -5f), Vector(z = 1f))
        val s = Sphere(transform = Matrix.scaling(2f, 2f, 2f))
        val xs = intersect(s, r)
        assertEquals(2, xs.size)
        assertEquals(3f, xs[0].t)
        assertEquals(7f, xs[1].t)
    }

    @Test
    fun test_intersect_a_translated_sphere_with_ray() {
        val r = Ray(Point(z = -5f), Vector(z = 1f))
        val s = Sphere(transform = Matrix.translation(5f, 0f, 0f))
        val xs = intersect(s, r)
        assertEquals(0, xs.size)

    }




}