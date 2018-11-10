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
        val zero = vector(0f,0f,0f)
        val v = vector(1f, -2f, 3f)

        assertEquals(vector(-1f, 2f, -3f), zero - v)
    }

    @Test
    fun test_negating_a_tuple() {
        val a = Tuple(1f, -2f, 3f, -4)
        assertEquals(Tuple(-1f, 2f, -3f, 4), -a)
    }

}