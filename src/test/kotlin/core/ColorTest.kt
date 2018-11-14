package core

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ColorTest {


    @Test
    fun test_colors_are_rgb_tuples() {
        val c = Color(-0.5f, 0.4f, 1.7f)
        assertEquals(-0.5f, c.r)
        assertEquals(0.4f, c.g)
        assertEquals(1.7f, c.b)
    }

    @Test
    fun test_adding_colors() {
        val c1 = Color(0.9f, 0.6f, 0.75f)
        val c2 = Color(0.7f, 0.1f, 0.25f)
        assertEquals(Color(1.6f, 0.7f, 1.0f), c1 + c2)
    }


    @Test
    fun test_subtract_colors() {
        val c1 = Color(0.9f, 0.6f, 0.75f)
        val c2 = Color(0.7f, 0.1f, 0.25f)
        assertEquals(Color(0.2f, 0.5f, 0.50f), c1 - c2)
    }

    @Test
    fun test_miltiply_color_by_scalar() {
        val c = Color(0.2f, 0.3f, 0.4f)
        assertEquals(Color(0.4f, 0.6f, 0.8f), c * 2f)
    }


}