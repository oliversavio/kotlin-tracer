package core

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CanvasTest {

    @Test
    fun test_create_canvas() {
        val c = Canvas(10, 20)
        assertEquals(10, c.width)
        assertEquals(20, c.height)

        for (i in 0 until c.height - 1) {
            for (j in 0 until c.width - 1) {
                assertEquals(Color.black(), c.canvas[i][j])
            }
        }
    }

    @Test
    fun test_writing_pixel_to_canvas() {
        val canvas = Canvas(10, 20)
        val red = Color(1f, 0f, 0f)
        canvas.writePixel(2, 3, red)

        assertEquals(red, canvas.pixelAt(2, 3))

    }

    @Test
    fun test_construct_the_ppm_header() {
        val c = Canvas(5,3)
        c.writePixel(0,0, Color(1.5f, 0f, 0f))
        c.writePixel(1,2, Color(0f, 0.5f, 0f))
        c.writePixel(2,4, Color(-0.5f, 0f, 1f))
        println(c.canvasToPPM())
    }
}