package core

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LightAndShading {
    @Test
    fun test_normal_of_sphere_at_point_on_x_axis() {
        val s = Sphere()
        Assertions.assertEquals(Vector(x = 1f), normalAt(s, Point(x = 1f)))
    }

    @Test
    fun test_normal_of_sphere_at_point_on_y_axis() {
        val s = Sphere()
        Assertions.assertEquals(Vector(y = 1f), normalAt(s, Point(y = 1f)))
    }

    @Test
    fun test_normal_of_sphere_at_point_on_z_axis() {
        val s = Sphere()
        Assertions.assertEquals(Vector(z = 1f), normalAt(s, Point(z = 1f)))
    }

    @Test
    fun test_normal_at_shere_at_non_axial_point() {
        val s = Sphere()
        val xyz = (Math.sqrt(3.0) / 3).toFloat()
        Assertions.assertEquals(Vector(x = xyz, y = xyz, z = xyz), normalAt(s, Point(x = xyz, y = xyz, z = xyz)))
    }

    @Test
    fun test_nornal_vector_is_normalized() {
        val s = Sphere()
        val xyz = (Math.sqrt(3.0) / 3).toFloat()
        val n = normalAt(s, Point(x = xyz, y = xyz, z = xyz))
        Assertions.assertEquals(normalize(n), n)
    }

    @Test
    fun test_compute_normal_on_transated_sphere() {
        val t = Matrix.identity().translate(0f, 1f, 0f)
        val s = Sphere(transform = t)
        val normal = normalAt(s, Point(y = 1.70711f, z = -0.70711f))
        val n = Vector(0f, 0.70711f, -0.70711f)
        Assertions.assertEquals(n, normal)
    }

    @Test
    fun test_compute_normal_on_transformed_sphere() {
        val t = Matrix.identity().rotate(Math.PI / 5, Matrix.RotationAxis.Z).scale(1f, 0.5f, 1f)
        val s = Sphere(transform = t)
        val pointVal = (Math.sqrt(2.0) / 2).toFloat()
        val normal = normalAt(s, Point(y = pointVal, z = -pointVal))
        val n = Vector(y = 0.97014f, z = -0.24254f)
        Assertions.assertEquals(n, normal)
    }
}