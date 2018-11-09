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
        assertEquals(point(4.1f,-4.3f,1.010001f), point(4.1f,-4.3f,1.01001f))
        assertEquals(point(4.1f,-4.3f,1.01f), point(4.1f,-4.3f,1.01f))
    }


}