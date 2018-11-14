package core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TuplesTest {

    @Test
    fun test_tuple_point() {
        val a = Tuple(4.3f, -4.2f, 3.1f, 1)
        assertEquals(4.3f, a.x)
        assertEquals(-4.2f, a.y)
        assertEquals(3.1f, a.z)
        assertEquals(1, a.w)
        assertTrue(a.isPoint())
        assertFalse(a.isVector())
    }

    @Test
    fun test_tuple_vector() {
        val a = Tuple(4.3f, -4.2f, 3.1f, 0)
        assertEquals(4.3f, a.x)
        assertEquals(-4.2f, a.y)
        assertEquals(3.1f, a.z)
        assertEquals(0, a.w)
        assertFalse(a.isPoint())
        assertTrue(a.isVector())
    }

    @Test
    fun test_create_point() {
        val p = point(4f, -4f, 3f)
        assertTrue(p.isPoint())
    }

    @Test
    fun test_create_vector() {
        val v = vector(4f, -4f, 3f)
        assertTrue(v.isVector())
    }

    @Test
    fun test_compare_tuples() {
        assertEquals(point(4.1f, -4.3f, 1.010001f), point(4.1f, -4.3f, 1.01001f))
        assertEquals(point(4.1f, -4.3f, 1.01f), point(4.1f, -4.3f, 1.01f))
    }

    @Test
    fun test_add_tuples() {
        val a = Tuple(3f, -2f, 5f, 1)
        val b = Tuple(-2f, 3f, 1f, 0)

        assertEquals(Tuple(1f, 1f, 6f, 1), a + b)

    }

    @Test
    fun test_sub_points() {
        val p1 = point(3.0f, 2f, 1f)
        val p2 = point(5f, 6f, 7f)

        assertTrue((p1 - p2).isVector())
        assertEquals(vector(-2f, -4f, -6f), p1 - p2)

    }

    @Test
    fun test_sub_vector_from_points() {
        val p1 = point(3.0f, 2f, 1f)
        val p2 = vector(5f, 6f, 7f)

        assertTrue((p1 - p2).isPoint())
        assertEquals(point(-2f, -4f, -6f), p1 - p2)

    }

    @Test
    fun test_sub_vectors() {
        val p1 = vector(3.0f, 2f, 1f)
        val p2 = vector(5f, 6f, 7f)

        assertTrue((p1 - p2).isVector())
        assertEquals(vector(-2f, -4f, -6f), p1 - p2)

    }

    @Test
    fun test_sub_point_from_vectors() {
        val p1 = point(3.0f, 2f, 1f)
        val p2 = vector(5f, 6f, 7f)

        assertThrows(IllegalStateException::class.java) {
            p2 - p1
        }
    }

    @Test
    fun test_subtracting_a_vector_from_the_zero_vector() {
        val zero = vector(0f, 0f, 0f)
        val v = vector(1f, -2f, 3f)

        assertEquals(vector(-1f, 2f, -3f), zero - v)
    }

    @Test
    fun test_negating_a_tuple() {
        val a = Tuple(1f, -2f, 3f, -4)
        assertEquals(Tuple(-1f, 2f, -3f, 4), -a)
    }

    @Test
    fun test_negating_a_point() {
        val a = point(1f, -2f, 3f)
        assertEquals(Tuple(-1f, 2f, -3f, -1), -a)
    }

    @Test
    fun test_multiplying_a_tuple_by_a_scalar() {
        val a = Tuple(1f, -2f, 3f, -4)
        assertEquals(Tuple(3.5f, -7f, 10.5f, -14), a * 3.5f)
    }

    @Test
    fun test_multiplying_a_tuple_by_a_fraction() {
        val a = Tuple(1f, -2f, 3f, -4)
        assertEquals(Tuple(0.5f, -1f, 1.5f, -2), a * 0.5f)
    }

    @Test
    fun test_dividing_a_tuple_by_a_scalar() {
        val a = Tuple(1f, -2f, 3f, -4)
        assertEquals(Tuple(0.5f, -1f, 1.5f, -2), a / 2f)
    }

    @Test
    fun test_magnitude_of_vector_1_0_0() {
        val v = vector(1f, 0f, 0f)
        assertEquals(1.0, magnitude(v))
    }

    @Test
    fun test_magnitude_of_vector_0_1_0() {
        val v = vector(0f, 1f, 0f)
        assertEquals(1.0, magnitude(v))
    }

    @Test
    fun test_magnitude_of_vector_0_0_1() {
        val v = vector(0f, 0f, 1f)
        assertEquals(1.0, magnitude(v))
    }

    @Test
    fun test_magnitude_of_vector_1_2_3() {
        val v = vector(1f, 2f, 3f)
        assertEquals(Math.sqrt(14.0), magnitude(v))
    }

    @Test
    fun test_magnitude_of_vector_m1_m2_m3() {
        val v = vector(-1f, -2f, -3f)
        assertEquals(Math.sqrt(14.0), magnitude(v))
    }

    @Test
    fun test_normalizing_vector_4_0_0_gives_1_0_0() {
        val v = vector(4f, 0f, 0f)
        assertEquals(vector(1f, 0f, 0f), normalize(v))
    }

    @Test
    fun test_normalizing_vector_1_2_3() {
        val v = vector(1f, 2f, 3f)
        assertEquals(vector(0.26726f, 0.53452f, 0.80178f), normalize(v))
    }

    @Test
    fun test_the_magnitude_of_a_normalized_vector() {
        val v = vector(1f, 2f, 3f)
        assertEquals(1.0, magnitude(normalize(v)), 0.00001)
    }

    @Test
    fun test_the_dot_product_of_two_tuples() {
        val a = vector(1f, 2f, 3f)
        val b = vector(2f, 3f, 4f)

        assertEquals(20f, dot(a, b))
    }

    @Test
    fun test_cross_product_of_two_vectors() {
        val a = vector(1f, 2f, 3f)
        val b = vector(2f, 3f, 4f)

        assertEquals(vector(-1f, 2f, -1f), cross(a, b))
        assertEquals(vector(1f, -2f, 1f), cross(b, a))
    }




}

